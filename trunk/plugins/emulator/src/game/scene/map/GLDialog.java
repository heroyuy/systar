/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.ImageUtils;
import com.soyostar.app.LTextArea;
import com.soyostar.app.Layer;
import com.soyostar.app.Widget;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class GLDialog extends Layer {

    private Skin skin = new Skin("res/image/skin/001-Blue01.png");
    private Widget headImage = null;
    private LTextArea contentWidget = null;
    private int width = 600;
    private int height = 150;
    private int imageWidth=120,imageHeight=120;

    public GLDialog(Image haed,String content) {
        //设置背景
        this.setSize(width, height);
        this.setBackgroundImage(ImageUtils.createAlphaImage(skin.createBlueBg(width, height, true),80));
        this.setVisible(true);
        //设置头像框
        headImage=new Widget();
        headImage.setSize(imageWidth, imageHeight);
        headImage.setLocation((height-imageHeight)/2, (height-imageHeight)/2);
        headImage.setBackgroundImage(haed);
        headImage.setVisible(true);
        this.addWidget(headImage);
        //设置文字框
        contentWidget=new LTextArea(content);
        contentWidget.setSize(width-height-(height-imageHeight)/2, imageHeight);
        contentWidget.setLocation(height, (height-imageHeight)/2);
        contentWidget.setTextSize(16);
        contentWidget.setTextColor(Color.WHITE);
        contentWidget.setLeading(5);
        contentWidget.setVisible(true);
        this.addWidget(contentWidget);
    }
}
