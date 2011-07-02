/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.util;

import com.soyostar.app.Image;

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
}
