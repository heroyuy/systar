package frame.event.ui_event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * 
 * 事件处理的Dialog的父类
 */
public abstract class Event_Dialog extends JDialog implements ActionListener {

    private Dialog_Main dialog_Main = null;
    private JButton button_confirm = null;
    private JButton button_cancel = null;

    public Event_Dialog(Dialog_Main parent) {
        super(parent, true);
        dialog_Main = parent;
        init();
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                cancel();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) e.getSource();
        if (jb == button_confirm) {
            if (confirm()) {
                cancel();
            } else {
                setVisible(false);
                dialog_Main.setVisible(false);
            }
        } else if (jb == button_cancel) {
            cancel();
        }
    }

    public void addDefaultButton() {
        add(button_confirm);
        add(button_cancel);
        button_confirm.setLocation(this.getWidth() - 240, this.getHeight() - 80);
        button_cancel.setLocation(this.getWidth() - 120, this.getHeight() - 80);
    }

    public void cancel() {
        setVisible(false);
        dialog_Main.setVisible(true);
    }

    public abstract boolean confirm();

    private void init() {
        this.setResizable(false);
        button_confirm = new JButton("确定");
        button_confirm.setSize(90, 30);
        button_cancel = new JButton("取消");
        button_cancel.setSize(90, 30);
        button_confirm.addActionListener(this);
        button_cancel.addActionListener(this);
//        this.setLocationRelativeTo(null);
//        button_confirm.setLocation(this.getWidth() - 240, this.getHeight() - 80);
//        button_cancel.setLocation(this.getWidth() - 120, this.getHeight() - 80);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
} // @jve:decl-index=0:visual-constraint="10,10"

