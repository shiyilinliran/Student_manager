package com.syl.sm.frame;

import com.syl.sm.entity.Department;
import com.syl.sm.factory.ServiceFactory;
import com.syl.sm.utils.AlioSSUtil;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @ClassName MainFrame
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel jpe_1;
    private JPanel jpe_2;
    private JButton ygButton;
    private JButton bgButton;
    private JButton xgButton;
    private JButton jgButton;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;
    private JPanel mainPanel_left;
    private JPanel mainPanel_bottom;
    private JPanel mainPanel_right;
    private JPanel departmentPanel_top;
    private JButton dpt_b1;
    private JButton dpt_b2;
    private JScrollPane p;
    private JPanel dp_center;
    private JTextField m_tf;
    private JButton m_b;
    private JLabel m_lb1;
    private JLabel lb11;
    private JPanel panel_left;
    private JButton bbt1;
    private final CardLayout c;
    private String uploadFileUrl;
    private File file;

    public MainFrame() {
        init();
        c = new CardLayout();
        jpe_2.setLayout(c);
        jpe_2.add("1", departmentPanel);
        jpe_2.add("2", classPanel);
        jpe_2.add("3", studentPanel);
        jpe_2.add("4", rewardPanel);
        ygButton.addActionListener(e -> {
            c.show(jpe_2, "1");
        });
        bgButton.addActionListener(e -> {
            c.show(jpe_2, "2");
        });
        xgButton.addActionListener(e -> {
            c.show(jpe_2, "3");
        });
        jgButton.addActionListener(e -> {
            c.show(jpe_2, "4");
        });
        showDepartments();
        //ctrl+o
        bbt1.addActionListener(e -> {
            mainPanel_left.setVisible(true);
            uploadFileUrl = AlioSSUtil.ossUpload(file);
            Department department = new Department();
            department.setDepartmentName(m_tf.getText().trim());
            department.setLogo(uploadFileUrl);
            int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
            if (n == 1) {
                JOptionPane.showMessageDialog(dp_center, "新增院系成功");
                mainPanel_left.setPreferredSize(new Dimension(60, this.getHeight() - 100));
                mainPanel_left.setVisible(false);
                showDepartments();
                m_tf.setText("");
                lb11.setIcon(null);
            } else {
                JOptionPane.showMessageDialog(dp_center, "新增院系失败");
            }
        });
        dpt_b1.addActionListener(e -> {
            boolean b=mainPanel_left.isVisible();
            if(b==false) {
                mainPanel_left.setVisible(true);
            }else {
                mainPanel_left.setVisible(false);
            }
        });
        m_b.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("D:\\DownLoad\\Picture_sucai"));
            int result = fileChooser.showOpenDialog(jpe_2);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                String name = file.getAbsolutePath();
                ImageIcon icon = new ImageIcon(name);
                lb11.setPreferredSize(new Dimension(150, 150));
                icon = new ImageIcon(icon.getImage().getScaledInstance(lb11.getWidth(), lb11.getHeight(), Image.SCALE_DEFAULT));
                lb11.setIcon(icon);
            }
        });
    }

    public void init() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //全屏
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setSize(1500, 1000);
        //居中
        this.setLocationRelativeTo(null);
    }

    private void showDepartments() {
        dp_center.removeAll();
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        int len = departmentList.size();
        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
        dp_center.setLayout(gridLayout);
        for (Department department : departmentList) {
//            dp_center.setPreferredSize(new Dimension(130,300));
            JPanel dePanel = new JPanel();
            JLabel blankLabel = new JLabel();
            JLabel nameLabel = new JLabel(department.getDepartmentName());
            blankLabel.setPreferredSize(new Dimension(200, 30));
            dePanel.setPreferredSize(new Dimension(150, 300));
            dePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "'height='222'/></html>");
            JButton delBut = new JButton("删除");
            delBut.addActionListener(e -> {
                ServiceFactory.getDepartmentServiceInstance().delDepartment(department.getId());
                this.showDepartments();
            });
            delBut.setBackground(new Color(213, 254, 255));
            dePanel.add(nameLabel);
            dePanel.add(logoLabel);
            dePanel.add(delBut);
            dp_center.add(dePanel);
            dp_center.revalidate();
        }
    }

    //350*250
    public static void main(String[] args) {
        new MainFrame();
    }
}
