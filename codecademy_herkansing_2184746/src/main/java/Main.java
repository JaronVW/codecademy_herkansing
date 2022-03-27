import Logic.CourseManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        final String studentSceneButton = "View and edit students";
        final String enrollmentSceneButton = "View and edit enrollments";
        final String courseSceneButton = "View courses";


        stage.setTitle(stageTitle + "Home");
        stage.setHeight(500);
        stage.setWidth(500);


        BorderPane borderPane = new BorderPane();
        Text title = new Text(stageTitle);
        borderPane.setTop(title);

        HBox layout = new HBox();

        Button studentScene = new Button(studentSceneButton);
        Button enrollmentScene = new Button(enrollmentSceneButton);
        Button courseScene = new Button(courseSceneButton);
        layout.getChildren().addAll(studentScene, enrollmentScene, courseScene);
        borderPane.setCenter(layout);


        Scene home = new Scene(borderPane);
        stage.setScene(home);
        stage.show();

        CourseManager courseManager = new CourseManager();
        System.out.println(courseManager.allCourses());

    }
}
