package engine;

import com.soyostar.app.event.KeyEvent;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 游戏主引擎
 *
 */
public final class GameEngine implements Runnable {

    /**
     * 游戏引擎单例模式
     */
    private static GameEngine ge = new GameEngine();

    /**
     * 获取游戏引擎运行时实例
     * @return 游戏引擎运行时实例
     */
    public static GameEngine getInstance() {
        return ge;
    }
    /**
     * 视图渲染层
     */
    private RenderLayer renderLayer = null;
    /**
     * 游戏管理器
     */
    private GameManager gameManager = null;
    /**
     * 循环计数器
     */
    private int ticker = 0;
    /**
     * 当前按键事件
     */
    private KeyEvent ke = null;
    /**
     * 当前触屏事件
     */
    private TouchEvent te = null;
    /**
     * 额定FPS
     */
    private final int FPS = 20;
    /**
     * 实际fps
     */
    private int fps = FPS;
    /**
     * 游戏引擎运行状态标识
     */
    private volatile boolean isRun = false;
    /**
     * 程序入口的引用
     */
    private Main main = null;
    /**
     * 游戏实例
     */
    private Game game = null;

    /**
     * 无参构造器
     *
     */
    private GameEngine() {
        renderLayer = new RenderLayer();
        gameManager = new GameManager();
    }

    /**
     * 退出游戏引擎
     *
     */
    public void exit() {
        isRun = false;
    }

    /**
     * 获取正在游戏引擎中运行的游戏实例
     * @return 正在游戏引擎中运行的游戏实例
     */
    public Game getGame() {
        return game;
    }

    /**
     * 游戏引擎初始化
     */
    private void init() {
        isRun = true;
        game = gameManager.getCurGame();

    }

    /**
     * 检测游戏引擎的运行状态
     *
     * @return 游戏引擎的运行状态
     */
    protected boolean isRun() {
        return isRun;
    }

    /**
     * 获取fps
     * @return fps
     */
    public int getFps() {
        return fps;
    }

    /**
     * 获取循环次数
     * @return 循环次数
     */
    public int getTicker() {
        return ticker;
    }

    /**
     * 游戏引擎主循环
     */
    public void run() {
        try {
            while (isRun) {
                long time = System.currentTimeMillis();

                //更新游戏
                game.update();

                // 渲染视图
                renderLayer.repaint();
                long cost = System.currentTimeMillis() - time;

                // 循环等待
                if (cost < 1000 / FPS) {
                    Thread.sleep(1000 / FPS - cost);      //保证fps维持一个定值
                }
                long time2 = System.currentTimeMillis();

                fps = (int) (1000 / (time2 - time));
                // 循环计数器自增
                ticker++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.stop();
    }

    /**
     * 设置按键事件
     * @param ke KeyEvent对象
     */
    protected void setKeyEvent(KeyEvent ke) {
        this.ke = ke;
    }

    /**
     * 设置触摸事件
     * @param te TouchEvent对象
     */
    protected void setTouchEvent(TouchEvent te) {
        this.te = te;
    }

    /**
     * 清除TouchEvent事件
     */
    public void clearTouchEvent() {
        te = null;
    }

    /**
     * 清除键盘事件
     */
    public void clearKeyEvent() {
        ke = null;
    }

    /**
     * 设置程序入口
     *
     * @param main  程序入口
     *            
     */
    public void setMain(Main main) {
        this.main = main;
        main.setContentPanel(renderLayer);
    }

    /**
     * 获取屏幕宽度
     * @return 屏幕宽度
     */
    public int getScreenWidth() {
        return renderLayer.getWidth();
    }

    /**
     * 获取屏幕高度
     * @return 屏幕的高度
     */
    public int getScreenHeight() {
        return renderLayer.getHeight();
    }

    /**
     * 启动游戏引擎
     *
     */
    public void start() {
        init();
        if (game != null) {
            game.start();
            new Thread(this).start();
        } else {
            main.stop();
        }
    }

    /**
     * 游戏管理器
     */
    private class GameManager {

        private String configFile = "config/config.xml";
        private Map<String, Game> games = null;
        private String curGame = null;

        /**
         * 获取当前game对象
         * @return 当前game对象
         */
        public Game getCurGame() {
            if (games == null) {
                loadConfig();
            }
            return games.get(curGame);

        }

        public void setCurGame(String fullName) {

            if (!games.containsKey(fullName)) {
                try {
                    throw new Exception("不存在名为" + fullName + "的游戏实例");
                } catch (Exception ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                curGame = fullName;
            }
        }

        private void loadConfig() {
            try {
                // 加载配置文件
                games = new HashMap<String, Game>();
                XMLObject engine = XMLParser.parse(new File(configFile));
                curGame = engine.getFirstXMLObject("currentGame").getStringValue();
                XMLObject[] gamesObject = engine.getFirstXMLObject("games").getXMLObjectArray("game");
                for (XMLObject gameObject : gamesObject) {
                    String gameName = gameObject.getStringValue();
                    Game tempGame = (Game) Class.forName(gameName).getConstructor(Render.class).newInstance(renderLayer);
                    games.put(gameName, tempGame);
                }
            } catch (Exception ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }
}
