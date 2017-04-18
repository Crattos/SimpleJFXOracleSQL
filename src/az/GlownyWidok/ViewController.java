package az.GlownyWidok;

import az.OknaINSERT.Druzyn.OkDruzyny;
import az.LogikaSQL.Dane.Kary;
import az.LogikaSQL.Dane.Druzyna;
import az.LogikaSQL.ZarzadzanieSQL;
import az.OknaUPDATE.Druzyn.StudenUpdateViewController;
import az.OknaUPDATE.Druzyn.StudentUppdateModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewController implements Initializable {
    
    @FXML
    private TableView<Druzyna> tDruzyna;
    @FXML
    private TableView<Kary> tKara;
    @FXML
    private TableColumn<Druzyna,String> idMiasta_druzyna, nazwaMiasta_druzyna;
    @FXML
    private TableColumn<Druzyna,String> idPilkarza_kara;
    @FXML
    private TableColumn<Druzyna,Integer> punkty, idDruzyna;
    @FXML
    private TableColumn<Druzyna,Integer> idDruzyny_kara, idSpotkania_kara;
    @FXML
    private ObservableList<Druzyna> list = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Kary> listDzialy = FXCollections.observableArrayList();
    private ZarzadzanieSQL ssql = new ZarzadzanieSQL();
    @FXML
    private Button btnInsert,btnDelete,btnRefresh;
    
    @FXML
    private void insert(ActionEvent event) throws IOException{

        new OkDruzyny().start(new Stage());
        list = ssql.listStudent();
        tDruzyna.setItems(list);
    }
    @FXML
    private void update(ActionEvent event){
        new StudentUppdateModel().start(new Stage());
    }                                       
    @FXML
    private void refresh(ActionEvent event){
        list = ssql.listStudent();
        listDzialy = ssql.listDzialy();
        tDruzyna.setItems(list);
        tKara.setItems(listDzialy);
    }
    @FXML
    private void delete(){

        Druzyna pojo = tDruzyna.getSelectionModel().getSelectedItem();
        ssql.deleteStudent(pojo.getId());
        System.out.println(pojo.getId());
        list = ssql.listStudent();
        tDruzyna.setItems(list);
    }
    @FXML
    private void selectRow(MouseEvent event){
        if(event.getClickCount()==2){           
           Druzyna pojo = tDruzyna.getSelectionModel().getSelectedItem();
            StudenUpdateViewController.id = pojo.getId();
            System.out.println(StudenUpdateViewController.id);
            new StudentUppdateModel().start(new Stage());
        }                                               
    }                                           
                                                
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        idDruzyna.setCellValueFactory(new PropertyValueFactory<Druzyna,Integer>("id"));
        idMiasta_druzyna.setCellValueFactory(new PropertyValueFactory<Druzyna,String>("nick"));
        nazwaMiasta_druzyna.setCellValueFactory(new PropertyValueFactory<Druzyna,String>("email"));
        punkty.setCellValueFactory(new PropertyValueFactory<Druzyna,Integer>("isAdmin"));

        idSpotkania_kara.setCellValueFactory(new PropertyValueFactory<Druzyna,Integer>("id_dzialu"));
        idDruzyny_kara.setCellValueFactory(new PropertyValueFactory<Druzyna,Integer>("id_uzytkownika"));
        idPilkarza_kara.setCellValueFactory(new PropertyValueFactory<Druzyna,String>("nazwa"));

        tDruzyna.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tKara.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        list = ssql.listStudent();
        listDzialy = ssql.listDzialy();
        tDruzyna.setItems(list);
        tKara.setItems(listDzialy);
    }
}
