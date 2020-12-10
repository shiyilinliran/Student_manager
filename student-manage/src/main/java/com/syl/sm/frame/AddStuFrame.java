
package com.syl.sm.frame;
import com.eltima.components.ui.DatePicker;
import com.syl.sm.component.ImgPanel;
import com.syl.sm.entity.Clazz;
import com.syl.sm.entity.Department;
import com.syl.sm.entity.Student;
import com.syl.sm.factory.ServiceFactory;
import com.syl.sm.utils.AlioSSUtil;
import com.syl.sm.utils.FormatUtil;
import com.syl.sm.vo.StudentVo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName AddStuFrame
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/3 18:47
 **/
public class AddStuFrame extends JFrame{
    private ImgPanel mainPanel;
    private JTextField idFiled;
    private JTextField nameField;
    private JComboBox<Clazz> addclassComboBox;
    private JRadioButton manRaButton;
    private JRadioButton womanRaButton;
    private JTextField addressField;
    private JPanel datePanel;
    private JTextField phoneField;
    private JLabel picLabel;
    private JButton addStuButton;
    private JComboBox<Department> chosDepComboBox;
    private JButton 关闭Button;
    private JLabel closeField;
    private String uploadFileUrl;
    private int classId;
    private File file;
    private final MainFrame mainFrame;

    public AddStuFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        mainPanel.setFileName("1.jpg");
        this.setTitle("新增学生信息");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //初始化院系选择
        Department department = new Department();
        department.setDepartmentName("请选择院系");
        chosDepComboBox.addItem(department);
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        for(Department depart:departmentList){
            chosDepComboBox.addItem(depart);
        }
        //初始化班级选择
        Clazz tip = new Clazz();
        tip.setClassName("请选择班级");
        addclassComboBox.addItem(tip);
        chosDepComboBox.addActionListener(e->{
            //根据院系选择，班级跳出
            int index=chosDepComboBox.getSelectedIndex();
            Integer depId=chosDepComboBox.getItemAt(index).getId();
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(depId);
            for(Clazz clazz:clazzList){
                addclassComboBox.addItem(clazz);
            }
        });
        //班级复选框
        addclassComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int index = addclassComboBox.getSelectedIndex();
                if (index != 0) {
                    classId = addclassComboBox.getItemAt(index).getId();
                }
            }
        });

        //关闭窗体

        picLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:\\背景图片"));
                int result = fileChooser.showOpenDialog(rootPane);
                if(result == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(100,100,100));
                    picLabel.setText("");
                    picLabel.setIcon(icon);
                }
            }
        });
        //单选按钮
        ButtonGroup group = new ButtonGroup();
        group.add(manRaButton);
        group.add(womanRaButton);

        //出生日期
        DatePicker datePicker = getDatePicker();
        datePanel.setPreferredSize(new Dimension(300,50));
        datePanel.add(datePicker);
        datePanel.revalidate();

        addStuButton.addActionListener(e -> {
            String gender =null;
            if(manRaButton.isSelected()){
                gender = "男";
            }
            if(womanRaButton.isSelected()){
                gender = "女";
            }
            Student student = new Student();
            student.setId(idFiled.getText());
            student.setClassId(classId);
            student.setStudentName(nameField.getText());
            student.setAvatar(AlioSSUtil.ossUpload(file));
            student.setGender(FormatUtil.formatGender(gender));
            student.setBirthday((Date)datePicker.getValue());
            student.setAddress(addressField.getText());
            student.setPhone(phoneField.getText());
            int n = ServiceFactory.getStudentServiceInstance().insertStudent(student);
            if(n ==1 ){
                JOptionPane.showMessageDialog(null,"新增成功");
                AddStuFrame.this.dispose();
                List<StudentVo> studentVoList = ServiceFactory.getStudentServiceInstance().selectAll();
                mainFrame.showStudentTable(studentVoList);
            }
        });
        关闭Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddStuFrame.this.dispose();
            }
        });
    }
    private static DatePicker getDatePicker() {
        final DatePicker datePicker;
        // 格式
        String defaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(200, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePicker = new DatePicker(date, defaultFormat, font, dimension);
        //设置起始位置
        // datePicker.setLocation(137, 83);
        //也可用setBounds()直接设置大小与位置
        //datePicker.setBounds(137, 83, 177, 24);
        // 设置一个月份中需要高亮显示的日子
        datePicker.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePicker.setDisableddays(disabledDays);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        // datePicker.setTimePanleVisible(true);
        return datePicker;
    }
}
