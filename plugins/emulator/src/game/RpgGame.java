package game;

import com.soyostar.app.KeyEvent;
import com.soyostar.app.Painter;
import com.soyostar.app.TouchEvent;
import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;
import engine.Game;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
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

    public void onTouchEvent(TouchEvent me) {
        getCurrentControl().onTouchEvent(me);
    }

    @Override
    public void onKeyEvent(KeyEvent ke) {
        getCurrentControl().onKeyEvent(ke);
    }

    public void exit() {
    }

    public void render(Painter p) {
        getCurrentControl().updateView(p);
    }

    /**
     * 根据配置文件初始化游戏
     * 1、加载需要自更新的Model
     * 2、加载所有Control
     * 3、设置当前Control
     */
    private void loadConfig() {
        try {
            //------------------------------------开始------------------------------------
            XMLObject rpg = XMLParser.parse(new File(configFile));
            //配置模型
            XMLObject[] modelList = rpg.getFirstXMLObject("models").getXMLObjectArray("model");
            for (XMLObject model : modelList) {
                int id = model.getFirstXMLObject("id").getIntValue();
                String fullName = model.getFirstXMLObject("fullName").getStringValue();
                models.put(id, new ModelNode(id, fullName));
            }
            //配置控制器
            XMLObject[] controlList = rpg.getFirstXMLObject("controls").getXMLObjectArray("control");
            for (XMLObject control : controlList) {
                int id = control.getFirstXMLObject("id").getIntValue();
                String fullName = control.getFirstXMLObject("fullName").getStringValue();
                //配置视图
                XMLObject[] viewList = control.getFirstXMLObject("views").getXMLObjectArray("view");
                Map<Integer, String> views = new HashMap<Integer, String>();
                for (XMLObject view : viewList) {
                    int viewId = view.getFirstXMLObject("id").getIntValue();
                    String viewName = view.getFirstXMLObject("fullName").getStringValue();
                    views.put(viewId, viewName);
                }
                controls.put(id, new ControlNode(id, fullName, views));
            }
            int currentControlID = rpg.getFirstXMLObject("controls").getFirstXMLObject("currentControlID").getIntValue();
            setCurrentControl(currentControlID);
            //------------------------------------结束------------------------------------
        } catch (Exception ex) {
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
