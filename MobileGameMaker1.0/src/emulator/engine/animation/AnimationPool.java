package emulator.engine.animation;

import java.util.Vector;

/**
 *
 * 动画池
 */
public class AnimationPool extends Vector {

    private static AnimationPool animationPool = new AnimationPool();

    public static AnimationPool getInstance() {
        return animationPool;
    }

    private AnimationPool() {
    }

    /**
     * 添加动画
     * @param animation 要添加的动画
     */
    public void addAnimation(Animation animation) {
        System.out.println("addAnimation");
        addElement(animation);
    }

    /**
     * 清除动画池中的所有动画
     */
    public void removeAllAnimations() {
        removeAllElements();
    }

    /**
     * 清理动画池，移除所有播放完的动画
     */
    public void clear() {

        int len = size();
        Animation temp = null;
        for (int i = 0; i < len; i++) {
            temp = (Animation) elementAt(i);
            if (temp.curIndex == temp.frameNum) {
                System.out.println("clear");
                this.removeElementAt(i);
                i--;
                len--;
            }
        }
    }

    /**
     * 获取动画池中指定位置的动画
     * @param i 指定的位置
     * @return 指定位置的动画
     */
    public Animation getAnimation(int i) {
        return (Animation) elementAt(i);
    }
}
