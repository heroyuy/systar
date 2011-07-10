/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author wp_g4
 */
public abstract class ArrayAdapter<T> implements Adapter {

    private ArrayList<T> items = new ArrayList<T>();

    public ArrayAdapter(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("参数不能为null");
        } else {
            this.items.addAll(Arrays.asList(items));
        }
    }

    public ArrayAdapter(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("参数不能为null");
        } else {
            this.items.addAll(items);
        }
    }

    public int getCount() {
        return items.size();
    }

    public abstract Widget getWidget(int inedx);

    public T getItem(int index) {
        return items.get(index);
    }
}
