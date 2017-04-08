
package az.main;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentSQL {
    
    private Connection connection = null;
    private Statement statement = null;
    
    private void connected(){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fxbase?zeroDateTimeBehavior=convertToNull","root","123456");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void closed(){
        try {
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
    
    public void insertStudent(StudentPojo pojo){
        try {
            connected();
            String sql = "Insert into Student values("+pojo.getId()+",'"+pojo.getName()+"','"+pojo.getSurname()+"',"+pojo.getAge()+",'"+pojo.getEmail()+"')";
            statement.executeUpdate(sql);
            System.out.println(sql);
            closed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<StudentPojo> listStudent(){
        try {
            connected();
            ObservableList<StudentPojo>list = FXCollections.observableArrayList();
            ResultSet rs = statement.executeQuery("Select * from Student");
            while(rs.next()){
                StudentPojo pojo = new StudentPojo();
                pojo.setId(rs.getInt(1));
                pojo.setName(rs.getString(2));
                pojo.setSurname(rs.getString(3));
                pojo.setAge(rs.getInt(4));
                pojo.setEmail(rs.getString(5));
                list.add(pojo);
                System.out.println(String.valueOf(list));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
        closed();
        }        
    }
     public List<StudentPojo> listStudent2(){
        try {
            connected();
            List<StudentPojo>list =new ArrayList<StudentPojo>();
            ResultSet rs = statement.executeQuery("Select * from Student");
            while(rs.next()){
                StudentPojo pojo = new StudentPojo();
                pojo.setId(rs.getInt(1));
                pojo.setName(rs.getString(2));
                pojo.setSurname(rs.getString(3));
                pojo.setAge(rs.getInt(4));
                pojo.setEmail(rs.getString(5));
                list.add(pojo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
        closed();
        }        
    }
    
    public void updateStudent(StudentPojo pojo){
        try {
            connected();
            String sql = "Update student set name='"+pojo.getName()+"',surname = '"+pojo.getSurname()+"',age = "+pojo.getAge()+",email = '"+pojo.getEmail()+"' Where id = "+pojo.getId();
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception e) {
        }
        finally{
        closed();
        }
    }
    
    public void deleteStudent(int id){
        try {
            connected();
            statement.executeUpdate("Delete from student where id = "+id);
        } catch (Exception e) {
        }finally{
           closed();
        }
}
    
     public int studentMaxID() {
        try {
            connected();
            int max = 1;
            ResultSet rs = statement.executeQuery("Select max(id) From student");
            rs.next();
            max = rs.getInt(1);
            rs.close();
            closed();
            return max + 1;
        } catch (Exception e) {
            return 0;
        }
    }
     
     public StudentPojo findByID(int id){
         try {
             connected();
             ResultSet rs = statement.executeQuery("Select * from Student where id="+id);
             StudentPojo pojo = new StudentPojo();
             while(rs.next()){
                 pojo.setId(rs.getInt(1));
                 pojo.setName(rs.getString(2));
                 pojo.setSurname(rs.getString(3));
                 pojo.setAge(rs.getInt(4));
                 pojo.setEmail(rs.getString(5));                
             }
             return pojo;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }finally{
         closed();
         }
     }

}
