package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
public class EmployeeSituation extends JPanel implements ActionListener{
	Hashtable ������Ϣ��=null;
	JTextField Ա����, ����, ����;
	Choice ����;
	JRadioButton ��, Ů;
	Employee Ա��=null;
	ButtonGroup group=null;
	JButton ¼��, ����;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public EmployeeSituation(File file){
		this.file=file;
		Ա����=new JTextField(10);
		����=new JTextField(10);
		����=new Choice();
		����.add(" ��ѡ��");
		����.add(" �з���");
		����.add(" ���۲�");
		����.add(" ���²�");
		����.add(" ��ȫ��");
		����=new JTextField(10);
		group=new ButtonGroup();
		��=new JRadioButton(" ��",true);
		Ů=new JRadioButton(" Ů",false);
		group.add( ��);
		group.add( Ů);
		¼��=new JButton(" ¼��");
		����=new JButton(" ����");
		¼��.addActionListener(this);
		����.addActionListener(this);
		Box box1=Box.createHorizontalBox();
		box1.add(new JLabel(" Ա����:",JLabel.CENTER));
		box1.add( Ա����);
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
		Box box6=Box.createHorizontalBox();
		box6.add(new JLabel(" ",JLabel.CENTER));
		Box box5=Box.createHorizontalBox();
		box5.add(new JLabel(" ����:",JLabel.CENTER));
		box5.add( ����);
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
		pSouth.add( ¼��);
		pSouth.add( ����);
		pSouth.setBackground(Color.yellow);
		add(pSouth,BorderLayout.SOUTH);
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== ¼��){
			String number="";
			//��ȡԱ����
			number= Ա����.getText();
			if(number.length()>0){
				try{
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					������Ϣ��=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				//�ж��Ƿ����Ա���Ļ�����Ϣ
				if( ������Ϣ��.containsKey(number)){
					String warning=" ��Ա��������Ϣ�Ѵ���, �뵽�޸�ҳ���޸�!";
					JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
				}else{
					//����Ϣ¼�������һ�ε�ȷ��
					String m=" ������Ϣ����¼��!";
					int ok=JOptionPane.showConfirmDialog(this,m," ȷ��",JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						//���������Ϣ���л�ȡ
						String name= ����.getText();
						String discipling= ����.getSelectedItem();
						String grade= ����.getText();
						String sex=null;
						if( ��.isSelected()){
							sex= ��.getText();
						}else{
							sex= Ů.getText();
						}
						Ա��=new Employee();
						Ա��.setNumber(number);
						Ա��.setName(name);
						Ա��.setDiscipling(discipling);
						Ա��.setGrade(grade);
						Ա��.setSex(sex);
						try{
							outOne=new FileOutputStream(file);
							outTwo=new ObjectOutputStream(outOne);
							������Ϣ��.put(number, Ա��);
							outTwo.writeObject( ������Ϣ��);
							outTwo.close();
							outOne.close();
							Ա����.setText(null);
							����.setText(null);
							����.setText(null);
						}catch(Exception ee){
							System.out.println(ee);
						}
					}
				}
			}
			else{
				String warning=" ����Ҫ����Ա����!";
				JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()== ����){
			Ա����.setText(null);
			����.setText(null);
			����.remove( ����.getSelectedIndex());
			����.setText(null);
		}
	}
	
}