/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

import com.soyostar.app.Image;
import com.soyostar.app.Painter;

/**@2011.6.29 by vv
 *本类为获取背景等图片数组的工具类
 * @author Administrator
 */
public class Skin {

    static Image img;
    private static final String PATH = "res/image/skin/001-Blue01.png";

    public Skin() {
//        skinPath = PATH;
        img = Image.createImage(PATH);
    }

    public Image getBackgroud() {
        Image img_ = null;
        img_ = Image.copyImage(img, 0, 0, 128, 128);
        return img_;
    }

    public Image[] getBattleArrows() {
        Image[] img_ = new Image[2];
        img_[0] = Image.copyImage(img, 128, 96, 32, 32);
        img_[1] = Image.copyImage(img, 160, 96, 32, 32);

        return img_;
    }

    public Image[] getItemBackgrouds() {
        Image[] img_ = new Image[9];
        img_[0] = Image.copyImage(img, 128, 64, 16, 16);
        img_[1] = Image.copyImage(img, 136, 64, 16, 16);
        img_[2] = Image.copyImage(img, 144, 64, 16, 16);
        img_[3] = Image.copyImage(img, 128, 72, 16, 16);
        img_[4] = Image.copyImage(img, 136, 72, 16, 16);
        img_[5] = Image.copyImage(img, 144, 72, 16, 16);
        img_[6] = Image.copyImage(img, 128, 80, 16, 16);
        img_[7] = Image.copyImage(img, 136, 80, 16, 16);
        img_[8] = Image.copyImage(img, 144, 80, 16, 16);
        return img_;
    }

    public Image[] getCursorArrows() {
        Image[] img_ = new Image[4];
        img_[0] = Image.copyImage(img, 160, 64, 16, 16);
        img_[1] = Image.copyImage(img, 160, 80, 16, 16);
        img_[2] = Image.copyImage(img, 176, 64, 16, 16);
        img_[3] = Image.copyImage(img, 176, 80, 16, 16);

        return img_;
    }

    public Image[] getFrames() {
        Image[] img_ = new Image[9];
        img_[0] = Image.copyImage(img, 128, 0, 16, 16);
        img_[1] = Image.copyImage(img, 144, 0, 16, 16);
        img_[2] = Image.copyImage(img, 176, 0, 16, 16);
        img_[3] = Image.copyImage(img, 128, 16, 16, 16);
        img_[4] = Image.copyImage(img, 152, 24, 16, 16);
        img_[5] = Image.copyImage(img, 176, 16, 16, 16);
        img_[6] = Image.copyImage(img, 128, 48, 16, 16);
        img_[7] = Image.copyImage(img, 144, 48, 16, 16);
        img_[8] = Image.copyImage(img, 176, 48, 16, 16);
        return img_;
    }

    public Image[] getItemArrows() {
        Image[] img_ = new Image[4];
        img_[0] = Image.copyImage(img, 152, 16, 16, 8);
        img_[1] = Image.copyImage(img, 144, 24, 8, 16);
        img_[2] = Image.copyImage(img, 168, 24, 8, 16);
        img_[3] = Image.copyImage(img, 152, 40, 16, 8);
        return img_;
    }

    public void drawRect(Painter painter, int x, int y, int width, int height) {
        Image img = Image.copyImage(this.img, 128, 64, 32, 32);
        painter.drawImage(Image.zoomImage(img, width, height), x, y, Painter.LT);
//        painter.drawImage(img, x, y, Painter.LT);
    }

    public Image createRect(int width, int height) {
        Image[] img_ = getFrames();
        Image img_1 = Image.createImage(width, height);
        Painter p = img_1.getPainter();
        if (width <= 32) {
            img_[0] = Image.copyImage(img, 128, 0, width / 2, height / 2);
            img_[2] = Image.copyImage(img, 192 - width / 2, 0, width / 2, height / 2);
            p.drawImage(img_[0], 0, 0, Painter.LT);
            p.drawImage(img_[2], img_1.getWidth() >> 1, 0, Painter.LT);
        } else {
            int t = width / 16;
            int m = width % 16;
            for (int i = 0; i < t - 2; i++) {
                p.drawImage(img_[1], 16 + i * 16, 0, Painter.LT);
                p.drawImage(img_[7], 16 + i * 16, img_1.getHeight() - 16, Painter.LT);
            }
            if (m == 0) {
            } else {
                p.drawImage(Image.copyImage(img, 144, 0, m, 16), (t - 1) * 16, 0, Painter.LT);
                p.drawImage(Image.copyImage(img, 144, 48, m, 16), (t - 1) * 16, img_1.getHeight() - 16, Painter.LT);
            }
            p.drawImage(img_[0], 0, 0, Painter.LT);
            p.drawImage(img_[2], img_1.getWidth() - 16, 0, Painter.LT);

        }
        if (height <= 32) {
            p.drawImage(img_[6], 0, img_1.getHeight() >> 1, Painter.LT);
            p.drawImage(img_[8], img_1.getWidth() >> 1, img_1.getHeight() >> 1, Painter.LT);
            img_[6] = Image.copyImage(img, 128, 64 - height / 2, width / 2, height / 2);
            img_[8] = Image.copyImage(img, 192 - width / 2, 64 - height / 2, width / 2, height / 2);
        } else {
            int th = height / 16;
            int mh = height % 16;
            for (int i = 0; i < th - 2; i++) {
                p.drawImage(img_[3], 0, 16 + i * 16, Painter.LT);
                p.drawImage(img_[5], img_1.getWidth() - 16, 16 + i * 16, Painter.LT);
            }
            if (mh == 0) {
            } else {
                p.drawImage(Image.copyImage(img, 128, 16, 16, mh), 0, (th - 1) * 16, Painter.LT);
                p.drawImage(Image.copyImage(img, 176, 16, 16, mh), img_1.getWidth() - 16, (th - 1) * 16, Painter.LT);
            }
            p.drawImage(img_[6], 0, img_1.getHeight() - 16, Painter.LT);
            p.drawImage(img_[8], img_1.getWidth() - 16, img_1.getHeight() - 16, Painter.LT);
        }
        return img_1;

    }

    public Image createBlueBg(int width, int height, boolean hasFrame) {
        Image img_  = Image.zoomImage(getBackgroud(), width, height);
        if (hasFrame) {
            Painter p = img_.getPainter();
            p.drawImage(this.createRect(width, height), 0, 0, Painter.LT);
        }
        return img_;
    }
    public Image createAlphaBg(int width, int height, boolean hasFrame) {
          Image img_  = Image.zoomImage(Image.copyImage(this.img, 128, 64, 32, 32), width, height);
        if (hasFrame) {
            Painter p = img_.getPainter();
            p.drawImage(this.createRect(width, height), 0, 0, Painter.LT);
        }
        return img_;
    }
    }

