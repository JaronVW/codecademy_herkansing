package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public abstract class OverviewElements {

    private final Scene home;
    private final Stage stage;
    private final Insets tableInsets;
    private final Insets sidebarInsets;

    public OverviewElements(Scene homeScene, Stage stage) {
        this.home = homeScene;
        this.stage = stage;
        this. tableInsets = new Insets(10,20,10,20);
        this.sidebarInsets = new Insets(10,0,10,10);
    }

    public VBox getNavigationSidebar(){
        final String backToHomeButtonText = "Back";
        final String studentSceneButton = "Students";
        final String enrollmentSceneButton = "Enrollments";
        final String courseSceneButton = "Courses";
        final String webcastSceneButton = "Popular webcasts";

        VBox sidebar = new VBox();
        Button backToHome = new Button(backToHomeButtonText);

        Button studentScene = new Button(studentSceneButton);
        Button enrollmentScene = new Button(enrollmentSceneButton);
        Button courseScene = new Button(courseSceneButton);
        Button webcastScene = new Button(webcastSceneButton);

        backToHome.setOnAction(actionEvent -> {
            stage.setScene(home);
        });

        studentScene.setOnAction(actionEvent -> {
            stage.setScene( new StudentOverview(home,stage).getStudentOverview());
        });

        enrollmentScene.setOnAction(actionEvent -> {
            stage.setScene(new EnrollmentOverview(home,stage).getEnrollmentOverview());
        });

        courseScene.setOnAction(actionEvent -> {

            stage.setScene(new CourseOverview(home,stage).getCourseOverview());
        });

        webcastScene.setOnAction(actionEvent -> {

            stage.setScene(new WebcastOverview(home,stage).getWebcastOverview());
        });

        sidebar.getChildren().addAll(backToHome,studentScene,enrollmentScene,courseScene,webcastScene);
        return sidebar;
    }
    // contains a sidebar that can be inherited in scenes to create a menu structure

    public HBox getCRUDButtons(Button delete, Button edit, Button add){
        HBox layout = new HBox();

        layout.getChildren().addAll(delete,edit,add);

        return layout;
    }

    public Scene getHome() {
        return home;
    }

    public Stage getStage() {
        return stage;
    }

    public Insets getSidebarInsets() {
        return sidebarInsets;
    }

    public Insets getTableInsets() {
        return tableInsets;
    }
}
