package az.mb;

import az.dialog.DialogStudent;
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
    private TableView<StudentPojo> table;
    @FXML
    private TableColumn<StudentPojo,String>name,surname,email;
    @FXML
    private TableColumn<StudentPojo,Integer>age,id;
    @FXML
    private ObservableList<StudentPojo> list = FXCollections.observableArrayList();
    StudentSQL ssql = new StudentSQL();
    @FXML
    private Button btnInsert,btnDelete,btnRefresh;
    
    @FXML
    private void insert(ActionEvent event) throws IOException{
        new DialogStudent().start(new Stage());
        list = ssql.listStudent();
        table.setItems(list);
    }
    @FXML
    private void update(ActionEvent event){
        new StudentUppdateModel().start(new Stage());
    }                                       
    @FXML
    private void refresh(ActionEvent event){
        list = ssql.listStudent();
        table.setItems(list);
    }
    @FXML
    private void delete(){
        StudentPojo pojo = table.getSelectionModel().getSelectedItem();
        ssql.deleteStudent(pojo.getId());
        System.out.println(pojo.getId());
        list = ssql.listStudent();
        table.setItems(list);
    }
    @FXML
    private void selectRow(MouseEvent event){
        if(event.getClickCount()==2){           
           StudentPojo pojo = table.getSelectionModel().getSelectedItem();
            StudenUpdateViewController.id = pojo.getId();
            System.out.println(StudenUpdateViewController.id);
            new StudentUppdateModel().start(new Stage());
        }                                               
    }                                           
                                                
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        id.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("id"));
        age.setCellValueFactory(new PropertyValueFactory<StudentPojo,Integer>("age"));
        name.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<StudentPojo,String>("email"));        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        list = ssql.listStudent();
        table.setItems(list);
        
    }
}
