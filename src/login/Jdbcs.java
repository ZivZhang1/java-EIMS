package login;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
import javax.swing.JOptionPane;  
  
public class Jdbcs {
	Connection con = null;  
	Statement statement = null;  
	ResultSet res = null;  
	String driver = "com.mysql.cj.jdbc.Driver";  
	String url  = "jdbc:mysql://localhost:3306/coursedesign?serverTimezone=UTC&useSSL=true";  
	String name = "root";  
	String passwd = "111111";  
	  
	public Jdbcs(){  
	    try{  
	    //�������ݿ��������
	    Class.forName(driver);  
	    //���ӵ����ݿ�
	    con = DriverManager.getConnection(url,name,passwd);  
	    statement = con.createStatement();  
	      
	    }catch(ClassNotFoundException e){  
	        System.out.println("�Բ����Ҳ������Driver");  
	        e.printStackTrace();  
	    }catch(SQLException e){  
	        e.printStackTrace();  
	    }catch(Exception e){  
	        e.printStackTrace();  
	        }  
	}

	//���û���Ϣ���޸�ʵ���Ͼ��Ƕ�������޸�  
	public boolean update(String loginname,String password,String newpassword){  
	    boolean judge = false;  
	    boolean s =compare(loginname,password);  
	    if(s){  
	    String sql = "update login set password=\""+newpassword+"\"where loginname=\""+loginname+"\"";  
	    try {  
	        int a = statement.executeUpdate(sql);  
	        if(a==1){  
	            JOptionPane.showMessageDialog(null,"�����޸ĳɹ���");  
	            judge = true;  
	        }  
	        con.close();  
	        statement.close();
	    } catch (SQLException e) {  
	        JOptionPane.showMessageDialog(null, "�û������ڣ�");  
	        e.printStackTrace();  
	    }  
	    }  
	    else{  
	         JOptionPane.showMessageDialog(null, "�޸�ʧ��");  
	    }  
	    return judge;  
	}  
	
	//�Ա��û����������ǲ�ƥ��  
	public boolean compare(String loginname,String password){  
	    boolean m = false;  
	    String sql = "select password from login where loginname=\""+loginname+"\"";  
	    try{  
	        res = statement.executeQuery(sql);  
	        if(res.next()){  
	            String pa = res.getString(1);  
	            System.out.println(pa+" " +password);  
	            if(pa.equals(password)){  
	                m = true;  
	            }else {  
	                JOptionPane.showMessageDialog(null, "�������");  
	            }  
	        }else {  
	            JOptionPane.showMessageDialog(null, "�û��������ڣ�");  
	        }  
	        res.close();  
	        con.close();  
	        statement.close();  
	          
	    }catch(SQLException e){  
	        e.printStackTrace();  
	    }  
	    return m;  
	}
}