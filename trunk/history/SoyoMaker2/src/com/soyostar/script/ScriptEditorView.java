/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyostar.script;

import java.awt.*;
import javax.swing.text.*;

/**
 *
 * @author Administrator
 */
public class ScriptEditorView extends PlainView {

    /**
     *
     * @param element
     */
    public ScriptEditorView(Element element) {
        super(element);
    }

    @Override
    @SuppressWarnings({"empty-statement", "empty-statement"})
    protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1)
            throws BadLocationException {
        Document doc = getDocument();
        Segment segment = new Segment(), token = new Segment();
        int index = 0, count = p1 - p0;
        char c = '\u0000';

        doc.getText(p0, count, segment);
        for (int i = 0; i < count; i++) {
            if (Character.isLetter(c = segment.array[segment.offset + i])) {
                index = i;
                while (++i < count && Character.isLetter(segment.array[segment.offset + i]));
                doc.getText(p0 + index, (i--) - index, token);
                if (KeyWord.isKeyWord(token)) {
                    g.setFont(KEYWORDFONT);
                    g.setColor(KEYWORDCOLOR);
                } else {
                    g.setFont(TEXTFONT);
                    g.setColor(TEXTCOLOR);
                }
                x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                continue;
            } else if (c == '/') {
                index = i;
                if (++i < count && segment.array[segment.offset + i] == '/') {
                    doc.getText(p0 + index, count - index, token);
                    g.setFont(COMMENTFONT);
                    g.setColor(COMMENTCOLOR);
                    x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                    break;
                }
                doc.getText(p0 + index, 1, token);
                g.setFont(TEXTFONT);
                g.setColor(TEXTCOLOR);
                x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                i--;
                continue;
            } else if (c == '\'' || c == '\"') {
                index = i;
                char ch = '\u0000';
                while (++i < count) {
                    if ((ch = segment.array[segment.offset + i]) == '\\') {
                        i++;
                        continue;
                    } else if (ch == c) {
                        break;
                    }
                }
                if (i >= count) {
                    i = count - 1;
                }
                doc.getText(p0 + index, i - index + 1, token);
                g.setFont(STRINGFONT);
                g.setColor(STRINGCOLOR);
                x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
                continue;
            } else {
                index = i;
                while (++i < count && !Character.isLetter((c = segment.array[segment.offset + i])) && c != '/' && c != '\'' && c != '\"');
                doc.getText(p0 + index, (i--) - index, token);
                g.setFont(TEXTFONT);
                g.setColor(TEXTCOLOR);
                x = Utilities.drawTabbedText(token, x, y, g, this, p0 + index);
            }
        }
        return x;
    }

    @Override
    protected int drawSelectedText(Graphics g, int x, int y, int p0, int p1)
            throws BadLocationException {
        g.setFont(TEXTFONT);
        g.setColor(TEXTCOLOR);
        return super.drawSelectedText(g, x, y, p0, p1);
    }

    /**
     *
     */
    public Font TEXTFONT = new Font("DialogInput", Font.PLAIN, 16);
    /**
     *
     */
    public Color TEXTCOLOR = Color.black;
    /**
     *
     */
    public Font KEYWORDFONT = new Font(TEXTFONT.getFontName(), Font.PLAIN, TEXTFONT.getSize());
    /**
     *
     */
    public Color KEYWORDCOLOR = new Color(0, 0, 230);
    /**
     *
     */
    public Font COMMENTFONT = TEXTFONT;
    /**
     *
     */
    public Color COMMENTCOLOR = new Color(150, 150, 150);
    /**
     *
     */
    public Font STRINGFONT = TEXTFONT;
    /**
     *
     */
    public Color STRINGCOLOR = new Color(206, 123, 0);
}