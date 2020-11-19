package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
public class ModifySituation extends JPanel implements ActionListener{
	Hashtable 基本信息表=null;
	JTextField 员工号, 姓名, 工资;
	Choice 部门;
	JRadioButton 男, 女;
	ButtonGroup group=null;
	JButton 开始修改, 录入修改, 重置;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public ModifySituation(File file){
		this.file=file;
		员工号=new JTextField(10);
		姓名=new JTextField(10);
		部门=new Choice();
		部门.add(" 请选择");
		部门.add(" 研发部");
		部门.add(" 销售部");
		部门.add(" 人事部");
		部门.add(" 安全部");
		工资=new JTextField(10);
		group=new ButtonGroup();
		男=new JRadioButton(" 男",true);
		女=new JRadioButton(" 女",false);
		group.add( 男);
		group.add( 女);
		开始修改=new JButton(" 开始修改");
		录入修改=new JButton(" 录入修改");
		录入修改.setEnabled(false);
		重置=new JButton(" 重置");
		员工号.addActionListener(this);
		开始修改.addActionListener(this);
		录入修改.addActionListener(this);
		重置.addActionListener(this);
		Box box1=Box.createHorizontalBox();
		box1.add(new JLabel(" 输入要修改信息的员工号:",JLabel.CENTER));
		box1.add( 员工号);
		box1.add( 开始修改);
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
		Box box6=Box.createHorizontalBox();
		box6.add(new JLabel(" ",JLabel.CENTER));
		Box box5=Box.createHorizontalBox();
		box5.add(new JLabel(" 工资:",JLabel.CENTER));
		box5.add( 工资);
		Box boxH=Box.createVerticalBox();
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		JPanel pCenter=new JPanel();
		pCenter.add(boxH);
		pCenter.setBackground(Color.yellow);
		setLayout(new BorderLayout());
		add(pCenter,BorderLayout.CENTER);
		JPanel pSouth=new JPanel();
		pSouth.add( 录入修改);
		pSouth.add( 重置);
		pSouth.setBackground(Color.yellow);
		add(pSouth,BorderLayout.SOUTH);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== 开始修改||e.getSource()== 员工号){
			String number="";
			number=员工号.getText();
			if(number.length()>0){
				try {
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					基本信息表=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				//判断是否存在此员工，如果存在则可以修改，否则提示无此员工
				if( 基本信息表.containsKey(number)){
					录入修改.setEnabled(true);
					Employee stu=(Employee) 基本信息表.get(number);
					姓名.setText(stu.getName());
					部门.getSelectedItem();
					工资.setText(stu.getGrade());
					if(stu.getSex().equals(" 男")){
						男.setSelected(true);
					}else{
						女.setSelected(true);
					}
				}else{
					录入修改.setEnabled(false);
					String warning=" 该员工号不存在!";
					JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
					员工号.setText(null);
					姓名.setText(null);
					部门.remove( 部门.getSelectedItem());
					工资.setText(null);
				}
			}else{
				录入修改.setEnabled(false);
				String warning=" 必须要输入员工号!";
				JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
				员工号.setText(null);
				姓名.setText(null);
				部门.remove( 部门.getSelectedItem());
				工资.setText(null);
			}
		}else if(e.getSource()== 录入修改){
			String number="";
			number=员工号.getText();
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
					String question=" 该员工基本信息已存在, 您想修改他( 她) 的基本信息吗?";
					JOptionPane.showMessageDialog(this,question," 警告",JOptionPane.QUESTION_MESSAGE);
					String m=" 基本信息将被修改!";
					int ok=JOptionPane.showConfirmDialog(this,m," 确认",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						String name= 姓名.getText();
						String discipling= 部门.getSelectedItem();
						String grade= 工资.getText();
						String sex=null;
						if( 男.isSelected()){
							sex= 男.getText();
						}else{
							sex= 女.getText();
						}
						Employee 员工=new Employee();
						员工.setNumber(number);
						员工.setName(name);
						员工.setDiscipling(discipling);
						员工.setGrade(grade);
						员工.setSex(sex);
						try{
							outOne=new FileOutputStream(file);
							outTwo=new ObjectOutputStream(outOne);
							基本信息表.put(number, 员工);
							outTwo.writeObject( 基本信息表);
							outTwo.close();
							outOne.close();
							员工号.setText(null);
							姓名.setText(null);
							部门.remove( 部门.getSelectedItem());
							工资.setText(null);
						}catch(Exception ee){
							System.out.println(ee);
						}
						录入修改.setEnabled(false);
					}else if(ok==JOptionPane.NO_OPTION){
						录入修改.setEnabled(true);
					}
				}
				else{
					String warning=" 该员工号没有基本信息, 不能修改!";
					JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
					录入修改.setEnabled(false);
				}
			}else{
				String warning=" 必须要输入员工号!";
				JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
				录入修改.setEnabled(false);
			}
		}
		//实现对员工信息的重置
		if(e.getSource()== 重置){
			员工号.setText(null);
			姓名.setText(null);
			部门.remove( 部门.getSelectedItem());
			工资.setText(null);
		}
	}
}