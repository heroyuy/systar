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
    //皮肤文件的路径
    public Skin() {
//        skinPath = PATH;
        img = Image.createImage(PATH);
    }

    public Image getBackgroud() {

        Image img_ = null;
        img_ = Image.copyImage(img, 0, 0, 128, 128);
        return img_;
    }
}
