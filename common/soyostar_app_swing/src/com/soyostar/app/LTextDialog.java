/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Voln
 */
public class LTextDialog extends Widget {

    private static List<String> texts = new ArrayList<String>();
    private int totalOffsetY = 0;//总偏移量
    private int curOffsetY = 0;//当前偏移量
    private int wight = 40;
    private int height = 30;
    private int marginTop = 0;
    private int marginBottom = 0;
    private int marginLeft = 0;
    private int marginRight = 0;
    private int textColor = 0xff000000;
    private int leading = 0;//行距
    private boolean needUpdate = false;
    private static List<String> textsTemp = new ArrayList<String>();

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getLeading() {
        return leading;
    }

    public void setLeading(int leading) {
        this.leading = leading;
    }

    public void setMargin(int marginTop, int marginBottom, int marginLeft, int marginRight) {
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        needUpdate = true;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        needUpdate = true;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        needUpdate = true;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public LTextDialog(String text) {
        texts.add(text);
        String regex = "<c>\\[0x[0-9a-fA-F]{8}\\]";
        change(regex);
        regex = "<d>\\[[0-9]{11}\\]";
        change(regex);
        needUpdate = true;
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        if (needUpdate) {
            analyseLine(painter);
            //for (String str : texts) {
            //   System.out.println(str);
            //}



            curOffsetY = 0;
            totalOffsetY = texts.size() * painter.getTextSize() + (texts.size() - 1) * leading - (getHeight() - marginTop - marginBottom);
            needUpdate = false;
        }
        //painter.setClip(0, 0, getWidth(), getHeight() - marginBottom);
        for (int i = 0; i < texts.size(); i++) {
            //行数
            int lineNum = 0;
            char ch = 0;
            String regex = "<[0-9a-zA-Z]{1}>";
            Pattern compiler = Pattern.compile(regex);
            Matcher matcher = compiler.matcher(texts.get(i));
            if (matcher.find()) {
                ch = getType(texts.get(i));
            }

            switch (ch) {
                case 'c':
                    String color = getContents(texts.get(i));
                    System.out.println(color);
                    System.out.println("梁鸿博");
                    //int Icolcor = Integer.parseInt(color, 0x10);
                    //painter.setColor(Icolcor);
                    break;
                case 'd':

                    break;
                case 'n':
                    lineNum++;

                    break;
                default:

                    painter.drawString(texts.get(i), marginLeft, marginTop + lineNum * (painter.getTextSize() + leading) + curOffsetY,
                            Painter.LT);
                //输入其它字符时提示出错
            }
            //painter.setClip(0, 0, getWidth(), getHeight());
        }
    }

    private char getType(String text) {
        text = text.substring(0, 3);
        char ch = 0;
        String regex = "<[0-9a-zA-Z]{1}>";
        Pattern compiler = Pattern.compile(regex);
        Matcher matcher = compiler.matcher(text);
        if (matcher.find()) {
            String res = matcher.group();
            ch = res.charAt(1);
        }
        System.out.println(ch);
        return ch;
    }

    private String getContents(String text) {
        text = text.substring(4);
        String content = "";
        String regex = "[0-9a-zA-Z]+";
        Pattern compiler = Pattern.compile(regex);
        Matcher matcher = compiler.matcher(text);
        if (matcher.find()) {
            String res = matcher.group();
            content = res;
        }
        return content;
    }

    private void change(String regex) {
        List<String> texts_temp = new ArrayList<String>();
        Pattern compiler = Pattern.compile(regex);
        for (String text : texts) {
            Matcher matcher = compiler.matcher(text);
            String[] strs = text.split(regex);
            if (strs[0] == null || strs[0].equals("")) {
            } else {
                texts_temp.add(strs[0]);
            }
            int i = 1;
            while (matcher.find()) {
                if (matcher.group() == null || matcher.group().equals("")) {
                } else {

                    texts_temp.add(matcher.group());
                }
                if (strs[i] == null || strs[i].equals("")) {
                } else {
                    texts_temp.add(strs[i]);
                }
                i++;
            }
        }
        texts.clear();
        for (String text : texts_temp) {
            texts.add(text);
        }

    }

    private void analyseLine(Painter painter) {
        String strsremain = "";
        List<String> texts_temp = new ArrayList<String>();
        String regex = "<[0-9a-zA-Z]{1}>";
        Pattern compiler = Pattern.compile(regex);
        for (String text : texts) {
            Matcher matcher = compiler.matcher(text);
            if (matcher.find()) {
                texts_temp.add(text);
            } else {
                int index = text.length();
                //第一种可能，没有保留字符串的情况下。
                if (strsremain.equals("")) {
                    String temp = text.substring(0, index);
                    //第一种可能，字符串短于屏幕。
                    if (painter.stringWidth(temp) < getWidth() - marginLeft - marginRight) {
                        texts_temp.add(temp);
                        strsremain = text;
                        //第二种情况，大于或者等于屏幕，必须使用循环将之一段一段的截取。
                    } else {
                        textsTemp.clear();
                        analyse(painter, text);
                        for (String strs : textsTemp) {
                            texts_temp.add(strs);
                            texts_temp.add("<n>[]");
                        }
                        int size = texts_temp.size();
                        strsremain = texts_temp.get(size - 2).toString();
                        texts_temp.remove(size - 1);
                    }
                    //第二种可能，保留字符串不是空的情况下
                } else {
                    //让暂时保留字符串为保留字符串及当前字符串相加的字符串
                    String temp = strsremain + text.substring(0, index);
                    //第一中可能，就是保留字符串加上当前字符串还是不够一样的情况下
                    if (painter.stringWidth(temp) < getWidth() - marginLeft - marginRight) {
                        texts_temp.add(text);
                        strsremain = temp;
                        //第二种情况，加上保留字符串后大于当前屏幕
                    } else {
                        while (index > 0) {
                            temp = strsremain + text.substring(0, index);
                            if (painter.stringWidth(temp) > getWidth() - marginLeft - marginRight) {
                                index--;
                            } else {
                                texts_temp.add(text.substring(0, index));
                                texts_temp.add("<n>[]");
                                text = text.substring(index);
                                break;
                            }
                        }
                        textsTemp.clear();
                        analyse(painter, text);
                        for (String strs : textsTemp) {
                            texts_temp.add(strs);
                            texts_temp.add("<n>[]");
                            int size = texts_temp.size();
                            strsremain = texts_temp.get(size - 2).toString();
                            texts_temp.remove(size - 1);
                        }
                    }

                }


            }
        }
        texts.clear();
        for (String text : texts_temp) {
            texts.add(text);
        }

    }

    private void analyse(Painter painter, String text) {
        if (text == null || text.equals("")) {
            return;
        }
        int index = text.length();
        while (index > 0) {
            String temp = text.substring(0, index);
            if (painter.stringWidth(temp) > getWidth() - marginLeft - marginRight) {
                index--;
            } else {
                textsTemp.add(temp);
                if (index < text.length()) {
                    analyse(painter, text.substring(index));
                }
                break;
            }
        }
    }

    public static void main(String[] args){
        System.out.println(Integer.parseInt("ff", 16));
    }
 }
