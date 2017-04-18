package az.mb;

import az.dialog.DialogStudent;
import az.main.DzialPojo;
import az.main.StudentPojo;
import az.main.StudentSQL;
import az.mb.update.StudenUpdateViewController;
import az.mb.update.StudentUppdateModel;
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
    private TableView<StudentPojo> tDruzyna;
    @FXML
    private TableView<DzialPojo> tKara;
    @FXML
    private TableColumn<StudentPojo,String> idMiasta_druzyna, nazwaMiasta_druzyna;
    @FXML
    private TableColumn<StudentPojo,String> idPilkarza_kara;
    @FXML
    private TableColumn<StudentPojo,Integer> punkty, idDruzyna;
    @FXML
    private TableColumn<StudentPojo,Integer> idDruzyny_kara, idSpotkania_kara;
    @FXML
    private ObservableList<StudentPojo> list = FXCollections.observableArrayList();
    @FXML
    private ObservableList<DzialPojo> listDzialy = FXCollections.observableArrayList();
    private StudentSQL ssql = new StudentSQL();
    @FXML
    private Button btnInsert,btnDelete,btnRefresh;
    
    @FXML
    private void insert(ActionEvent event) throws IOException{

        new DialogStudent().start(new Stage());
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

        StudentPojo pojo = tDruzyna.getSelectionModel().getSelectedItem();
        ssql.deleteStudent(pojo.getId());
        System.out.println(pojo.getId());
        list = ssql.listStudent();
        tDruzyna.setItems(list);
    }
    @FXML
    private void selectRow(MouseEvent event){
        if(event.getClickCount()==2){           
           StudentPojo pojo = tDruzyna.getSelectionModel().getSelectedItem();
            StudenUpdateViewController.id = pojo.getId();
            System.out.println(StudenUpdateViewController.id);
            new StudentUppdateModel().start(new Stage());
        }                                               
    }                                           
                                                
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        idDruzyna.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id"));
        idMiasta_druzyna.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("nick"));
        nazwaMiasta_druzyna.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("email"));
        punkty.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("isAdmin"));

        idSpotkania_kara.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id_dzialu"));
        idDruzyny_kara.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id_uzytkownika"));
        idPilkarza_kara.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("nazwa"));

        tDruzyna.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tKara.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        list = ssql.listStudent();
        listDzialy = ssql.listDzialy();
        tDruzyna.setItems(list);
        tKara.setItems(listDzialy);
    }
}
