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
	Hashtable 基本信息=null;
	File file=null;
	Inquest 基本信息查询=null;
	
	private JPanel pan = new JPanel();  
	private JLabel namelab = new JLabel("用户名");  
	private JLabel passlab = new JLabel("密    码");  
	private JTextField nametext = new JTextField();  
	private JPasswordField passtext = new JPasswordField();  
	  
	public JButton denglu = new JButton("登录");  
	public JButton updatepass = new JButton("修改密码");
	  
	public Login(){
		Image image = new ImageIcon("src\\background.jpg").getImage(); 
		
	    Font font = new Font("宋体",Font.BOLD,12);  
	    super.setTitle("员工信息管理系统");  
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
	    
	    
	    基本信息=new Hashtable();
	    file = new File(" 基本信息.txt");
		if(!file.exists()){
			try{
				FileOutputStream out=new FileOutputStream(file);
				ObjectOutputStream objectOut=new ObjectOutputStream(out);
				objectOut.writeObject( 基本信息);
				objectOut.close();
				out.close();
			}
			catch(IOException e){
			}
		}
		基本信息查询=new Inquest(null,file);
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
    //登录按钮的事件处理函数  
    public void denglu(){
     Jdbcs d  = new Jdbcs();  
     String loginname = nametext.getText();  
     String password = passtext.getText();  
      if(d.compare(loginname, password)){
    	  JOptionPane.showMessageDialog(null,"登录成功！");
    	  if(loginname.equals("admin")) {
    		  new EmployeeManager();
    	  }else {
    	  基本信息查询.setVisible(true);
    	  }
          super.setVisible(false);
      }  
    }  

    //修改密码按钮触发后的事件处理函数  
    public void updatepass(){  
        pan.setEnabled(false);  
        JFrame frame1 = new JFrame("密码修改");  
        frame1.setSize(250, 200);  
        JPanel updatepass = new JPanel();  
        JLabel namelab1 = new JLabel("用户名");  
        JLabel passlab1 = new JLabel("旧密码");  
        JLabel newpasslab = new JLabel("新密码");  
        JTextField nametext1  = new JTextField(""+nametext.getText());  
        JPasswordField passtext1 = new JPasswordField();  
        JPasswordField newpasstext  = new JPasswordField();  
        JButton ok = new JButton("修改");  
        JButton resert = new JButton("重置");  
          
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
        //重置文本框 里的内容  
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