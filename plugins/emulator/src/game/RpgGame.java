package game;

import emulator.EmulatorGraphics;
import emulator.MotionEvent;
import engine.IGame;
import engine.script.GameEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class RpgGame implements IGame {

    private Map<Integer, IModel> models = null;
    private long startTime = 0;
    private boolean isDealingGameEvent = false;
    private String configFile = "rpg.xml";
    private int curControlID = -1;
    private Map<Integer, ControlNode> controls = null;

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
        models = new HashMap<Integer, IModel>();
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
        System.out.println("自动更新Model");
        for (IModel model : models.values()) {
            model.update();
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

    public void setCurControl(int index) {
        setCurrentControl(index);

    }

    public void setCurControl(String fullName) {
        setCurrentControl(fullName);

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
            NodeList controlList = root.getElementsByTagName("controls");
            controlList = ((Element) controlList.item(0)).getElementsByTagName("control");
            System.out.println("controlList:" + controlList.getLength());
            for (int i = 0; i < controlList.getLength(); i++) {
                Element control = (Element) controlList.item(i);
                NodeList nl2 = control.getChildNodes();
                System.out.println("nl2.getLength():" + nl2.getLength());
                ControlNode cn = new ControlNode();
                for (int j = 0; j < nl2.getLength(); j++) {
                    Node node = nl2.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        if (node.getNodeName().equals("id")) {
                            cn.id = Integer.parseInt(node.getFirstChild().getNodeValue());
                            System.out.println("id:" + cn.id);
                        } else if (node.getNodeName().equals("fullName")) {
                            cn.fullName = node.getFirstChild().getNodeValue();
                            cn.control = (AbControl) Class.forName(cn.fullName).newInstance();
                            System.out.println("fullName:" + cn.fullName);
                        } else if (node.getNodeName().equals("views")) {
                            NodeList viewlList = ((Element) node).getElementsByTagName("view");
                            System.out.println("viewlList:" + viewlList.getLength());
                            for (int k = 0; k < viewlList.getLength(); k++) {
                                Element view = (Element) viewlList.item(k);
                                NodeList nl3 = view.getChildNodes();
                                for(int l=0;l<nl3.getLength();l++){
                                    Node node2 = nl3.item(l);
                                   if(node2.getNodeType()==Node.ELEMENT_NODE){
                                       if(node2.getNodeName().equals("id")){

                                       }else if(node2.getNodeName().equals("fullName")){

                                       }
                                   }
                                }
                            }
                            String viewName = node.getFirstChild().getNodeValue();
                            cn.views.add(viewName);
                            View view = (View) Class.forName(viewName).newInstance();
                            cn.control.addView(view);
                            System.out.println("view:" + node.getFirstChild().getNodeValue());
                        }
                    }
                    controls.put(cn.id, cn);
                }
            }
            curControlID = Integer.parseInt(root.getElementsByTagName("currentControlID").item(0).getFirstChild().getNodeValue());
            System.out.println("curControlID:" + curControlID);
            NodeList nl3 = root.getElementsByTagName("model");
            System.out.println("nl3.getLength():" + nl3.getLength());
            for (int i = 0; i < nl3.getLength(); i++) {
                String modelName = nl3.item(i).getFirstChild().getNodeValue();
                IModel model = (IModel) Class.forName(modelName).newInstance();

                System.out.println("model:" + modelName);
            }
            String dataHandler = root.getElementsByTagName("dataHandler").item(0).getFirstChild().getNodeValue();
            System.out.println("dataHandler:" + dataHandler);

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

    private Control getCurrentControl() {
        return controls.get(curControlID).control;
    }

    private void setCurrentControl(int index) {
        if (controls.containsKey(index)) {
            curControlID = index;
        } else {
            try {
                throw new Exception("不存在ID为" + index + "的控制器");
            } catch (Exception ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setCurrentControl(String fullName) {
        boolean hasControl = false;
        for (ControlNode cd : controls.values()) {
            if (cd.fullName.equals(fullName)) {
                curControlID = cd.id;
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

        public int id = -1;
        public String fullName = null;
        public ArrayList<String> views = new ArrayList<String>();
        public AbControl control = null;
    }
}
