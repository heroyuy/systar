/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Administrator
 */
public abstract class AbModel {

    private int index = -1;

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        if (this.index != -1) {
            try {
                throw new Exception("不能修改对象索引");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            this.index = index;
        }
    }

    public abstract void update();
}
