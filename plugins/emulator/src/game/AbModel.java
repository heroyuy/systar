package game;

/**
 *
 * @author wp_g4
 */
public abstract class AbModel {

    private int index = -1;

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        if (this.index != -1) {
            try {
                throw new IllegalStateException("不能修改对象索引");
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        } else {
            this.index = index;
        }
    }

    public abstract void update();
}
