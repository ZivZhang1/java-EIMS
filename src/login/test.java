package login;

import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 public class test {
 
     public static void main(String[] args) {
         //����Connection����
         Connection con;
         //����������
         String driver = "com.mysql.cj.jdbc.Driver";
         //URLָ��Ҫ���ʵ����ݿ���coursedesign
         String url = "jdbc:mysql://localhost:3306/coursedesign?serverTimezone=UTC&useSSL=true";
         //MySQL����ʱ���û���
         String user = "root";
         //MySQL����ʱ������
         String password = "111111";
         //������ѯ�����
         try {
             //������������
             Class.forName(driver);
             //1.getConnection()����������MySQL���ݿ⣡��
             con = DriverManager.getConnection(url,user,password);
             if(!con.isClosed())
                 System.out.println("Succeeded connecting to the Database!");
             //2.����statement���������ִ��SQL��䣡��
             Statement statement = con.createStatement();
             //Ҫִ�е�SQL���
             String sql = "select * from login";
             //3.ResultSet�࣬������Ż�ȡ�Ľ��������
             ResultSet rs = statement.executeQuery(sql);
              
             String mm = null;
             String id = null;
             while(rs.next()){
                 //��ȡpassword��������
                 mm = rs.getString("password");
                 //��ȡloginname��������
                 id = rs.getString("loginname");
 
                 //������
                 System.out.println(id + "\t" + mm);
             }
             rs.close();
             con.close();
         } catch(ClassNotFoundException e) {   
             //���ݿ��������쳣����
             System.out.println("Sorry,can`t find the Driver!");   
             e.printStackTrace();   
             } catch(SQLException e) {
             //���ݿ�����ʧ���쳣����
             e.printStackTrace();  
             }catch (Exception e) {
             // TODO: handle exception
             e.printStackTrace();
         }finally{
             System.out.println("���ݿ����ݳɹ���ȡ����");
         }
     }
 
 }