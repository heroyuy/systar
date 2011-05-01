package game;

import emulator.EmulatorGraphics;
import emulator.MotionEvent;
import engine.IGame;
import engine.script.GameEvent;
import game.impl.control.TestControl;
import game.impl.view.TestView;
import game.impl.view.TestView2;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        public String[] views = null;
        public Control control = null;
    }
}
