package UI;

import Domain.Gender;
import Domain.Mail;
import Domain.Student;
import Logic.StudentManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class StudentOverview extends OverviewElements {

    public StudentOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
    }

    public Scene getStudentOverview() {
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Students");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();

        StudentManager studentManager = new StudentManager();
        ArrayList<Student> students = studentManager.allStudents();

        TableView<Student> table = new TableView<>();
        table.getItems().addAll(students);

        TableColumn<Student, Mail> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("emailaddress"));

        TableColumn<Student, String> firstnameCol = new TableColumn<>("First Name");
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));

        TableColumn<Student, String> lastnameCol = new TableColumn<>("Last Name");
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));

        TableColumn<Student, String> DOBCol = new TableColumn<>("Date of birth");
        DOBCol.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));

        TableColumn<Student, Gender> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));

        TableColumn<Student, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory("address"));

        TableColumn<Student, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));

        TableColumn<Student, String> zipcodeCol = new TableColumn<>("Zipcode");
        zipcodeCol.setCellValueFactory(new PropertyValueFactory("zipcode"));

        TableColumn<Student, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(new PropertyValueFactory("city"));

        TableColumn<Student, String> countryCol = new TableColumn<>("Country");
        countryCol.setCellValueFactory(new PropertyValueFactory("country"));

        table.getColumns().setAll(emailCol, firstnameCol, lastnameCol, DOBCol, genderCol, addressCol, zipcodeCol, cityCol, countryCol);

        layout.setCenter(table);
        BorderPane.setMargin(table, getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, getSidebarInsets());

        javafx.scene.control.Button deleteEnrollment = new javafx.scene.control.Button("Delete student");
        javafx.scene.control.Button editEnrollment = new javafx.scene.control.Button("Edit student");
        javafx.scene.control.Button addEnrollment = new Button("Add student");

        Node bottomBar = getCRUDButtons(deleteEnrollment, editEnrollment, addEnrollment);
        layout.setBottom(bottomBar);


        return new Scene(layout);
    }
}
