package com.soyostar.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author wp_g4
 * @version 1.0.2011.04.17
 */
public class Clone {

    /**
     * 通过对象序列化的方法实现对象的深层拷贝
     * @param <T> 所有实现了Serializable接口的类
     * @param t 实现了Serializable接口的类的对象
     * @return t的深层拷贝
     */
    public static <T extends Serializable> T clone(T t) {
        T res = null;
        ObjectOutputStream oos = null;
        try {
            //save
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            oos.close();
            //get
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            res = (T) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 通过反射的方法实现对象的深层拷贝。
     * 若一个字段是t1和t2共有的，则将t1的这个字段的值拷贝给t2，拷贝为深层拷贝
     * @param <T1> 拷贝源对象类型，任意
     * @param <T2> 拷贝目标对象类型，任意
     * @param t1 拷贝源对象
     * @param t2 拷贝目标对象
     */
    public static <T1, T2> void clone(T1 t1, T2 t2) {
        Field[] f1 = t1.getClass().getFields();
        for (int i = 0; i < f1.length; i++) {
            if (hasField(t2, f1[i].getName())) {
                //如果t2和t1有同一字段，则拷贝
                try {
                    //获取t1的字段的值
                    Object value = f1[i].get(t1);
                    if (value == null) {
                         f1[i].set(t2, null);
                    } else if ((f1[i].getType().isPrimitive()) || (value instanceof String)) {
                        System.out.println("基本类型||String:" + value.getClass().getName());
                        f1[i].set(t2, value);
                    } else {
                        System.out.println("对象：" + value.getClass().getName());
                        clone(value,f1[i].get(t2));
                    }
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断指定的类是否有指定名称的字段
     * @param <T>
     * @param t
     * @param name
     * @return
     */
    private static <T> boolean hasField(T t, String name) {
        boolean state = false;
        try {
            Field f = t.getClass().getField(name);
            state = true;
        } catch (NoSuchFieldException ex) {
            System.out.println(t.getClass().getName() + "中不存在字段：" + name);
            ex.printStackTrace();
        } catch (SecurityException ex) {
            System.out.println("没有权限访问" + t.getClass().getName() + "中的字段：" + name);
            ex.printStackTrace();
        }
        return state;
    }
}
