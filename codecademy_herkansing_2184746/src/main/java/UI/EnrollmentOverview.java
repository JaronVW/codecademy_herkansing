package UI;

import Domain.*;
import Logic.EnrollmentManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EnrollmentOverview extends OverviewElements {

    public EnrollmentOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
    }

    public  Scene getEnrollmentOverview(){
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Enrollments");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();
        EnrollmentManager enrollmentManager = new EnrollmentManager();
        ArrayList<Enrollment> enrollments = enrollmentManager.allEnrollments();

        TableView<Enrollment> table = new TableView<>();
        table.getItems().addAll(enrollments);

        TableColumn<Enrollment, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("emailaddress"));

        TableColumn<Enrollment,String> courseNameCol = new TableColumn<>("Course name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory("courseName"));

        TableColumn<Enrollment,String> registerDateCol = new TableColumn<>("Register date");
        registerDateCol.setCellValueFactory(new PropertyValueFactory("registerDate"));


        table.getColumns().setAll(emailCol,courseNameCol,registerDateCol);

        layout.setCenter(table);
        BorderPane.setMargin(table,getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar,getSidebarInsets());

        Button deleteEnrollment = new Button("Delete enrollment");

        deleteEnrollment.setOnAction(actionEvent -> {
            Enrollment enrollment = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(enrollment);
            enrollmentManager.deleteEnrollment(enrollment);
            table.refresh();
        });

        Button editEnrollment = new Button("Edit enrollment");
        Button addEnrollment = new Button("Add enrollment");

        Node bottomBar = getCRUDButtons(deleteEnrollment,editEnrollment,addEnrollment);
        layout.setBottom(bottomBar);

        return new Scene(layout);
    }
}
