package game;

import emulator.ui.EmulatorGraphics;
import emulator.MotionEvent;
import engine.Game;
import engine.script.GameEvent;
import engine.script.IDataHandler;
import engine.script.ScriptEngine;
import game.data.ImageManager;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RpgGame extends Game {

    private long startTime = 0;
    private boolean isDealingGameEvent = false;
    private String configFile = "rpg.xml";
    private int curControlID = -1;
    private Map<Integer, ControlNode> controls = null;
    private Map<Integer, ModelNode> models = null;

    public void startGameEvent() {
        isDealingGameEvent = true;
    }

    public void finishGameEvent() {
        isDealingGameEvent = false;
    }

    public long getCurTime() {
        return System.currentTimeMillis() - startTime;
    }

    public RpgGame() {
        models = new HashMap<Integer, ModelNode>();
        controls = new HashMap<Integer, ControlNode>();
    }

    public void start() {
        loadConfig();
        startTime = System.currentTimeMillis();
    }

    /**
     * 运行游戏逻辑
     * 1、更新需要自更新的Model
     * 2、更新由控制器控制的Model
     */
    public void update() {
//        System.out.println("自动更新Model");
        for (ModelNode mn : models.values()) {
            mn.model.update();
        }
        getCurrentControl().updateModel();
    }

    public boolean dealGameEvent(GameEvent event) {
        if (isDealingGameEvent) {
            return false;
        }
        getCurrentControl().dealGameEvent(event);
        return true;
    }

    public void dealKeyEvent(int key) {
        getCurrentControl().dealKeyEvent(key);
    }

    public void onTouchEvent(MotionEvent me) {
        getCurrentControl().onTouchEvent(me);
    }

    public void exit() {
    }

    public void render(EmulatorGraphics g) {
        getCurrentControl().updateView(g);
    }

    /**
     * 根据配置文件初始化游戏
     * 1、加载所有Control
     * 2、设置当前Control
     * 3、加载需要自更新的Model
     * 4、设置数据处理器
     */
    private void loadConfig() {
        try {
            File f = new File(configFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            Element root = doc.getDocumentElement();
            //------------------------------------开始------------------------------------

            //配置模型
            NodeList modelList = root.getElementsByTagName("models");
            modelList = ((Element) modelList.item(0)).getElementsByTagName("model");
            System.out.println("modelList.getLength():" + modelList.getLength());
            for (int i = 0; i < modelList.getLength(); i++) {
                Element model = (Element) modelList.item(i);
                NodeList modelChilds = model.getChildNodes();
                System.out.println("modelChilds.getLength():" + modelChilds.getLength());
                int modelId = -1;
                String modelName = null;
                for (int j = 0; j < modelChilds.getLength(); j++) {
                    Node modelChild = modelChilds.item(j);
                    if (modelChild.getNodeType() == Node.ELEMENT_NODE) {
                        if (modelChild.getNodeName().equals("id")) {
                            modelId = Integer.parseInt(modelChild.getFirstChild().getNodeValue());
                            System.out.println("modelId:" + modelId);
                        } else if (modelChild.getNodeName().equals("fullName")) {
                            modelName = modelChild.getFirstChild().getNodeValue();
                            System.out.println("modelName:" + modelName);
                        }
                    }
                }
                models.put(modelId, new ModelNode(modelId, modelName));
            }

            //配置数据处理器
            String dataHandlerName = root.getElementsByTagName("dataHandler").item(0).getFirstChild().getNodeValue();
            IDataHandler dataHandler = (IDataHandler) Class.forName(dataHandlerName).newInstance();
            ScriptEngine.getInstance().setDataHandler(dataHandler);

            //配置控制器
            NodeList controlList = root.getElementsByTagName("controls");
            controlList = ((Element) controlList.item(0)).getElementsByTagName("control");
            System.out.println("controlList:" + controlList.getLength());
            for (int i = 0; i < controlList.getLength(); i++) {
                Element control = (Element) controlList.item(i);
                NodeList controlChilds = control.getChildNodes();
                System.out.println("controlChilds.getLength():" + controlChilds.getLength());
                int controlId = -1;
                String controlName = null;
                Map<Integer, String> views = new HashMap<Integer, String>();
                for (int j = 0; j < controlChilds.getLength(); j++) {
                    Node controlChild = controlChilds.item(j);
                    if (controlChild.getNodeType() == Node.ELEMENT_NODE) {
                        if (controlChild.getNodeName().equals("id")) {
                            controlId = Integer.parseInt(controlChild.getFirstChild().getNodeValue());
                            System.out.println("controlId:" + controlId);
                        } else if (controlChild.getNodeName().equals("fullName")) {
                            controlName = controlChild.getFirstChild().getNodeValue();
                            System.out.println("controlName:" + controlName);
                        } else if (controlChild.getNodeName().equals("views")) {

                            //配置视图
                            NodeList viewList = ((Element) controlChild).getElementsByTagName("view");
                            System.out.println("viewlList:" + viewList.getLength());
                            for (int k = 0; k < viewList.getLength(); k++) {
                                Element view = (Element) viewList.item(k);
                                NodeList viewChilds = view.getChildNodes();
                                int viewId = -1;
                                String viewName = null;
                                for (int l = 0; l < viewChilds.getLength(); l++) {
                                    Node viewChild = viewChilds.item(l);
                                    if (viewChild.getNodeType() == Node.ELEMENT_NODE) {
                                        if (viewChild.getNodeName().equals("id")) {
                                            viewId = Integer.parseInt(viewChild.getFirstChild().getNodeValue());
                                            System.out.println("viewId:" + viewId);
                                        } else if (viewChild.getNodeName().equals("fullName")) {
                                            viewName = viewChild.getFirstChild().getNodeValue();
                                            System.out.println("viewName:" + viewName);
                                        }
                                        views.put(viewId, viewName);
                                    }
                                }
                            }
                        }
                    }
                }
                controls.put(controlId, new ControlNode(controlId, controlName, views));
            }
            setCurrentControl(Integer.parseInt(root.getElementsByTagName("currentControlID").item(0).getFirstChild().getNodeValue()));

            System.out.println("curControlID:" + curControlID);

            //------------------------------------结束------------------------------------
        } catch (InstantiationException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public AbModel getModel(int id) {
        System.out.println("models:" + models.size());
        System.out.println("models.get(id)" + models.get(id));
        return models.get(id).model;
    }

    private Control getCurrentControl() {
        return controls.get(curControlID).control;
    }

    public void setCurrentControl(int index) {
        if (controls.get(curControlID) != null) {
            controls.get(curControlID).control.onLose();
        }
        if (controls.containsKey(index)) {
            curControlID = index;
            controls.get(curControlID).control.onObtain();
        } else {
            try {
                throw new Exception("不存在ID为" + index + "的控制器");
            } catch (Exception ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setCurrentControl(String fullName) {
        if (controls.get(curControlID) != null) {
            controls.get(curControlID).control.onLose();
        }
        boolean hasControl = false;
        for (ControlNode cd : controls.values()) {
            if (cd.name.equals(fullName)) {
                curControlID = cd.id;
                controls.get(curControlID).control.onObtain();
                hasControl = true;
                break;
            }
        }
        if (!hasControl) {
            try {
                throw new Exception("不存在名为" + fullName + "的控制器");
            } catch (Exception ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class ControlNode {

        public ControlNode(int id, String name, Map<Integer, String> views) {
            try {
                this.id = id;
                this.name = name;
                this.control = (AbControl) Class.forName(name).newInstance();
                for (int viewId : views.keySet()) {
                    this.control.addView(viewId, views.get(viewId));
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public int id = -1;
        public String name = null;
        public AbControl control = null;
    }

    private class ModelNode {

        public ModelNode(int id, String name) {
            try {
                this.id = id;
                this.name = name;
                this.model = (AbModel) Class.forName(name).newInstance();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public int id = -1;
        public String name = null;
        public AbModel model = null;
    }
}
