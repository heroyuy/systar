package game.system;

import emulator.EmulatorGraphics;
import emulator.EmulatorImage;
import java.awt.Color;
import java.util.Vector;
import game.impl.model.Const;
import game.impl.model.GameData;
import game.manager.ImageManager;
/*
 * 功能：
 * 1 drawString
 * 2 fillRect
 * 3 drawRect
 * 4 drawRoundRect
 * 5 fillRoundRect
 * 6 画三角
 * 7 绘制对话框
 * 8 绘制滚动条
 * 9 分割字符串
 * 10 半透明图片
 * 11 调整图片大小
 * 12 绘制图片数字
 * 13 绘制表格
 * 14 滚动文字
 */

/**
 *
 * 绘图工具包
 */
public class Painter {

    public static final byte DIALOG_DEEP = 0;
    public static final byte DIALOG_LIGHT = 1;
    public static final byte NODIALOG = 2;
    public static final byte SCROLLBAR_VERTICAL = 0;
    public static final byte SCROLLBAR_HORIZONTAL = 1;
    public static final byte CELL_DEEP = 0;
    public static final byte CELL_LIGHT = 1;

    public static void drawString(EmulatorGraphics g, String str, int x, int y, Color color) {
        g.setColor(color);
        g.drawString(str, x, y, EmulatorGraphics.LT);
    }

    public static void fillRect(EmulatorGraphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public static void drawRect(EmulatorGraphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    public static void drawRoundRect(EmulatorGraphics g, int x, int y, int width, int height, int anchor, Color color) {
        g.setColor(color);
        g.drawRoundRect(x, y, width, height, anchor, anchor);
    }

    public static void fillRoundRect(EmulatorGraphics g, int x, int y, int width, int height, int anchor, Color color) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, anchor, anchor);
    }

    public static void drawTriangle(EmulatorGraphics g, int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }

    /**
     * 在指定位置绘制指定大小的对话框
     * @param g 画笔
     * @param x x坐标
     * @param y y坐标
     * @param w 宽度
     * @param h 高度
     * @param type 类型，为Painter.DIALOG_DEEP时画深色框 其它则画浅色框
     */
    public static void drawDialog(EmulatorGraphics g, int x, int y, int w, int h, byte type) {
        if (type == Painter.DIALOG_DEEP || type == Painter.DIALOG_LIGHT) {
            EmulatorImage img = ImageManager.getInstance().getImage(type == Painter.DIALOG_DEEP ? Const.ImagePath.DIALOG_DEEP : Const.ImagePath.DIALOG_LIGHT);
            drawRectFrame(g, img, x, y, w, h);
        } else {
            System.out.println("参数type不对，只能为0或1");
        }
    }

