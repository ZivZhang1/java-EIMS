package login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.Hashtable;
public class EmployeeManager extends JFrame implements ActionListener{
	EmployeeSituation ������Ϣ¼��=null;
	ModifySituation ������Ϣ�޸�=null;
	Inquest ������Ϣ��ѯ=null;
	Delete ������Ϣɾ��=null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem ¼��, �޸�, ��ѯ, ɾ��;
	Container con=null;
	Hashtable ������Ϣ=null;
	File file=null;
	CardLayout card=null;
	JLabel label=null;
	JPanel pCenter;
	public EmployeeManager(){
		¼��=new JMenuItem(" ¼��Ա��������Ϣ");
		�޸�=new JMenuItem(" �޸�Ա��������Ϣ");
		��ѯ=new JMenuItem(" ��ѯԱ��������Ϣ");
		ɾ��=new JMenuItem(" ɾ��Ա��������Ϣ");
		bar=new JMenuBar();
		fileMenu=new JMenu(" �˵�ѡ��");
		fileMenu.add( ¼��);
		fileMenu.add( �޸�);
		fileMenu.add( ��ѯ);
		fileMenu.add( ɾ��);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label=new JLabel(" ��ӭ����Ա����Ϣ����ϵͳ",JLabel.CENTER);
		label.setFont(new Font("SansSerif",Font.BOLD+Font.ITALIC,25));
		label.setForeground(Color.red);
		������Ϣ=new Hashtable();
		¼��.addActionListener(this);
		�޸�.addActionListener(this);
		��ѯ.addActionListener(this);
		ɾ��.addActionListener(this);
		card=new CardLayout();
		con=getContentPane();
		pCenter=new JPanel();
		pCenter.setLayout(card);
		pCenter.setBackground(Color.yellow);
		file=new File(" ������Ϣ.txt");
		if(!file.exists()){
			try{
				FileOutputStream out=new FileOutputStream(file);
				ObjectOutputStream objectOut=new ObjectOutputStream(out);
				objectOut.writeObject( ������Ϣ);
				objectOut.close();
				out.close();
			}
			catch(IOException e){
			}
		}
		������Ϣ¼��=new EmployeeSituation(file);
		������Ϣ�޸�=new ModifySituation(file);
		������Ϣ��ѯ=new Inquest(this,file);
		������Ϣɾ��=new Delete(file);
		pCenter.add(" ��ӭ�����",label);
		pCenter.add(" ¼�����", ������Ϣ¼��);
		pCenter.add(" �޸Ľ���", ������Ϣ�޸�);
		pCenter.add(" ɾ������", ������Ϣɾ��);
		con.add(pCenter,BorderLayout.CENTER);
		con.validate();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setVisible(true);
		setBounds(100,50,420,380);
		validate();
	}
	//�¼�����
	public void actionPerformed(ActionEvent e){
		if(e.getSource()== ¼��){
		card.show(pCenter," ¼�����");
		}else if(e.getSource()== �޸�){
		card.show(pCenter," �޸Ľ���");
		}else if(e.getSource()== ��ѯ){
		������Ϣ��ѯ.setVisible(true);
		}else if(e.getSource()== ɾ��){
		card.show(pCenter," ɾ������");
		}
	}
	public static void main(String args[]){
		new EmployeeManager();
	}
}