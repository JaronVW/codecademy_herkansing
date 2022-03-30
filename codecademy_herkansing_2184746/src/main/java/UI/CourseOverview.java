package UI;

import Domain.Course;
import Logic.CourseManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CourseOverview extends OverviewElements {

    public CourseOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
    }

    public Scene getCourseOverview(){

        BorderPane layout = new BorderPane();
        CourseManager courseManager = new CourseManager();
        ArrayList<Course> courses = courseManager.allCourses();

        TableView<Course> table = new TableView<>();
        table.getItems().addAll(courses);

        TableColumn<Course, String> courseNameCol = new TableColumn<>("Course name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory("courseName"));

        TableColumn<Course,String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory("subject"));

        TableColumn<Course,String> introTextCol = new TableColumn<>("Intro text");
        introTextCol.setCellValueFactory(new PropertyValueFactory("introText"));

        TableColumn<Course, Integer> levelCol = new TableColumn<>("Level");
        levelCol.setCellValueFactory(new PropertyValueFactory("level"));

        table.getColumns().setAll(courseNameCol,subjectCol,introTextCol,levelCol);

        layout.setCenter(table);
        layout.setMargin(table,getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);

        return new Scene(layout);
    }
}
