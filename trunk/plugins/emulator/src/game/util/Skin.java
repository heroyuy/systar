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

    public void drawRect(Painter p, Image[] img_, int x, int y, int width, int height) {
        Image[] img = img_;
//        if (height % 16 != 0 && height < 32) {
//
//            img[0] = Image.zoomImage(img[0], 16, height / 2);
//            img[2] = Image.zoomImage(img[2], 16, height / 2);
//            img[6] = Image.zoomImage(img[6], 16, height / 2);
//            img[8] = Image.zoomImage(img[8], 16, height / 2);
//        }


        p.drawImage(img[0], x, y, Painter.LT);
        p.drawImage(img[2], x + width - 16, y, Painter.LT);
        p.drawImage(img[6], x, y + height - 16, Painter.LT);
        p.drawImage(img[8], x + width - 16, y + height - 16, Painter.LT);

//        if (width > 32) {
//            for (int i = 1; i < (width - 16) / 16; i++) {
//                p.drawImage(img[1], x + i * 16, y, Painter.LT);
//                p.drawImage(img[7], x + i * 16, y + height - 16, Painter.LT);
//            }
//        }  if (height > 32 ) {
//             for (int i = 0; i < (height - 16) / 16; i++) {
//                p.drawImage(img[3], x , y+ i * 16, Painter.LT);
//                p.drawImage(img[5], x+ width - 16 , y+ i * 16 , Painter.LT);
//            }
//
//        }


    }

    public void drawRect(Painter painter,int x,int y,int width,int height){
        Image img=Image.copyImage(this.img, 128, 64, 32, 32);
        painter.drawImage(Image.zoomImage(img, width, height), x, y, Painter.LT);
//        painter.drawImage(img, x, y, Painter.LT);
    }
}
