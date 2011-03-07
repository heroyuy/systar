package frame.dm;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JTextField;
import model.Project;

import java.awt.event.KeyEvent;

public class SystemTab extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JScrollPane jScrollPane = null;
    private JScrollPane jScrollPane1 = null;
    private JTextArea jTextArea = null;
    private JTextArea jTextArea1 = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JTextField jTextField = null;
    private JTextField jTextField1 = null;
    private JLabel jLabel5 = null;
    private JLabel jLabel6 = null;
    private JTextField jTextField2 = null;
    private JTextField jTextField3 = null;
    private JTextField jTextField4 = null;
    private JTextField jTextField5 = null;
    private JTextField jTextField6 = null;
    private JTextField jTextField7 = null;
    private JTextField jTextField8 = null;
    private JTextField jTextField9 = null;
    private JTextField jTextField10 = null;
    private JTextField jTextField11 = null;
    private JTextField jTextField12 = null;
    private JTextField jTextField13 = null;
    private JTextField jTextField14 = null;
    private JTextField jTextField15 = null;
    private JTextField jTextField16 = null;
    private JLabel jLabel7 = null;
    private JLabel jLabel8 = null;
    private JLabel jLabel9 = null;
    private JLabel jLabel10 = null;
    private JLabel jLabel11 = null;
    private JLabel jLabel12 = null;
    private JLabel jLabel13 = null;
    private JLabel jLabel14 = null;
    private JLabel jLabel15 = null;
    private JLabel jLabel16 = null;
    private JLabel jLabel17 = null;
    private JLabel jLabel18 = null;

    public SystemTab() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        jLabel7 = new JLabel();
        jLabel7.setText("智力用语：");
        jLabel7.setSize(new Dimension(70, 20));
        jLabel7.setLocation(new Point(170, 100));
        jLabel8 = new JLabel();
        jLabel8.setText("敏捷用语：");
        jLabel8.setSize(new Dimension(70, 20));
        jLabel8.setLocation(new Point(170, 70));
        jLabel9 = new JLabel();
        jLabel9.setText("攻击力用语：");
        jLabel9.setSize(new Dimension(75, 20));
        jLabel9.setLocation(new Point(170, 10));
        jLabel10 = new JLabel();
        jLabel10.setText("防御力用语：");
        jLabel10.setSize(new Dimension(75, 20));
        jLabel10.setLocation(new Point(170, 40));
        jLabel11 = new JLabel();
        jLabel11.setText("金钱用语：");
        jLabel11.setSize(new Dimension(70, 20));
        jLabel11.setLocation(new Point(170, 160));
        jLabel12 = new JLabel();
        jLabel12.setText("闪避用语：");
        jLabel12.setSize(new Dimension(70, 20));
        jLabel12.setLocation(new Point(170, 130));
        jLabel13 = new JLabel();
        jLabel13.setText("头盔用语：");
        jLabel13.setSize(new Dimension(70, 20));
        jLabel13.setLocation(new Point(10, 130));
        jLabel14 = new JLabel();
        jLabel14.setText("铠甲用语：");
        jLabel14.setSize(new Dimension(70, 20));
        jLabel14.setLocation(new Point(10, 160));
        jLabel15 = new JLabel();
        jLabel15.setText("武器用语：");
        jLabel15.setSize(new Dimension(70, 20));
        jLabel15.setLocation(new Point(10, 220));
        jLabel16 = new JLabel();
        jLabel16.setText("盾牌用语：");
        jLabel16.setSize(new Dimension(70, 20));
        jLabel16.setLocation(new Point(10, 190));
        jLabel17 = new JLabel();
        jLabel17.setText("战靴用语：");
        jLabel17.setSize(new Dimension(70, 20));
        jLabel17.setLocation(new Point(170, 190));
        jLabel18 = new JLabel();
        jLabel18.setText("饰品用语：");
        jLabel18.setSize(new Dimension(70, 20));
        jLabel18.setLocation(new Point(170, 220));
        jLabel6 = new JLabel();
        jLabel6.setText("hp用语：");
        jLabel6.setSize(new Dimension(70, 20));
        jLabel6.setLocation(new Point(10, 100));
        jLabel5 = new JLabel();
        jLabel5.setText("sp用语：");
        jLabel5.setSize(new Dimension(70, 20));
        jLabel5.setLocation(new Point(10, 70));
        jLabel4 = new JLabel();
        jLabel4.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
        jLabel4.setLocation(new Point(10, 40));
        jLabel4.setSize(new Dimension(70, 20));
        jLabel4.setText("力量用语：");
        jLabel3 = new JLabel();
        jLabel3.setText("游戏名称：");
        jLabel3.setSize(new Dimension(70, 20));
        jLabel3.setLocation(new Point(10, 10));
        jLabel1 = new JLabel();
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setLocation(new Point(350, 170));
        jLabel1.setSize(new Dimension(200, 20));
        jLabel1.setText("游戏说明");
        jLabel = new JLabel();
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setLocation(new Point(350, 10));
        jLabel.setSize(new Dimension(200, 20));
        jLabel.setText("游戏帮助");
        this.setLayout(null);
        this.setSize(new Dimension(566, 320));
        this.add(jLabel, null);
        this.add(jLabel1, null);
        this.add(getJScrollPane(), null);
        this.add(getJScrollPane1(), null);
        this.add(jLabel3, null);
        this.add(jLabel4, null);
        this.add(getJTextField(), null);
        this.add(getJTextField1(), null);
        this.add(jLabel5, null);
        this.add(jLabel6, null);
        this.add(getJTextField2(), null);
        this.add(getJTextField3(), null);
        this.add(getJTextField4(), null);
        this.add(getJTextField5(), null);
        this.add(getJTextField6(), null);
        this.add(getJTextField7(), null);
        this.add(getJTextField8(), null);
        this.add(getJTextField9(), null);
        this.add(getJTextField10(), null);
        this.add(getJTextField11(), null);
