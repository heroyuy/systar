/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import emulator.EmulatorImage;


/**
 * 帧
 */
public class Frame {

    public int num = 0;//帧在动画源图片中的位置编号
    public EmulatorImage image = null;//帧的图像
    public int offsetX = 0;//帧相对于动画基准点在x方向上的偏移量
    public int offsetY = 0;//帧相对于动画基准点在y方向上的偏移量
}
