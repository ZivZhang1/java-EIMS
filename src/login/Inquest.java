package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
public class Inquest extends JDialog implements ActionListener{
	Hashtable ������Ϣ��=null;
	JTextField Ա����, ����, ����, ����;
	JRadioButton ��, Ů;
	JButton ��ѯ;
	ButtonGroup group=null;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	File file=null;
	public Inquest(JFrame f,File file){
		super(f," ��ѯ�Ի���",false);
		this.file=file;
		Ա����=new JTextField(10);
		��ѯ=new JButton(" ��ѯ");
		Ա����.addActionListener(this);
		��ѯ.addActionListener(this);
		����=new JTextField(10);
		����.setEditable(false);
		����=new JTextField(10);
		����.setEditable(false);
		����=new JTextField(10);
		����.setEditable(false);
		��=new JRadioButton(" ��",false);
		Ů=new JRadioButton(" Ů",false);
		group=new ButtonGroup();
		group.add( ��);
		group.add( Ů);
		Box box1=Box.createHorizontalBox();
		box1.add(new JLabel(" ����Ҫ��ѯ��Ա����:",JLabel.CENTER));
		box1.add( Ա����);
		box1.add( ��ѯ);
		Box box2=Box.createHorizontalBox();
		box2.add(new JLabel(" ����:",JLabel.CENTER));
		box2.add( ����);
		Box box3=Box.createHorizontalBox();
		box3.add(new JLabel(" �Ա�:",JLabel.CENTER));
		box3.add( ��);
		box3.add( Ů);
		Box box4=Box.createHorizontalBox();
		box4.add(new JLabel(" ����:",JLabel.CENTER));
		box4.add( ����);
		Box box5=Box.createHorizontalBox();
		box5.add(new JLabel(" ����:",JLabel.CENTER));
		box5.add( ����);
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
		����.setText(null);
		����.setText(null);
		����.setText(null);
		if(e.getSource()== ��ѯ||e.getSource()== Ա����){
			String number="";
			number= Ա����.getText();
			if(number.length()>0){
				try {
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					������Ϣ��=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				if( ������Ϣ��.containsKey(number)){
					Employee stu=(Employee) ������Ϣ��.get(number);
					����.setText(stu.getName());
					����.setText(stu.getDisciping());
					����.setText(stu.getGrade());
					if(stu.getSex().equals(" ��")){
						��.setSelected(true);
					}else{
						Ů.setSelected(true);
					} 
				}else{
					String warning=" ��Ա���Ų�����!";
					JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				String warning=" ����Ҫ����Ա����!";
				JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}