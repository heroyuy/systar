package game.impl.model;

import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import game.AbModel;

/**
 * 游戏中可移动元素的父类，如Player、NPC等
 * @author wp_g4
 */
public abstract class Sprite extends AbModel {

    public static final byte STATE_STAND = 0;
    public static final byte STATE_WALKING = 1;
    public static final byte STATE_TALK = 2;
    public static final byte FACE_UP = 3;
    public static final byte FACE_DOWN = 0;
    public static final byte FACE_LEFT = 1;
    public static final byte FACE_RIGHT = 2;
    private byte state = STATE_STAND;
    private Image[][] chartlets = null;
    private int curStep = -1;
    //以下是子类需要使用的字段
    public int row = -1;
    public int col = -1;
    public int face = -1;
    public int moveSpeed = -1;

    public void goUp() {
        if (state == STATE_STAND) {
            face = FACE_UP;
            state = STATE_WALKING;
        }
    }

    public void goDown() {
        if (state == STATE_STAND) {
            face = FACE_DOWN;
            state = STATE_WALKING;
        }
    }

    public void goLeft() {
        if (state == STATE_STAND) {
            face = FACE_LEFT;
            state = STATE_WALKING;
        }
    }

    public void goRight() {
        if (state == STATE_STAND) {
            face = FACE_RIGHT;
            state = STATE_WALKING;
        }
    }

    public final void setChartlet(String path) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public final void setChartlet(Image chartlet) {
        int width = chartlet.getWidth() / 4;
        int height = chartlet.getHeight() / 4;
        chartlets = new Image[4][4];
        for (int i = 0; i < chartlets.length; i++) {
            for (int j = 0; j < chartlets[i].length; j++) {
                chartlets[i][j] = Image.copyImage(chartlet, i * width, j * height, width, height);
            }
        }
    }

    public final void draw(Painter p, int x, int y) {
        p.drawImage(chartlets[face][curStep], x, y, 0);
    }

    public void update() {
        if (state == STATE_WALKING) {
            curStep = (curStep + 1) % 4;
            if (curStep == 0) {
                state = STATE_STAND;
            }
        } else if (state == STATE_TALK) {
            if (curStep != 0) {
                curStep = (curStep + 1) % 4;
            }
        }
    }
}
