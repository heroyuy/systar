/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.script;

import javax.swing.text.*;

/**
 *  �ؼ��ֱ�
 * @author Administrator
 */
public class KeyWord {

    /**
     *
     */
    private KeyWord() {
    }

    /**
     *
     * @param seg
     * @return
     */
    public static boolean isKeyWord(Segment seg) {
        boolean isKey = false;
        for (int i = 0; !isKey && i < KEYWORDS.length; i++) {
            if (seg.count == KEYWORDS[i].length()) {
                isKey = true;
                for (int j = 0; isKey && j < seg.count; j++) {
                    if (seg.array[seg.offset + j] != KEYWORDS[i].charAt(j)) {
                        isKey = false;
                    }
                }

            }
        }
        return isKey;
    }
    /**
     *
     */
    public static final String LUACOMMENT = "--";
    /**
     *
     */
    public static final String JAVACOMMENT = "//";
    /**
     *
     */
    public static String COMMENT = "--";//暂时只支持长度为2的注释
    /**
     *
     */
    public static String[] KEYWORDS;
    /**
     *
     */
    public static final String[] LUAKEYWORDS = {
        "and",
        "break",
        "do",
        "else", "elseif", "end",
        "false", "for", "function",
        "if", "in",
        "local",
        "nil", "not",
        "or",
        "repeat", "return",
        "then", "true",
        "until",
        "while"
    };
    /**
     *
     */
    public static final String[] JAVAKEYWORDS = {
        "abstract",
        "boolean", "break", "byte",
        "case", "catch", "char", "class", "const", "continue",
        "default", "do", "double",
        "else", "extends",
        "final", "finally", "float", "for", "false",
        "goto",
        "if", "implements", "import", "instanceof", "int", "interface",
        "long",
        "native", "new",
        "package",
        "private", "protected", "public",
        "return",
        "short", "static", "strictfp", "super", "switch", "synchronized",
        "this", "throw", "throws", "transient", "try",
        "void", "volatile",
        "while",
        "true"
    };//java keyword
}
