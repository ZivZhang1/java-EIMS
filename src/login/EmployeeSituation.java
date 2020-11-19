package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
public class EmployeeSituation extends JPanel implements ActionListener{
	Hashtable 基本信息表=null;
	JTextField 员工号, 姓名, 工资;
	Choice 部门;
	JRadioButton 男, 女;
	Employee 员工=null;
	ButtonGroup group=null;
	JButton 录入, 重置;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public EmployeeSituation(File file){
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
		录入=new JButton(" 录入");
		重置=new JButton(" 重置");
		录入.addActionListener(this);
		重置.addActionListener(this);
		Box box1=Box.createHorizontalBox();
		box1.add(new JLabel(" 员工号:",JLabel.CENTER));
		box1.add( 员工号);
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
		pSouth.add( 录入);
		pSouth.add( 重置);
		pSouth.setBackground(Color.yellow);
		add(pSouth,BorderLayout.SOUTH);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== 录入){
			String number="";
			//获取员工号
			number= 员工号.getText();
			if(number.length()>0){
				try{
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					基本信息表=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				//判断是否存在员工的基本信息
				if( 基本信息表.containsKey(number)){
					String warning=" 该员工基本信息已存在, 请到修改页面修改!";
					JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
				}else{
					//对信息录入进行再一次的确认
					String m=" 基本信息将被录入!";
					int ok=JOptionPane.showConfirmDialog(this,m," 确认",JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						//对输入的信息进行获取
						String name= 姓名.getText();
						String discipling= 部门.getSelectedItem();
						String grade= 工资.getText();
						String sex=null;
						if( 男.isSelected()){
							sex= 男.getText();
						}else{
							sex= 女.getText();
						}
						员工=new Employee();
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
							工资.setText(null);
						}catch(Exception ee){
							System.out.println(ee);
						}
					}
				}
			}
			else{
				String warning=" 必须要输入员工号!";
				JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()== 重置){
			员工号.setText(null);
			姓名.setText(null);
			部门.remove( 部门.getSelectedIndex());
			工资.setText(null);
		}
	}
	
}