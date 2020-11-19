package login;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JPasswordField;  
import javax.swing.JTextField;  

public class Login extends JFrame implements ActionListener {
	Hashtable ������Ϣ=null;
	File file=null;
	Inquest ������Ϣ��ѯ=null;
	
	private JPanel pan = new JPanel();  
	private JLabel namelab = new JLabel("�û���");  
	private JLabel passlab = new JLabel("��    ��");  
	private JTextField nametext = new JTextField();  
	private JPasswordField passtext = new JPasswordField();  
	  
	public JButton denglu = new JButton("��¼");  
	public JButton updatepass = new JButton("�޸�����");
	  
	public Login(){
		Image image = new ImageIcon("src\\background.jpg").getImage(); 
		
	    Font font = new Font("����",Font.BOLD,12);  
	    super.setTitle("Ա����Ϣ����ϵͳ");  
	    pan.setLayout(null);  
	    namelab.setBounds(20,20,60,30);  
	    nametext.setBounds(90,20,140,30);  
	    passlab.setBounds(20,60,60,30);  
	    passtext.setBounds(90,60,140,30);  
	    denglu.setBounds(90,120,100,20);  
	    updatepass.setBounds(90,150,100,20);  
	      
	    pan.add(namelab);  
	    pan.add(nametext);  
	    pan.add(passlab);  
	    pan.add(passtext);  
	    pan.add(denglu);  
	    pan.add(updatepass);  
	      
	    passtext.setFont(font);  
	    updatepass.setFont(font);  
	      
	    denglu.addActionListener(this);  
	    updatepass.addActionListener(this);  
	      
	    super.add(pan);  
	    super.setSize(300,250);  
	    super.setVisible(true);
	    
	    
	    ������Ϣ=new Hashtable();
	    file = new File(" ������Ϣ.txt");
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
		������Ϣ��ѯ=new Inquest(null,file);
	}
	  
	public static void main(String []args){  
	      
	    new Login();  
	}
    @Override  
    public void actionPerformed(ActionEvent arg0) {  
	    if(arg0.getSource()==denglu){  
	        denglu();  
	    }else if (arg0.getSource()==updatepass){  
	        updatepass();  
	    }
    }
    //��¼��ť���¼�������  
    public void denglu(){
     Jdbcs d  = new Jdbcs();  
     String loginname = nametext.getText();  
     String password = passtext.getText();  
      if(d.compare(loginname, password)){
    	  JOptionPane.showMessageDialog(null,"��¼�ɹ���");
    	  if(loginname.equals("admin")) {
    		  new EmployeeManager();
    	  }else {
    	  ������Ϣ��ѯ.setVisible(true);
    	  }
          super.setVisible(false);
      }  
    }  

    //�޸����밴ť��������¼�������  
    public void updatepass(){  
        pan.setEnabled(false);  
        JFrame frame1 = new JFrame("�����޸�");  
        frame1.setSize(250, 200);  
        JPanel updatepass = new JPanel();  
        JLabel namelab1 = new JLabel("�û���");  
        JLabel passlab1 = new JLabel("������");  
        JLabel newpasslab = new JLabel("������");  
        JTextField nametext1  = new JTextField(""+nametext.getText());  
        JPasswordField passtext1 = new JPasswordField();  
        JPasswordField newpasstext  = new JPasswordField();  
        JButton ok = new JButton("�޸�");  
        JButton resert = new JButton("����");  
          
        updatepass.setLayout(null);  
          
        namelab1.setBounds(5,5,70,20);  
        nametext1.setBounds(80,5,120,20);  
        passlab1.setBounds(5,30,70,20);  
        passtext1.setBounds(80,30,120,20);  
        newpasslab.setBounds(5,60,70,20);  
        newpasstext.setBounds(80,60,120,20);  
        ok.setBounds(10,110,60,20);  
        resert.setBounds(90,110,60,20);  
          
        updatepass.add(namelab1);  
        updatepass.add(nametext1);  
        updatepass.add(passlab1);  
        updatepass.add(passtext1);  
        updatepass.add(newpasslab);  
        updatepass.add(newpasstext);  
        updatepass.add(ok);  
        updatepass.add(resert);  
          
        frame1.add(updatepass);  
        frame1.setVisible(true);  
          
        ok.addActionListener(new ActionListener(){  
  
            @Override  
            public void actionPerformed(ActionEvent arg0) {  
                Jdbcs d  =  new Jdbcs();  
                String loginname = nametext1.getText();  
                 String password1 = passtext1.getText();  
                 String newpassword = newpasstext.getText();  
                if(d.update(loginname,password1,newpassword)){  
                    frame1.setVisible(false);  
                }  
            }  
        });  
        //�����ı��� �������  
        resert.addActionListener(new ActionListener(){  
            @Override  
            public void actionPerformed(ActionEvent arg0) {  
                  
                nametext1.setText("");  
                passtext1.setText("");  
                newpasstext.setText("");  
            }  
        });  
    }
} 