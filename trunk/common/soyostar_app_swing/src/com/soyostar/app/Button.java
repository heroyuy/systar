package com.soyostar.app;

import com.soyostar.app.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * 按钮
 * @author wp_g4
 */
public class Button extends Component {

    private ActionListener listener = null;

    /**
     * 获取绑定到按钮上的事件监听器
     * @return 绑定到按钮上的事件监听器
     */
    public ActionListener getListener() {
        return listener;
    }

    /**
     * 设置事件监听器
     * @param listener 要绑定到按钮上的事件监听器
     */
    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    /**
     * 返回按钮的文本。
     * @return 按钮的文本。
     */
    public String getText() {
        return ((JButton) content).getText();
    }

    /**
     * 设置按钮的文本。
     * @param text 用于设置文本的字符串
     */
    public void setText(String text) {
        ((JButton) content).setText(text);
    }

    /**
     * 创建不带有设置文本的按钮。
     */
    public Button() {
        content = new JButton();
        ((JButton) content).addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.actionPerformed(Button.this);
                }
            }
        });
    }

    /**
     * 创建一个带文本的按钮。
     * @param text 按钮的文本
     */
    public Button(String text) {
        this();
        ((JButton) content).setText(text);
    }

    /**
     *  创建一个带事件监听器的按钮。
     * @param listener 要绑定到按钮的事件监听器
     */
    public Button(ActionListener listener) {
        this();
        this.listener = listener;
    }
//    /**
//     * 按钮的事件监听器
//     */
//    public interface Listener {
//
//        /**
//         * 当按钮被按下时调用
//         * @param source 事件源，即被按下的按钮
//         */
//        public void actionPerformed(Button source);
//    }
}