    /**
     * 绘制滚动条
     * @param g 画笔
     * @param style 风格 可取  Painter.SCROLLBAR_HORIZONTAL 水平  Painter.SCROLLBAR_VERTICAL 竖直
     * @param a 如果是水平风格则是滚动条y坐标，竖直风格则是x坐标
     * @param b1 如果是水平风格则是滚动条起始x坐标，竖直风格则是起始y坐标
     * @param b2 如果是水平风格则是滚动条终止x坐标，竖直风格则是终止y坐标 注：b2>b1
     * @param topIndex 显示的第一个条目的索引
     * @param showNum 一屏可以显示的条目数量
     * @param maxNum 总共要显示的条目数量
     */
    public static void drawScrollbar(EmulatorGraphics g, byte style, int a, int b1, int b2, int topIndex, int showNum, int maxNum) {
        if (b2 <= b1 || maxNum <= showNum) {
            return;
        }
        ImageManager im = ImageManager.getInstance();
        EmulatorImage trench = im.getImage(Const.ImagePath.TRENCH);
        trench = EmulatorImage.createImage(trench, trench.getWidth() / 2, 0, trench.getWidth() / 2, trench.getHeight(), 6);
        EmulatorImage triangle = im.getImage(Const.ImagePath.SMALLTRIANGLE);
        EmulatorImage ball = im.getImage(Const.ImagePath.BALL);
        switch (style) {
            case Painter.SCROLLBAR_VERTICAL: {

                int hei = b2 - b1;
                int wid = triangle.getWidth();

                //1、绘制条状底色
                g.setClip(a, b1 + triangle.getHeight(), wid, hei - 2 * triangle.getHeight());
                int num = hei / trench.getHeight() + 1;
                for (int i = 0; i < num; i++) {
                    g.drawImage(trench, a + wid / 2, b1 + i * trench.getHeight(), EmulatorGraphics.HT);
                }
                g.setClip(a, b1, wid, hei);
                //2、绘制两个三角
                g.drawImage(triangle, a, b1, 0);
                g.drawRegion(triangle, 0, 0, triangle.getWidth(), triangle.getHeight(), 1, a, b1 + hei - triangle.getHeight(), 0);

                //3、绘制小球
                if (maxNum > showNum) {
                    if (topIndex == 0) {
                        g.drawImage(ball, a + wid / 2, b1 + triangle.getHeight(), Const.Anchor.HT);
                    } else if (topIndex == maxNum - showNum) {
                        g.drawImage(ball, a + wid / 2, b1 + hei - triangle.getHeight() - ball.getHeight(), Const.Anchor.HT);
                    } else {
                        g.drawImage(ball, a + wid / 2, b1 + triangle.getHeight() + (hei - 2 * triangle.getHeight()) / (maxNum - showNum) * topIndex, Const.Anchor.HT);
                    }
                }
            }
            break;
            case Painter.SCROLLBAR_HORIZONTAL: {

                int wid = b2 - b1;
                int hei = triangle.getWidth();

                //1、绘制条状底色
                g.setClip(b1 + triangle.getHeight(), a, wid - 2 * triangle.getHeight(), hei);
                int num = wid / trench.getHeight() + 1;
                for (int i = 0; i < num; i++) {
                    g.drawRegion(trench, 0, 0, trench.getWidth(), trench.getHeight(), 5, b1 + i * trench.getHeight(), a + hei / 2, Const.Anchor.LV);
                }
                g.setClip(b1, a, wid, hei);
                //2、绘制两个三角
                g.drawRegion(triangle, 0, 0, triangle.getWidth(), triangle.getHeight(), 4, b1, a, 0);
                g.drawRegion(triangle, 0, 0, triangle.getWidth(), triangle.getHeight(), 5, b1 + wid - triangle.getHeight(), a, 0);

                //3、绘制小球
                if (maxNum > showNum) {
                    if (topIndex == 0) {
                        g.drawImage(ball, b1 + triangle.getHeight(), a + hei / 2, Const.Anchor.LV);
                    } else if (topIndex == maxNum - showNum) {
                        g.drawImage(ball, b1 + wid - triangle.getHeight() - ball.getWidth(), a + hei / 2, Const.Anchor.LV);
                    } else {
                        g.drawImage(ball, b1 + triangle.getHeight() + (wid - 2 * triangle.getHeight()) / (maxNum - showNum) * topIndex, a + hei / 2, Const.Anchor.LV);
                    }
                }
            }
            break;
        }
        g.setClip(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);

    }

