
package az.LogikaSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import az.LogikaSQL.Dane.Druzyna;
import az.LogikaSQL.Dane.Kary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.pool.OracleDataSource;

public class ZarzadzanieSQL {
    
    private Connection connection = null;
    private Statement statement = null;
    
    private void connected(){
        try {
          /*  DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","wpatryk","wpatryk123");
            statement = connection.createStatement();*/

            OracleDataSource ds;
            ds = new OracleDataSource();
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
            ds.setURL(jdbcUrl);
            connection=ds.getConnection("wpatryk","wpatryk123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
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
    
    public void insertStudent(Druzyna pojo){
        try {
            connected();
            String sql = "Insert into Uzytkownicy values("+pojo.getId()+",'"+pojo.getNick()+"','"+pojo.getEmail()+"',"+pojo.getIsAdmin()+")";
            statement.executeUpdate(sql);
            System.out.println(sql);
            closed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<Druzyna> listStudent(){
        try {
            connected();
            ObservableList<Druzyna>list = FXCollections.observableArrayList();
            ResultSet rs = statement.executeQuery("Select * from Uzytkownicy");
            while(rs.next()){
                Druzyna pojo = new Druzyna();
                pojo.setId(rs.getInt(1));
                pojo.setNick(rs.getString(2));
                pojo.setEmail(rs.getString(3));
                pojo.setIsAdmin(rs.getInt(4));
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

    public ObservableList<Kary> listDzialy(){
        try {
            connected();
            ObservableList<Kary>list = FXCollections.observableArrayList();
            ResultSet rs = statement.executeQuery("Select * from Dzialy");
            while(rs.next()){
                Kary pojo = new Kary();
                pojo.setIdDzialy(rs.getString(1));
                pojo.setIdUzytkownicy(rs.getInt(2));
                pojo.setNazwa(rs.getString(3));
                list.add(pojo);
                System.out.println(pojo.getIdDzialy()+" "+pojo.getIdUzytkownicy()+" "+pojo.getNazwa());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            closed();
        }
    }
     public List<Druzyna> listStudent2(){
        try {
            connected();
            List<Druzyna>list =new ArrayList<Druzyna>();
            ResultSet rs = statement.executeQuery("Select * from Uzytkownicy");
            while(rs.next()){
                Druzyna pojo = new Druzyna();
                pojo.setId(rs.getInt(1));
                pojo.setNick(rs.getString(2));
                pojo.setEmail(rs.getString(3));
                pojo.setIsAdmin(rs.getInt(4));
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
    
    public void updateStudent(Druzyna pojo)  {
        try {
            connected();
            String sql = "Update Uzytkownicy set nick='"+pojo.getNick()+"', email = '"+pojo.getEmail()+"', admin = "+pojo.getIsAdmin()+" Where id_uzytkownika = "+pojo.getId();
            System.out.println(sql);
            statement.executeUpdate(sql);
            //connection.commit();


        } catch (Exception e) {
        }finally {
            closed();
        }


    }
    
    public void deleteStudent(int id){
        try {
            connected();
            statement.executeUpdate("Delete from Uzytkownicy where id_uzytkownika = "+id);
        } catch (Exception e) {
        }finally{
           closed();
        }
}
    
     public int studentMaxID() {
        try {
            connected();
            int max = 1;
            ResultSet rs = statement.executeQuery("Select max(id_uzytkownika) From Uzytkownicy");
            rs.next();
            max = rs.getInt(1);
            rs.close();
            closed();
            return max + 1;
        } catch (Exception e) {
            return 0;
        }
    }
     
     public Druzyna findByID(int id){
         try {
             connected();
             ResultSet rs = statement.executeQuery("Select * from Uzytkownicy where ID_UZYTKOWNIKA="+id);
             Druzyna pojo = new Druzyna();
             while(rs.next()){
                 pojo.setId(rs.getInt(1));
                 pojo.setNick(rs.getString(2));
                 pojo.setEmail(rs.getString(3));
                 pojo.setIsAdmin(rs.getInt(4));
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
