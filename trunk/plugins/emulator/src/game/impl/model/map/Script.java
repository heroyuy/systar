/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model.map;

import game.AbModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Script  extends AbModel{

    public static final byte CONTACTPARALLEL = 1;
    public static final byte SERIALACCESS = 2;
    public static final byte PARALLELlKEY = 3;
    public static final byte SERIALKEY = 4;
    public static final byte UP = 0;
    public static final byte DOWN = 1;
    public static final byte LEFT = 2;
    public static final byte RIGHT = 3;
    public static final byte FIXED = 0;  //固定
    public static final byte RANDOW = 1; //随机
    public static final byte NEAR = 2;   //接近
    public static final byte FAST = 0;   //快
    public static final byte MIDDLE = 1; //中
    public static final byte SLOW = 2;   //慢
    public byte type = 0;              //脚本类型
    public String imgPath = "";        //行走图路径
    public int row = 0;                //脚本行号
    public int col = 0;                //脚本列号
    public byte face = 0;              //初始面向
    public byte move = 0;              //移动规则
    public byte speed = 0;             //移动速度
    public ArrayList<String> codes = null;

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