    /**
     *
     * 分割字符串，原理：检测字符串中的分割字符串，然后取子串
     *
     * @param original
     *            需要分割的字符串
     *
     * @paran regex 分割字符串
     *
     * @return 分割后生成的字符串数组
     *
     */
    public static String[] split(String original, String regex) {
        // 取子串的起始位置
        int startIndex = 0;
        // 将结果数据先放入Vector中
        Vector v = new Vector();
        // 返回的结果字符串数组
        String[] str = null;
        // 存储取子串时起始位置
        int index = 0;
        // 获得匹配子串的位置
        startIndex = original.indexOf(regex);
        // 如果起始字符串的位置小于字符串的长度，则证明没有取到字符串末尾。
        // -1代表取到了末尾
        while (startIndex < original.length() && startIndex != -1) {
            String temp = original.substring(index, startIndex);
            // 取子串
            v.addElement(temp);
            // 设置取子串的起始位置
            index = startIndex + regex.length();
            // 获得匹配子串的位置
            startIndex = original.indexOf(regex, startIndex + regex.length());
        }

        // 取结束的子串

        v.addElement(original.substring(index + 1 - regex.length()));
        // 将Vector对象转换成数组
        str = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            str[i] = (String) v.elementAt(i);
        }
        // 返回生成的数组
        return str;

    }

    /**
     * 在指定位置绘制指定大小的单元格
     * @param g 画笔
     * @param x x坐标
     * @param y y坐标
     * @param w 宽度
     * @param h 高度
     * @param type 类型，为Painter.CELL_DEEP时画深色框 其它则画浅色框
     */
    public static void drawCell(EmulatorGraphics g, int x, int y, int w, int h, byte type) {
        EmulatorImage img = ImageManager.getInstance().getImage(type == Painter.CELL_DEEP ? Const.ImagePath.CELL_DEEP : Const.ImagePath.CELL_LIGHT);
        drawRectFrame(g, img, x, y, w, h);
    }

    public static void drawStringOnCell(EmulatorGraphics g, String text, Color color, int x, int y, int w, int h, int anchor, byte type) {
        drawCell(g, x, y, w, h, type);
        if (text == null || text.equals("")) {
            return;
        }
        g.setColor(color);
        int gap = 3;//文字与边框的间距
        switch (anchor) {
            case Const.Anchor.LT:
                g.drawString(text, x + gap, y, anchor);
                break;
            case Const.Anchor.LV:
                g.drawString(text, x + gap, y + (h - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.LT);
                break;
            case Const.Anchor.LB:
                g.drawString(text, x + gap, y + h, anchor);
                break;
            case Const.Anchor.HT:
                g.drawString(text, x + w / 2, y, anchor);
                break;
            case Const.Anchor.HV:
                g.drawString(text, x + w / 2, y + (h - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.HT);
                break;
            case Const.Anchor.HB:
                g.drawString(text, x + w / 2, y + h, anchor);
                break;
            case Const.Anchor.RT:
                g.drawString(text, x + w - gap, y, anchor);
                break;
            case Const.Anchor.RV:
                g.drawString(text, x + w - gap, y + (h - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.RT);
                break;
            case Const.Anchor.RB:
                g.drawString(text, x + w - gap, y + h, anchor);
                break;
        }

    }

    /**
     * 绘制表格
     * @param g 画笔
     * @param x 起始x坐标
     * @param y 起始y坐标
     * @param cellWidth 单元格宽度
     * @param cellHeight 单元格高度
     * @param cellNum 单元格数量
     * @param gap 单元格间距
     * @param texts 内容
     * @param color 文本颜色
     * @param topIndex 第一条要显示的信息的索引
     * @param curIndex 当前选中的信息的索引
     * @param anchor 锚点
     * @param type 类型，为Painter.CELL_DEEP时选中框为深色框 其它则画浅色框
     */
    public static void drawTable(EmulatorGraphics g, int x, int y, int cellWidth, int cellHeight, int cellNum, int gap, String[] texts, Color color, int topIndex, int curIndex, int anchor, byte style, byte type) {
        if (style != NODIALOG) {//不为0,1时不画对话框
            drawDialog(g, x, y, cellWidth + 2 * gap, cellNum * (cellHeight + gap) + gap, style);
        }

        for (int i = 0; i < cellNum; i++) {
            drawStringOnCell(g, i + topIndex < texts.length ? texts[i + topIndex] : null, color, x + gap, y + (cellHeight + gap) * i + gap, cellWidth, cellHeight, anchor, i + topIndex == curIndex ? (type == Painter.CELL_DEEP ? Painter.CELL_DEEP : Painter.CELL_LIGHT) : (type == Painter.CELL_DEEP ? Painter.CELL_LIGHT : Painter.CELL_DEEP));
        }
    }

    public static void drawTab(EmulatorGraphics g, int x, int y, int w, int h, byte type, int curIndex, String[] titles) {
        //绘制所有耳朵
        int wid = 48, hei = 24;
        for (int i = 0; i < titles.length; i++) {
            drawDialog(g, x + i * wid, y, wid, hei, type == DIALOG_DEEP ? DIALOG_LIGHT : DIALOG_DEEP);
        }
        //绘制选中的耳朵
        drawDialog(g, x + curIndex * wid, y, wid, hei, type == DIALOG_DEEP ? DIALOG_DEEP : DIALOG_LIGHT);
        //绘制内容框
        drawDialog(g, x, y + hei, w, h - hei, type == DIALOG_DEEP ? DIALOG_DEEP : DIALOG_LIGHT);
        //绘制标题
        g.setEmulatorFont(Const.Font.FONTSMALL_PLAIN);
        for (int i = 0; i < titles.length; i++) {
            g.drawString(titles[i], x + i * wid + wid / 2, y + (hei - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.HT);
        }
    }

    public static void drawRectFrame(EmulatorGraphics g, EmulatorImage img, int x, int y, int w, int h) {
        g.setClip(x, y, w, h);
        int rowNum = h / (img.getHeight() / 2) + 1;
        int colNum = w / (img.getWidth() / 2) + 1;
        //绘制底色
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                g.drawRegion(img, img.getWidth() / 2, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 0, x + img.getWidth() / 2 * i, y + img.getHeight() / 2 * j, 0);
            }
        }
        //绘制边框 [先水平，后竖直]
        for (int i = 0; i < colNum; i++) {
            g.drawRegion(img, img.getWidth() / 2, 0, img.getWidth() / 2, img.getHeight() / 2, 0, x + img.getWidth() / 2 * i, y, 0);
            g.drawRegion(img, img.getWidth() / 2, 0, img.getWidth() / 2, img.getHeight() / 2, 1, x + img.getWidth() / 2 * i, y + h - img.getHeight() / 2, 0);
        }
        for (int i = 0; i < rowNum; i++) {
            g.drawRegion(img, 0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 0, x, y + img.getHeight() / 2 * i, 0);
            g.drawRegion(img, 0, img.getHeight() / 2, img.getWidth() / 2, img.getHeight() / 2, 3, x + w - img.getWidth() / 2, y + img.getHeight() / 2 * i, 0);

        }
        //四个角
        g.drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 0, x, y, 0);
        g.drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 2, x + w - img.getWidth() / 2, y, 0);
        g.drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 1, x, y + h - img.getHeight() / 2, 0);
        g.drawRegion(img, 0, 0, img.getWidth() / 2, img.getHeight() / 2, 3, x + w - img.getWidth() / 2, y + h - img.getHeight() / 2, 0);


        g.setClip(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);
    }
    /*
     * 图片镜像特效
     */

    public static EmulatorImage effect_mirror(EmulatorImage src) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();
        int[] srcPixels = getPixels(src);
        int len;
        int temp;
        for (int i = 0; i < srcH; i++) {
            len = (i + 1) * srcW;
            for (int ii = 0; ii < srcW / 2; ii++) {
                temp = srcPixels[i * srcW + ii];
                srcPixels[i * srcW + ii] = srcPixels[len - 1 - ii];
                srcPixels[len - 1 - ii] = temp;
            }
        }
        return drawPixels(srcPixels, srcW, srcH);
    }

    /*******************************下面是各个图片处理函数***********************************/

    /*
     * 获取图片RGB数据，并返回大小为width*height大小的一维数组
     */
    public static int[] getPixels(EmulatorImage src) {
        int w = src.getWidth();
        int h = src.getHeight();
        int[] pixels = new int[w * h];
        src.getRGB(pixels, 0, w, 0, 0, w, h);
        return pixels;
    }
    /*
     *将pixels[]里的数据，生成一张图片，图片宽为w，高为h
     */

    public static EmulatorImage drawPixels(int[] pixels, int w, int h) {
        EmulatorImage image = EmulatorImage.createRGBImage(pixels, w, h, true);
        pixels = null;
        return image;
    }
    /*
     *调整图片大小
     *destW 调整后的宽，destH调整后的高
     */

    public static EmulatorImage effect_resizeImage(EmulatorImage src, int destW, int destH) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();
        int[] destPixels = new int[destW * destH];
        int[] srcPixels = getPixels(src);
        for (int destY = 0; destY < destH; ++destY) {
            for (int destX = 0; destX < destW; ++destX) {
                int srcX = (destX * srcW) / destW;
                int srcY = (destY * srcH) / destH;
                destPixels[destX + destY * destW] = srcPixels[srcX + srcY * srcW];
            }
        }
        return drawPixels(destPixels, destW, destH);
    }

    /**
     *
     * @param img
     *            原始图片
     * @param transparent
     *            透明度 0-255之间
     * 越大越不透明，只半透明不透明的部分
     * @return 处理透明度后的图片
     */
    public static EmulatorImage effect_transparent_Other(EmulatorImage img, int transparent) {
        if (transparent < 0 || transparent > 255) {
            return img;
        }
        int srcW = img.getWidth();
        int srcH = img.getHeight();
        int[] srcPixels = getPixels(img); //函数功能 讲图片数据存入指定数组
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int argb;
        for (int i = 0; i < srcH; i++) {
            for (int ii = 0; ii < srcW; ii++) {
                argb = srcPixels[i * srcW + ii];
                a = ((argb & 0xff000000) >> 24); // alpha channel
                r = ((argb & 0x00ff0000) >> 16); // red channel
                g = ((argb & 0x0000ff00) >> 8); // green channel
                b = (argb & 0x000000ff); // blue channel
                if (a != 0) {
                    srcPixels[i * srcW + ii] = ((transparent << 24) | (r << 16) | (g << 8) | b);
                } else {
                    srcPixels[i * srcW + ii] = 0x00ffffff;
                }
            }
        }
        return drawPixels(srcPixels, srcW, srcH); //将数组转化为图片
    }
    public static int tipStringPos = 240 / 2;   //当前字符串左边的位置 这里改为屏幕中间位子
    private static int tipStringSpeed = 3;         //字符串移动速度
