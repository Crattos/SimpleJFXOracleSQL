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
    private TableView<StudentPojo> tUzytkownicy;
    @FXML
    private TableView<DzialPojo> tDzialy;
    @FXML
    private TableColumn<StudentPojo,String> nick,email;
    @FXML
    private TableColumn<StudentPojo,String> nazwa;
    @FXML
    private TableColumn<StudentPojo,Integer> isAdmin,id;
    @FXML
    private TableColumn<StudentPojo,Integer> idUzytkownika, id_Dzialu;
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
        tUzytkownicy.setItems(list);
    }
    @FXML
    private void update(ActionEvent event){
        new StudentUppdateModel().start(new Stage());
    }                                       
    @FXML
    private void refresh(ActionEvent event){
        list = ssql.listStudent();
        listDzialy = ssql.listDzialy();
        tUzytkownicy.setItems(list);
        tDzialy.setItems(listDzialy);
    }
    @FXML
    private void delete(){

        StudentPojo pojo = tUzytkownicy.getSelectionModel().getSelectedItem();
        ssql.deleteStudent(pojo.getId());
        System.out.println(pojo.getId());
        list = ssql.listStudent();
        tUzytkownicy.setItems(list);
    }
    @FXML
    private void selectRow(MouseEvent event){
        if(event.getClickCount()==2){           
           StudentPojo pojo = tUzytkownicy.getSelectionModel().getSelectedItem();
            StudenUpdateViewController.id = pojo.getId();
            System.out.println(StudenUpdateViewController.id);
            new StudentUppdateModel().start(new Stage());
        }                                               
    }                                           
                                                
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        id.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id"));
        nick.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("nick"));
        email.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("email"));
        isAdmin.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("isAdmin"));

        id_Dzialu.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id_dzialu"));
        idUzytkownika.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id_uzytkownika"));
        nazwa.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("nazwa"));

        tUzytkownicy.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tDzialy.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        list = ssql.listStudent();
        listDzialy = ssql.listDzialy();
        tUzytkownicy.setItems(list);
        tDzialy.setItems(listDzialy);
    }
}
