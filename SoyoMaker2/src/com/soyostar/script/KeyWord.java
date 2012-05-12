/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.script;

import javax.swing.text.*;

/**
 *  �ؼ��ֱ�
 * @author Administrator
 */
public class KeyWord {

    /**
     *
     */
    public KeyWord() {
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
    /**
     *
     */
    public static String[] KEYWORDS;
}