//    private static final int FONT_HEIGHT = 11;      //字体高度 font.getHeight() 不准确

    public static void resetTipStringPos() {
        tipStringPos = 240 / 2;
    }

    /**		滚动文字效果
     * @param Graphics g - 画刷
     * @param String str - 所画字符串
     * @param int height - 字符串高度
     * @param int rectX - 剪裁区顶点X坐标
     * @param int rectY - 剪裁区顶点Y坐标
     * @param int rectWidth - 剪裁区宽度
     * @param int rectHeight - 剪裁区高度
     * TIPSTR_LEFT //左边消失绘制坐标
     * TIPSTR_RIGHT //右边出现绘制坐标
     */
    public static void drawTipString(EmulatorGraphics g, String str, int height, int TIPSTR_LEFT, int TIPSTR_RIGHT, int rectX, int rectY, int rectWidth, int rectHeight, Color color) {
        g.setColor(color);
        int strWidth = g.getEmulatorFont().stringWidth(str);
        int strHeight = g.getEmulatorFont().getHeight();
        if (strWidth < rectWidth) {
            g.drawString(str, rectX, height - strHeight / 2, EmulatorGraphics.LT);
            return;
        }
        tipStringPos -= tipStringSpeed;
        if (tipStringPos + strWidth < TIPSTR_LEFT) {
            tipStringPos = TIPSTR_RIGHT;
        }
        //裁减区
        int oldClipX = g.getClipX();
        int oldClipY = g.getClipY();
        int oldClipWidth = g.getClipWidth();
        int oldClipHeight = g.getClipHeight();
        g.setClip(rectX, rectY, rectWidth, rectHeight);
        g.drawString(str, tipStringPos, height - strHeight / 2, EmulatorGraphics.LT);
        g.setClip(oldClipX, oldClipY, oldClipWidth, oldClipHeight);

    }

    public static void drawImage(EmulatorImage src, int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor, EmulatorGraphics g) {
        g.drawRegion(src, x_src, y_src, width, height, 0, x_dest, y_dest, anchor);
    }
    private static int width;
    private static int height;
    private static char cha;
    public static int REDNUM = 0;
    public static int GREENNUM = 1;

    /**
     * 画数字图片
     *
     * @param imgNumber
     * @param number
     * @param x
     * @param y
     * @param g
     * @param type
     * @param atype 0 红色图片 1 绿色图片
     */
    public static void drawNumber(EmulatorGraphics g, int number, int x, int y, int w, int type, int atype) {
        ImageManager im = ImageManager.getInstance();
        EmulatorImage imgNumber = im.getImage(Const.ImagePath.NUMS);
        width = imgNumber.getWidth() / w;
        height = imgNumber.getHeight() / 2;
        String strNmber = Integer.toString(number);
        char[] chrNumr = strNmber.toCharArray();
        cha = 0;
        if (type == NUMBER_LEFT) {
            x -= (chrNumr.length - 1) * width;
        } else if (type == NUMBER_UP) {
            y -= chrNumr.length * height;
        }
        for (int i = 0; i < chrNumr.length; i++) {
            cha = chrNumr[i];
            switch (cha) {
                case '0':
                    drawImage(imgNumber, 0 * width, atype * height, width, height, x, y, 0,
                        g);
                    break;
                case '1':
                    drawImage(imgNumber, 1 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '2':
                    drawImage(imgNumber, 2 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '3':
                    drawImage(imgNumber, 3 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '4':
                    drawImage(imgNumber, 4 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '5':
                    drawImage(imgNumber, 5 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '6':
                    drawImage(imgNumber, 6 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '7':
                    drawImage(imgNumber, 7 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '8':
                    drawImage(imgNumber, 8 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
                case '9':
                    drawImage(imgNumber, 9 * width, atype * height, width, height, x, y,
                        0, g);
                    break;
            }
            switch (type) {
                case NUMBER_LEFT:
                case NUMBER_RIGHT:
                    x += width;
                    break;
                case NUMBER_UP:
                case NUMBER_DOWN:
                    y += height;
                    break;
            }

        }
        strNmber = null;
        chrNumr = null;
//        System.gc();
    }
    /**
     * 第一文字排版
     */
    public final static int NUMBER_LEFT = 1;
    public final static int NUMBER_RIGHT = 2;
    public final static int NUMBER_UP = 3;
    public final static int NUMBER_DOWN = 4;

    /**
     * 在指定区域绘制自动换行的文本
     * @param g 画笔
     * @param str 要绘制的字符串
     * @param x  起始x坐标
     * @param y  起始y坐标
     * @param wid 区域宽度
     * @param hei 区域高度
     * @param color 文本颜色
     */
    public static void drawWordWrapString(EmulatorGraphics g, String str, int x, int y, int wid, int hei, Color color) {
        g.setClip(x, y, wid, hei);
        g.setColor(color);
        char[] txt = str.toCharArray();
        for (int i = 0, col = 0, line = 0; i < txt.length; i++) {
            g.drawChar(txt[i], x + col * (g.getEmulatorFont().charWidth(txt[0]) + 1), y + line * (g.getEmulatorFont().getHeight()), EmulatorGraphics.LT);
            if ((col + 1) * (g.getEmulatorFont().charWidth(txt[0]) + 1) >= wid) {
                col = 0;
                line++;
            } else {
                col++;
            }

        }
        g.setClip(0, 0, GameData.getGameData().screenWidth, GameData.getGameData().screenHeight);

    }

}
