package game;

import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;
import engine.Game;
import engine.Render;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RpgGame extends Game {

    private long startTime = 0;
    private String configFile = "config/rpg.xml";
    private String curController = null;
    private Map<String, Controller> controls = null;
    private Map<String, AbModel> models = null;

    public long getCurTime() {
        return System.currentTimeMillis() - startTime;
    }

    public RpgGame(Render render) {
        super(render);
        models = new HashMap<String, AbModel>();
        controls = new HashMap<String, Controller>();
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
        for (AbModel model : models.values()) {
            model.update();
        }
        getCurrentControl().updateModel();
    }

    public void exit() {
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
                String fullName = model.getStringValue();
                AbModel tempModel = (AbModel) Class.forName(fullName).newInstance();
                models.put(fullName, tempModel);
            }
            //配置控制器
            XMLObject[] controlList = rpg.getFirstXMLObject("controllers").getXMLObjectArray("controller");
            for (XMLObject control : controlList) {
                String fullName = control.getStringValue();
                AbController tempController = (AbController) Class.forName(fullName).newInstance();
                tempController.setRender(this);
                controls.put(fullName, tempController);
            }
            String currentController = rpg.getFirstXMLObject("currentController").getStringValue();
            setCurrentControl(currentController);
            //------------------------------------结束------------------------------------
        } catch (Exception ex) {
            Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public AbModel getModel(String fullName) {
        return models.get(fullName);
    }

    public Controller getCurrentControl() {
        return controls.get(curController);
    }

    public void setCurrentControl(String fullName) {
        if (controls.get(curController) != null) {
            controls.get(curController).onLose();
            this.removeAllComponents();
            this.removeAllWidgets();
        }
        if (controls.containsKey(fullName)) {
            curController = fullName;
            controls.get(curController).onObtain();
        } else {
            try {
                throw new Exception("不存在名为" + fullName + "的控制器");
            } catch (Exception ex) {
                Logger.getLogger(RpgGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
