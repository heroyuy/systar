
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

import emulator.Emulator;

import java.awt.Rectangle;

public class Test extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton jButton = null;
    private Emulator emulator = null;

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(92, 60, 91, 37));
            jButton.setText("测试");
            jButton.setText("测试");
            jButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getEmulator().showEmulatorAt(0, 0);
                }
            });
        }
        return jButton;
    }

    private Emulator getEmulator() {
        if (emulator == null) {
            emulator =  Emulator.getDefaultEmulator();
        }
        return emulator;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成方法存根
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Test thisClass = new Test();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    /**
     * This is the default constructor
     */
    public Test() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJButton(), null);
        }
        return jContentPane;
    }
}