//        this.add(getJTextField12(), null);
        this.add(getJTextField13(), null);
        this.add(getJTextField14(), null);
        this.add(getJTextField15(), null);
        this.add(getJTextField16(), null);
        this.add(jLabel7, null);
        this.add(jLabel8, null);
        this.add(jLabel9, null);
        this.add(jLabel10, null);
        this.add(jLabel11, null);
        this.add(jLabel12, null);
        this.add(jLabel13, null);
        this.add(jLabel14, null);
        this.add(jLabel15, null);
        this.add(jLabel16, null);
        this.add(jLabel17, null);
        this.add(jLabel18, null);

    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setLocation(new Point(350, 40));
            jScrollPane.setSize(new Dimension(200, 100));
            jScrollPane.setViewportView(getJTextArea());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setSize(new Dimension(200, 100));
            jScrollPane1.setLocation(new Point(350, 200));
            jScrollPane1.setViewportView(getJTextArea1());
        }
        return jScrollPane1;
    }

    /**
     * This method initializes jTextArea
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
        }
        return jTextArea;
    }

    /**
     * This method initializes jTextArea1
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea1() {
        if (jTextArea1 == null) {
            jTextArea1 = new JTextArea();
        }
        return jTextArea1;
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setLocation(new Point(80, 10));
            jTextField.setSize(new Dimension(70, 20));
        }
        return jTextField;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setLocation(new Point(80, 40));
            jTextField1.setSize(new Dimension(70, 20));
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setSize(new Dimension(70, 20));
            jTextField2.setLocation(new Point(80, 70));
        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField3
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField3() {
        if (jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setSize(new Dimension(70, 20));
            jTextField3.setLocation(new Point(80, 100));
        }
        return jTextField3;
    }

    private JTextField getJTextField4() {
        if (jTextField4 == null) {
            jTextField4 = new JTextField();
            jTextField4.setSize(new Dimension(70, 20));
            jTextField4.setLocation(new Point(80, 130));
            jTextField4.setText("头盔");
        }
        return jTextField4;
    }

    private JTextField getJTextField5() {
        if (jTextField5 == null) {
            jTextField5 = new JTextField();
            jTextField5.setSize(new Dimension(70, 20));
            jTextField5.setLocation(new Point(80, 160));
            jTextField5.setText("铠甲");
        }
        return jTextField5;
    }

    private JTextField getJTextField6() {
        if (jTextField6 == null) {
            jTextField6 = new JTextField();
            jTextField6.setSize(new Dimension(70, 20));
            jTextField6.setLocation(new Point(260, 190));
            jTextField6.setText("战靴");
        }
        return jTextField6;
    }

    private JTextField getJTextField7() {
        if (jTextField7 == null) {
            jTextField7 = new JTextField();
            jTextField7.setSize(new Dimension(70, 20));
            jTextField7.setLocation(new Point(260, 220));
            jTextField7.setText("饰品");
        }
        return jTextField7;
    }

    private JTextField getJTextField8() {
        if (jTextField8 == null) {
            jTextField8 = new JTextField();
            jTextField8.setSize(new Dimension(70, 20));
            jTextField8.setLocation(new Point(260, 100));
            jTextField8.setText("智力");
        }
        return jTextField8;
    }

    private JTextField getJTextField9() {
        if (jTextField9 == null) {
            jTextField9 = new JTextField();
            jTextField9.setSize(new Dimension(70, 20));
            jTextField9.setLocation(new Point(260, 130));
            jTextField9.setText("闪避");
        }
        return jTextField9;
    }

    private JTextField getJTextField10() {
        if (jTextField10 == null) {
            jTextField10 = new JTextField();
            jTextField10.setSize(new Dimension(70, 20));
            jTextField10.setLocation(new Point(260, 160));
            jTextField10.setText("金钱");
        }
        return jTextField10;
    }

    private JTextField getJTextField11() {
        if (jTextField11 == null) {
            jTextField11 = new JTextField();
            jTextField11.setSize(new Dimension(70, 20));
            jTextField11.setLocation(new Point(260, 10));
            jTextField11.setText("攻击力");
        }
        return jTextField11;
    }

//    private JTextField getJTextField12() {
//        if (jTextField12 == null) {
//            jTextField12 = new JTextField();
//            jTextField12.setSize(new Dimension(70, 20));
//            jTextField12.setLocation(new Point(260, 220));
//            jTextField12.setText("12");
//        }
//        return jTextField12;
//    }
    private JTextField getJTextField13() {
        if (jTextField13 == null) {
            jTextField13 = new JTextField();
            jTextField13.setSize(new Dimension(70, 20));
            jTextField13.setLocation(new Point(260, 70));
            jTextField13.setText("敏捷");
        }
        return jTextField13;
    }

    private JTextField getJTextField14() {
        if (jTextField14 == null) {
            jTextField14 = new JTextField();
            jTextField14.setSize(new Dimension(70, 20));
            jTextField14.setLocation(new Point(260, 40));
            jTextField14.setText("防御力");
        }
        return jTextField14;
    }

    private JTextField getJTextField15() {
        if (jTextField15 == null) {
            jTextField15 = new JTextField();
            jTextField15.setSize(new Dimension(70, 20));
            jTextField15.setLocation(new Point(80, 220));
            jTextField15.setText("武器");
        }
        return jTextField15;
    }

    private JTextField getJTextField16() {
        if (jTextField16 == null) {
            jTextField16 = new JTextField();
            jTextField16.setSize(new Dimension(70, 20));
            jTextField16.setLocation(new Point(80, 190));
            jTextField16.setText("盾牌");
        }
        return jTextField16;
    }

    public void save() {
        Project.setName(jTextField.getText());
        Project.setStre(jTextField1.getText());
        Project.setHp(jTextField2.getText());
        Project.setSp(jTextField3.getText());
        Project.setHelp(jTextArea.getText());
        Project.setIntroduction(jTextArea1.getText());
        Project.setWeapon(jTextField15.getText());
        Project.setTrinket(jTextField7.getText());
        Project.setShield(jTextField16.getText());
        Project.setMoney(jTextField10.getText());
        Project.setAgil(jTextField13.getText());
        Project.setArmour(jTextField5.getText());
        Project.setAtk(jTextField11.getText());
        Project.setCaliga(jTextField6.getText());
        Project.setDef(jTextField14.getText());
        Project.setHelm(jTextField4.getText());
        Project.setInte(jTextField8.getText());
        Project.setFlee(jTextField9.getText());
    }

    public void updateData() {
        jTextField.setText(Project.getGameName());
        jTextField1.setText(Project.getStre());
        jTextField2.setText(Project.getHp());
        jTextField3.setText(Project.getSp());
        jTextArea.setText(Project.getHelp());
        jTextArea1.setText(Project.getIntroduction());
        jTextField15.setText(Project.getWeapon());
        jTextField7.setText(Project.getTrinket());
        jTextField16.setText(Project.getShield());
        jTextField10.setText(Project.getMoney());
        jTextField13.setText(Project.getAgil());
        jTextField5.setText(Project.getArmour());
        jTextField11.setText(Project.getAtk());
        jTextField6.setText(Project.getCaliga());
        jTextField14.setText(Project.getDef());
        jTextField4.setText(Project.getHelm());
        jTextField8.setText(Project.getInte());
        jTextField9.setText(Project.getFlee());
    }
} // @jve:decl-index=0:visual-constraint="42,-4"

