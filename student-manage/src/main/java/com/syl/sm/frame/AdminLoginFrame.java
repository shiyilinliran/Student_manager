package com.syl.sm.frame;

import com.syl.sm.factory.ServiceFactory;
import com.syl.sm.utils.ResultEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * @ClassName AdminLoginFrame
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class AdminLoginFrame extends JFrame{
    private JPanel panel_main;
    private JPanel panel_top;
    private JPanel panel_left;
    private JPanel panel_right;
    private JPanel panel_bottom;
    private JPanel panel_center;
    private JPanel jp_1;
    private JPanel jp_2;
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel jp_3;
    private JButton loginButton;
    private JButton resetButton;
    private JPanel jp_4;
    private JPanel jp_5;
    private JPanel jp_7;
    private JPanel jp_8;
    private JPanel jp_9;
    private JPanel jp_10;
    private JPanel jp_11;
    private JPanel jp_12;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    public AdminLoginFrame(){
        this.setTitle("AdminLoginFrame");
        this.setContentPane(panel_main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        loginButton.addActionListener(e -> {
        String account=textField1.getText().trim();
        String password = new String(textField2.getText()).trim();
            ResultEntity resultEntity= ServiceFactory.getAdminServiceInstance().adminLogin(account,password);
            JOptionPane.showMessageDialog(panel_center,resultEntity.getMessage());
            if(resultEntity.getCode()==0){
                this.dispose();
                new MainFrame("译霖霖");
            }else {
                textField1.setText("");
                textField2.setText("");
            }
        });
        resetButton.addActionListener(e -> {
            textField1.setText("");
            textField2.setText("");
        });
    }


    public static void main(String[] args) {
        new AdminLoginFrame();
    }
}
