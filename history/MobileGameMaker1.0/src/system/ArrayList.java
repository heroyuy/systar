package system;

/**
 *
 * 整型动态数组
 */
public class ArrayList {

    private int[] data = null;//数据
    private int increment = 10;//当存储空间不够时一次性追加的空间量
    private static final int DEFAULTLENGHT = 10;//默认的动态数组的初始大小
    private int size = 0;//动态数组中当前元素个数

    /**
     * 创建一个默认初始大小的动态数组
     */
    public ArrayList() {
        data = new int[DEFAULTLENGHT];
        clear();
    }

    /**
     * 创建一个指定初始大小的动态数组
     * @param lenght 指定的初始大小
     */
    public ArrayList(int lenght) {
        data = new int[lenght];
        clear();
    }

    /**
     * 创建一个指定初始大小、指定增量的动态数组
     * @param lenght 指定的初始大小
     * @param increment 指定的增量
     */
    public ArrayList(int lenght, int increment) {
        data = new int[lenght];
        this.increment = increment;
        clear();
    }

    public int size() {
        return size;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public int getIncrement() {
        return increment;
    }

    public int capacity() {
        return data.length;
    }

    public int[] toArray() {
        int[] array = new int[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }

    /**
     * 添加指定的索引
     * @param index
     * @throws Exception
     */
    public synchronized boolean add(int index) {
        if (has(index)) {
            //添加一个已经存在的索引抛出一个异常
            return false;
        } else {
            //如果不存在在此索引则添加
            //第一步，检查空间是否足够，不够则追加
            if (size == data.length) {
                int[] array = new int[size + increment];
                System.arraycopy(data, 0, array, 0, size);
                data = array;

            }
            //第二步，添加索引并让size加1
            data[size] = index;
            size++;
            return true;
        }
    }

    /**
     * 删除指定的索引
     * @param index
     */
    public synchronized boolean remove(int index) {
        if (!has(index)) {
            return false;
        } else {
            //第一步找到要删除的索引的下标
            int i = 0;
            for (i = 0; i < size; i++) {
                if (data[i] == index) {
                    break;
                }
            }
            //(i+1)----(size-1)的元素前移
            for (int j = i + 1; j < size; j++) {
                data[j - 1] = data[j];
            }
            //清除size-1位置的元素
            data[size - 1] = -1;
            //size减1
            size--;
            return true;
        }
    }

    public boolean has(int index) {
        boolean judge = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == index) {
                judge = true;
                break;
            }
        }
        return judge;
    }

    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = -1;
        }
        size = 0;
    }

    public int get(int index) {
        if (index < 0 | index > data.length) {
            return -1;
        }
        return data[index];
    }
}
