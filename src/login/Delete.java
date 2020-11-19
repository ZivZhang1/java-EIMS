package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Delete extends JPanel implements ActionListener{
	Hashtable ������Ϣ��=null;
	JTextField Ա����, ����, ����, ����;
	JRadioButton ��, Ů;
	JButton ɾ��;
	ButtonGroup group=null;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public Delete(File file){
		this.file=file;
		Ա����=new JTextField(10);
		ɾ��=new JButton(" ɾ��");
		Ա����.addActionListener(this);
		ɾ��.addActionListener(this);
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
		box1.add(new JLabel(" ����Ҫɾ����ѧ��:",JLabel.CENTER));
		box1.add( Ա����);
		box1.add( ɾ��);
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
		validate();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== ɾ��||e.getSource()== Ա����){
			String number="";
			number=Ա����.getText();
			if(number.length()>0){
				try{
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					������Ϣ��=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}catch(Exception ee){
				}
				//�ڻ�����Ϣ���в鿴�Ƿ��и�Ա��
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
					String m=" ȷ��Ҫɾ����Ա���ż�ȫ����Ϣ��?";
					int ok=JOptionPane.showConfirmDialog(this,m," ȷ��",JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
					if(ok==JOptionPane.YES_OPTION){
						������Ϣ��.remove(number);
						try{
							outOne=new FileOutputStream(file);
							outTwo=new ObjectOutputStream(outOne);
							outTwo.writeObject( ������Ϣ��);
							outTwo.close();
							outOne.close();
							Ա����.setText(null);
							����.setText(null);
							����.setText(null);
							����.setText(null);
						}catch(Exception ee){
							System.out.println(ee);
						}
					}else if(ok==JOptionPane.NO_OPTION){	
						Ա����.setText(null);
						����.setText(null);
						����.setText(null);
						����.setText(null);
					}
				}else{
					String warning=" ��Ա���Ų�����!";
					JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
				}
			}else{
				String warning=" ����Ҫ����Ա����!";
				JOptionPane.showMessageDialog(this,warning," ����",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}