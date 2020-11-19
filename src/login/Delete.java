package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Delete extends JPanel implements ActionListener{
	Hashtable 基本信息表=null;
	JTextField 员工号, 姓名, 部门, 工资;
	JRadioButton 男, 女;
	JButton 删除;
	ButtonGroup group=null;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public Delete(File file){
		this.file=file;
		员工号=new JTextField(10);
		删除=new JButton(" 删除");
		员工号.addActionListener(this);
		删除.addActionListener(this);
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
		box1.add(new JLabel(" 输入要删除的学号:",JLabel.CENTER));
		box1.add( 员工号);
		box1.add( 删除);
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
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== 删除||e.getSource()== 员工号){
			String number="";
			number=员工号.getText();
			if(number.length()>0){
				try{
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					基本信息表=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				//在基本信息表中查看是否有该员工
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
					String m=" 确定要删除该员工号及全部信息吗?";
					int ok=JOptionPane.showConfirmDialog(this,m," 确认",JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						基本信息表.remove(number);
						try{
							outOne=new FileOutputStream(file);
							outTwo=new ObjectOutputStream(outOne);
							outTwo.writeObject( 基本信息表);
							outTwo.close();
							outOne.close();
							员工号.setText(null);
							姓名.setText(null);
							部门.setText(null);
							工资.setText(null);
						}catch(Exception ee){
							System.out.println(ee);
						}
					}else if(ok==JOptionPane.NO_OPTION){	
						员工号.setText(null);
						姓名.setText(null);
						部门.setText(null);
						工资.setText(null);
					}
				}else{
					String warning=" 该员工号不存在!";
					JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
				}
			}else{
				String warning=" 必须要输入员工号!";
				JOptionPane.showMessageDialog(this,warning," 警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}