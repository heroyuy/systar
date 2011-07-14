/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class LTextDialog extends Widget {

    private String text = "";
    private int textColor = Color.BLACK;
    private int textSize = 12;
    private boolean needUpdate = false;
    private List<String> showTexts = new ArrayList<String>();
    private List<String> functionTexts = new ArrayList<String>();

    public LTextDialog(String text) {
        this.text = text;
        needUpdate = true;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        needUpdate = true;
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        needUpdate = true;
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    @Override
    public void paint(Painter painter) {
        if (needUpdate) {
            analyse(text);
            needUpdate = false;
        }
        super.paint(painter);
        painter.setColor(textColor);
        int curRow = 0;//绘制光标当前所在行
        int curX = 0;//绘制光标当前所在横坐标
        for (int i = 0; i < showTexts.size(); i++) {
            String temp = showTexts.get(i);
            //获取显示字符串
            int index = 0;
            while (index < temp.length()) {
                String curStr = temp.substring(index, index + 1);
                if (curStr.equals("\n") || curStr.equals("\r")) {
                    curRow++;
                    curX = 0;
                    index++;
                    continue;
                }
                if (curX + painter.stringWidth(curStr) >= getWidth()) {
                    curRow++;
                    curX = 0;
                }
                painter.drawString(curStr, curX, curRow * painter.getTextSize(), Painter.LT);
                curX += painter.stringWidth(curStr);
                index++;
            }
            if (i < showTexts.size() - 1) {
                //从功能字符串中取一个串并解析
                executeFunction(functionTexts.get(i), painter);
            }
        }
    }

    private void analyse(String text) {
        //1.提取所有显示字符串
        String[] strs = text.split("<c>\\[0x[0-9a-zA-Z]{8}\\]");
        for (String str : strs) {
            showTexts.add(str);
        }
        //2.提取所有功能字符串
        Pattern pattern = Pattern.compile("<c>\\[0x[0-9a-zA-Z]{8}\\]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String functionText = matcher.group();
            functionTexts.add(functionText);
        }
    }

    private void executeFunction(String functionText, Painter painter) {
        //1.获取功能部分
        String fucntion = functionText.substring(functionText.indexOf("<") + 1, functionText.lastIndexOf(">"));
        //2.获取参数
        String param = functionText.substring(functionText.indexOf("[") + 1, functionText.lastIndexOf("]"));
        if (fucntion.equals("c")) {
            //颜色
            int a = Integer.parseInt(param.substring(2, 4), 16);
            int r = Integer.parseInt(param.substring(4, 6), 16);
            int g = Integer.parseInt(param.substring(6, 8), 16);
            int b = Integer.parseInt(param.substring(8, 10), 16);
            painter.setColor(Color.getColor(a, r, g, b));
        }
    }
}
