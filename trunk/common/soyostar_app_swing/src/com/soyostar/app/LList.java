/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import com.soyostar.app.LList.ListItem.ImageItem;
import com.soyostar.app.LList.ListItem.StringItem;
import com.soyostar.app.event.TouchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class LList extends Widget {

    public static class ListItem {

        private List<ImageItem> imageList = new ArrayList<ImageItem>();
        private List<StringItem> stringList = new ArrayList<StringItem>();

        public void addImage(Image img, int x, int y, int anchor) {
            imageList.add(new ImageItem(img, x, y, anchor));
        }

        public void addString(String str, int x, int y, int anchor, int color) {
            stringList.add(new StringItem(str, x, y, anchor, color));
        }

        class ImageItem {

            public Image img;
            public int x;
            public int y;
            public int anchor;

            public ImageItem(Image img, int x, int y, int anchor) {
                this.img = img;
                this.x = x;
                this.y = y;
                this.anchor = anchor;
            }
        }

        class StringItem {

            public String str;
            public int x;
            public int y;
            public int anchor;
            public int color;

            public StringItem(String str, int x, int y, int anchor, int color) {
                this.str = str;
                this.x = x;
                this.y = y;
                this.anchor = anchor;
                this.color = color;
            }
        }
    }

    public interface ItemSelectedListener {

        public void itemSelected(int selectedIndex, ListItem selectedListItem);
    }
    private List<ListItem> itemList = new ArrayList<ListItem>();
    private int itemHeight = 20;
    private Image selectedItemBackground = null;
    private int selectedIndex = 0;
    private int curOffsetY = 0;
    private int prevY = 0;
    private ItemSelectedListener itemSelectedListener = null;

    public ItemSelectedListener getItemSelectedListener() {
        return itemSelectedListener;
    }

    public void setItemSelectedListener(ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public Image getSelectedItemBackground() {
        return selectedItemBackground;
    }

    public void setSelectedItemBackground(Image selectedItemBackground) {
        this.selectedItemBackground = selectedItemBackground;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public void addListItem(ListItem listItem) {
        itemList.add(listItem);
    }

    @Override
    public void paint(Painter painter) {
        super.paint(painter);
        for (int i = 0; i < itemList.size(); i++) {
            ListItem lt = itemList.get(i);
            //设置裁剪区
            Rect clip = painter.getClip();
            ((GraphicsPainter) painter).clipRect(0, i * itemHeight + curOffsetY, getWidth(), itemHeight);
            ((GraphicsPainter) painter).setCurClip(painter.getClip());
            //绘制listItem
            if (selectedIndex == i) {
                painter.drawImage(selectedItemBackground, 0, i * itemHeight + curOffsetY, Painter.LT);
            }
            for (ImageItem ii : lt.imageList) {
                painter.drawImage(ii.img, ii.x, i * itemHeight + curOffsetY + ii.y, ii.anchor);
            }

            for (StringItem si : lt.stringList) {
                painter.setColor(si.color);
                painter.drawString(si.str, si.x, i * itemHeight + curOffsetY + si.y, si.anchor);
            }

            //还原裁剪区
            ((GraphicsPainter) painter).forceClip(clip);
        }
    }

    @Override
    public boolean dispatchTouchEvent(TouchEvent te) {
        if (!isEnabled()) {
            return false;
        }
        switch (te.getType()) {
            case TouchEvent.TOUCH_DOWN: {
                prevY = te.getY();
            }
            break;
            case TouchEvent.TOUCH_MOVE: {
                int distance = te.getY() - prevY;
                if (curOffsetY + distance >= -(itemList.size() * itemHeight - getHeight()) && curOffsetY + distance <= 0) {
                    curOffsetY += distance;
                    prevY = te.getY();
                }
            }
            break;
            case TouchEvent.TOUCH_UP: {
                if (te.getY() >= 0 && te.getY() <= getHeight()) {
                    int y = te.getY() - curOffsetY;
                    for (int i = 0; i < itemList.size(); i++) {
                        if (y >= i * itemHeight && y < (i + 1) * itemHeight) {
                            selectedIndex = i;
                            if (itemSelectedListener != null) {
                                itemSelectedListener.itemSelected(selectedIndex, itemList.get(selectedIndex));
                            }
                            break;
                        }
                    }
                }
            }
            break;
        }
        return true;
    }
}
