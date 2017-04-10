package az.mb.update;

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

public class StudenUpdateViewController implements Initializable {

    @FXML
    Button btnCancel, btnInsert;
    @FXML
    private TextField name, surname, age, email;
    StudentSQL ssql = new StudentSQL();
    public static Integer id;
    private StudentPojo pojo = new StudentPojo();
    @FXML
    private void insert(ActionEvent event) {        
        pojo.setId(id);
        pojo.setNick(name.getText());
        pojo.setEmail(email.getText());
        pojo.setIsAdmin(Integer.parseInt(age.getText()));
        ssql.updateStudent(pojo);
        Stage stage = (Stage) btnInsert.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         StudentPojo pojo = ssql.findByID(id);
         System.out.println(id);
         name.setText(pojo.getNick());
         email.setText(pojo.getEmail());
        age.setText(String.valueOf(pojo.getIsAdmin()));

    }
}
