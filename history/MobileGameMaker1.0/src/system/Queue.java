package system;

import java.util.Vector;

/**
 * 队列
 */
public class Queue extends Vector {

    /**
     * 将指定的元素入队
     * @param o 要入队的元素
     */
    public synchronized void offer(Object o) {
        addElement(o);
    }

    /**
     * 将指定的元素数组依次入队
     * @param o 要入队的元素数组
     */
    public synchronized void offer(Object[] o) {
        for (int i = 0; i < o.length; i++) {
            addElement(o);
        }
    }

    /**
     * 获取并移除此队列的头，如果此队列为空，则返回 null。
     * @return 队列的头，如果此队列为空，则返回 null
     */
    public synchronized Object poll() {
        Object o = null;
        if (size() > 0) {
            o = elementAt(0);
            removeElementAt(0);
        }
        return o;
    }

    /**
     * 获取但不移除此队列的头；如果此队列为空，则返回 null。
     * @return 此队列的头；如果此队列为空，则返回 null
     */
    public synchronized Object peck() {
        Object o = null;
        if (size() > 0) {
            o = elementAt(0);
        }
        return o;
    }

    /**
     * 将队列中的元素按出队的顺序保存到数组中
     * @return 如果队列不为空则返回一个元素数组，否则返回null
     */
    public synchronized Object[] peckAll() {
        Object[] o = null;
        int size = size();
        if (size > 0) {
            o = new Object[size];
            for (int i = 0; i < size; i++) {
                o[i] = peck();
            }

        }
        return o;
    }
}
