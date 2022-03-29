package UI;

import Domain.Gender;
import Domain.Mail;
import Domain.Student;
import Domain.ValidatedDate;
import Logic.StudentManager;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.*;

public class StudentOverview {

    public static Scene getStudentOverview(Stage stage){

        HBox layout = new HBox();

        StudentManager studentManager = new StudentManager();
        ArrayList<Student> students = studentManager.allStudents();

        TableView<Student> table = new TableView<>();
        table.getItems().addAll(students);

        TableColumn<Student,Mail> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("emailaddress"));

        TableColumn<Student,String> firstnameCol = new TableColumn<>("First Name");
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn<Student,String> lastnameCol = new TableColumn<>("Last Name");
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn<Student, String> DOBCol = new TableColumn<>("Date of birth");
        DOBCol.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));

        TableColumn<Student, Gender> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));

        TableColumn<Student,String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory("address"));

        TableColumn<Student,String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));

        TableColumn<Student,String> zipcodeCol = new TableColumn<>("Zipcode");
        zipcodeCol.setCellValueFactory(new PropertyValueFactory("zipcode"));

        TableColumn<Student,String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(new PropertyValueFactory("city"));

        TableColumn<Student,String> countryCol = new TableColumn<>("Country");
        countryCol.setCellValueFactory(new PropertyValueFactory("country"));

        table.getColumns().setAll(emailCol,firstnameCol,lastnameCol,DOBCol,genderCol,addressCol,zipcodeCol,cityCol,countryCol);

        layout.getChildren().add(table);

        return new Scene(layout);
    }
}
