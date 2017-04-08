
package az.dialog;

import az.main.StudentPojo;
import az.main.StudentSQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogViewController implements Initializable {

    @FXML
    Button btnCancel,btnInsert;        
    @FXML
    private TextField name,surname,age,email;        
    StudentSQL ssql = new StudentSQL();
    @FXML
    private void insert(ActionEvent event){
        StudentPojo pojo = new StudentPojo(); 
        System.out.println(ssql.studentMaxID());
        pojo.setId(ssql.studentMaxID());
        pojo.setName(name.getText());
        pojo.setSurname(surname.getText());
        pojo.setAge(Integer.parseInt(age.getText()));
        pojo.setEmail(email.getText());
        ssql.insertStudent(pojo);
        Stage stage = (Stage)btnInsert.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancel(ActionEvent event){
     Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}
