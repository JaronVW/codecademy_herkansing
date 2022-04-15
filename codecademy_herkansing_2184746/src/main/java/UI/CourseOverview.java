package UI;

import Domain.Course;
import Logic.CourseManager;
import Logic.ModuleManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseOverview extends OverviewElements {

    public CourseOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
    }

    public Scene getCourseOverview() {

        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Students");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();
        CourseManager courseManager = new CourseManager();
        ArrayList<Course> courses = courseManager.allCourses();

        TableView<Course> table = new TableView<>();
        table.getItems().addAll(courses);

        TableColumn<Course, String> courseNameCol = new TableColumn<>("Course name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory("courseName"));

        TableColumn<Course, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory("subject"));

        TableColumn<Course, String> introTextCol = new TableColumn<>("Intro text");
        introTextCol.setCellValueFactory(new PropertyValueFactory("introText"));

        TableColumn<Course, Integer> levelCol = new TableColumn<>("Level");
        levelCol.setCellValueFactory(new PropertyValueFactory("level"));


        table.getColumns().setAll(courseNameCol, subjectCol, introTextCol, levelCol);

        Text completedCourses = new Text("Select a course to view the number of completed courses");
        table.setOnMouseClicked(e -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                completedCourses.setText(courseManager.numberOfCompletedCourses(selectedCourse.getCourseName()));
            }
        });

        HBox viewButtons = new HBox();
        Button modulePercentage = new Button("View the average completion percentage of the modules in this course");
        viewButtons.getChildren().addAll(modulePercentage);
        modulePercentage.setOnAction(actionEvent -> {
            Popup popup = courseSelector(table.getSelectionModel().getSelectedItem().getCourseName());
            popup.show(stage);
        });


        layout.setCenter(new VBox(table, completedCourses, viewButtons));
        BorderPane.setMargin(table, getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, getSidebarInsets());


        return new Scene(layout);
    }
    //returns a scene containing a table with all courses inherits overviewElements

    private Popup courseSelector(String courseName) {

        Popup menu = new Popup();
        menu.setOpacity(1f);
        menu.setHeight(500);
        menu.setWidth(500);

        VBox popupLayout = new VBox();
        popupLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popupLayout.setMinHeight(300);
        popupLayout.setMinWidth(400);

        popupLayout.setPadding(new Insets(10, 100, 10, 10));
        popupLayout.setSpacing(10);

        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            menu.hide();
        });

        final HashMap<String, Integer>[] hashMap = new HashMap[]{new HashMap<>()};
        hashMap[0] = new ModuleManager().ModulePercentage(courseName);
        VBox modulePercentages = new VBox();

        if (!hashMap[0].isEmpty()) {
            int i =1;
            for (Map.Entry<String, Integer> entry : hashMap[0].entrySet()) {
                modulePercentages.getChildren().add(new Text(i + ": Module: " +
                        entry.getKey() +
                        " avg percentage " +
                        entry.getValue()));
                i++;
            }
        }
        // select a course to view average percentage completion

        popupLayout.getChildren().addAll(modulePercentages, backButton);

        menu.getContent().add(popupLayout);


        return menu;
    }
}
