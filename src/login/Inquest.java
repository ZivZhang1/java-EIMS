package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
public class Inquest extends JDialog implements ActionListener{
	Hashtable 基本信息表=null;
	JTextField 员工号, 姓名, 部门, 工资;
	JRadioButton 男, 女;
	JButton 查询;
	ButtonGroup group=null;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	File file=null;
	public Inquest(JFrame f,File file){
		super(f," 查询对话框",false);
		this.file=file;
		员工号=new JTextField(10);
		查询=new JButton(" 查询");
		员工号.addActionListener(this);
		查询.addActionListener(this);
		姓名=new JTextField(10);
		姓名.setEditable(false);
		部门=new JTextField(10);
		部门.setEditable(false);
		工资=new JTextField(10);
		工资.setEditable(false);
		男=new JRadioButton(" 男",false);
		女=new JRadioButton(" 女",false);
		group=new ButtonGroup();
		group.add( 男);
		group.add( 女);
		Box box1=Box.createHorizontalBox();
		box1.add(new JLabel(" 输入要查询的员工号:",JLabel.CENTER));
		box1.add( 员工号);
		box1.add( 查询);
		Box box2=Box.createHorizontalBox();
		box2.add(new JLabel(" 姓名:",JLabel.CENTER));
		box2.add( 姓名);
		Box box3=Box.createHorizontalBox();
		box3.add(new JLabel(" 性别:",JLabel.CENTER));
		box3.add( 男);
		box3.add( 女);
		Box box4=Box.createHorizontalBox();
		box4.add(new JLabel(" 部门:",JLabel.CENTER));
		box4.add( 部门);
		Box box5=Box.createHorizontalBox();
		box5.add(new JLabel(" 工资:",JLabel.CENTER));
		box5.add( 工资);
		Box boxH=Box.createVerticalBox();
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box5);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		JPanel pCenter=new JPanel();
		pCenter.add(boxH);
		pCenter.setBackground(Color.green);
		Container con=getContentPane();
		con.add(pCenter,BorderLayout.CENTER);
		con.validate();
		setVisible(false);
		setBounds(100,200,360,270);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		姓名.setText(null);
		部门.setText(null);
		工资.setText(null);
		if(e.getSource()== 查询||e.getSource()== 员工号){
			String number="";
			number= 员工号.getText();
			if(number.length()>0){
				try {
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					基本信息表=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				if( 基本信息表.containsKey(number)){
					Employee stu=(Employee) 基本信息表.get(number);
					姓名.setText(stu.getName());
					部门.setText(stu.getDisciping());
					工资.setText(stu.getGrade());
					if(stu.getSex().equals(" 男")){
						男.setSelected(true);
					}else{
						女.setSelected(true);
					} 
				}else{
					String warning=" 该员工号不存在!";
					JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				String warning=" 必须要输入员工号!";
				JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}