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

public class RpgGame implements IGame {

    private Map<Integer, Control> controls = null;
    private Map<Integer, IModel> models = null;
    private int curControl = -1;
    private long startTime = 0;
    private boolean isDealingGameEvent = false;

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
        controls = new HashMap<Integer, Control>();
        models = new HashMap<Integer, IModel>();
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
        controls.get(curControl).updateModel();
    }

    public boolean dealGameEvent(GameEvent event) {
        if (isDealingGameEvent) {
            return false;
        }
        controls.get(curControl).dealGameEvent(event);
        return true;
    }

    public void dealKeyEvent(int key) {
        controls.get(curControl).dealKeyEvent(key);
    }

    public void onTouchEvent(MotionEvent me) {
        controls.get(curControl).onTouchEvent(me);
    }

    public void exit() {
    }

    public void render(EmulatorGraphics g) {
        controls.get(curControl).updateView(g);
    }

    public void setCurControl(int index) {
        if (controls.containsKey(index) || index != curControl) {
            if (controls.get(curControl) != null) {
                controls.get(curControl).onLose();
            }
            curControl = index;
            controls.get(curControl).onObtain();
        }
    }

    /**
     * 根据配置文件初始化游戏
     * 1、加载所有Control
     * 2、设置当前Control
     * 3、加载需要自更新的Model
     * 4、设置数据处理器
     */
    private void loadConfig() {
        controls.put(0, new TestControl());
        ((AbControl)controls.get(0)).addView(new TestView());
        ((AbControl)controls.get(0)).addView(new TestView2());
        setCurControl(0);

    }
}
