package game.impl.model;

import com.soyostar.app.Image;
import game.AbModel;

/**
 * 游戏中可移动元素的父类，如Player、NPC等
 * @author wp_g4
 */
public abstract class Sprite extends AbModel {

    public byte face = 0;
    public int row = 0;
    public int col = 0;
    private Image[][] sequence = null;
    private Image curStepImage = null;

    public void setCharImg(String charImgPath) {
        Image characterImage = Image.createImage(charImgPath);
        sequence = new Image[4][4];
        int w = characterImage.getWidth() / 4;
        int h = characterImage.getHeight() / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sequence[i][j] = Image.copyImage(characterImage, j * w, i * h, w, h);
            }
        }
        System.out.println("face:"+face);
        curStepImage = sequence[(face + 3) % 4][0];
    }

    public Image getCurStepImage() {
        return curStepImage;
    }

    public void setCurStepImage(int face, int index) {
        curStepImage = sequence[(face + 3) % 4][index];
    }
}
