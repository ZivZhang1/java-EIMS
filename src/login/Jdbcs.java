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
	    //加载数据库访问驱动
	    Class.forName(driver);  
	    //连接到数据库
	    con = DriverManager.getConnection(url,name,passwd);  
	    statement = con.createStatement();  
	      
	    }catch(ClassNotFoundException e){  
	        System.out.println("对不起，找不到这个Driver");  
	        e.printStackTrace();  
	    }catch(SQLException e){  
	        e.printStackTrace();  
	    }catch(Exception e){  
	        e.printStackTrace();  
	        }  
	}

	//对用户信息的修改实际上就是对密码的修改  
	public boolean update(String loginname,String password,String newpassword){  
	    boolean judge = false;  
	    boolean s =compare(loginname,password);  
	    if(s){  
	    String sql = "update login set password=\""+newpassword+"\"where loginname=\""+loginname+"\"";  
	    try {  
	        int a = statement.executeUpdate(sql);  
	        if(a==1){  
	            JOptionPane.showMessageDialog(null,"密码修改成功！");  
	            judge = true;  
	        }  
	        con.close();  
	        statement.close();
	    } catch (SQLException e) {  
	        JOptionPane.showMessageDialog(null, "用户不存在！");  
	        e.printStackTrace();  
	    }  
	    }  
	    else{  
	         JOptionPane.showMessageDialog(null, "修改失败");  
	    }  
	    return judge;  
	}  
	
	//对比用户名和密码是不匹配  
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
	                JOptionPane.showMessageDialog(null, "密码错误！");  
	            }  
	        }else {  
	            JOptionPane.showMessageDialog(null, "用户名不存在！");  
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