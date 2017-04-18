
package az.OknaINSERT.Druzyn;

import az.LogikaSQL.Dane.Druzyna;
import az.LogikaSQL.ZarzadzanieSQL;
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
    private TextField nick,admin,email;
    private ZarzadzanieSQL ssql = new ZarzadzanieSQL();
    @FXML
    private void insert(ActionEvent event){
        Druzyna pojo = new Druzyna();
        System.out.println(ssql.studentMaxID());
        pojo.setId(ssql.studentMaxID());
        pojo.setNick(nick.getText());
        pojo.setEmail(email.getText());
        pojo.setIsAdmin(Integer.parseInt(admin.getText()));
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
