package az.OknaUPDATE.Druzyn;

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

public class StudenUpdateViewController implements Initializable {

    @FXML
    Button btnCancel, btnInsert;
    @FXML
    private TextField nick, email, isAdmin ;
    ZarzadzanieSQL ssql = new ZarzadzanieSQL();
    public static Integer id;
    private Druzyna pojo = new Druzyna();
    @FXML
    private void insert(ActionEvent event)  {

        Stage stage = (Stage) btnInsert.getScene().getWindow();
        pojo.setId(id);
        pojo.setNick(nick.getText());
        pojo.setEmail(email.getText());
        pojo.setIsAdmin(Integer.parseInt(isAdmin.getText()));
        ssql.updateStudent(pojo);
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Druzyna pojo = ssql.findByID(id);
         System.out.println(id);
         nick.setText(pojo.getNick());
         email.setText(pojo.getEmail());
         isAdmin.setText(String.valueOf(pojo.getIsAdmin()));

    }
}
