package frame.event.ui_event;

import javax.swing.JButton;

/**
 *
 * 指令集面板中的Button
 */
public class Event_Button extends JButton {

    public Event_Button(Dialog_Main dialog_Main, String text) {
        setText(text);
        setSize(90, 30);
        addActionListener(dialog_Main);
    }
}
