import UI.CourseOverview;
import UI.EnrollmentOverview;
import UI.StudentOverview;
import UI.WebcastOverview;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        final String studentSceneButton = "View and edit students";
        final String enrollmentSceneButton = "View and edit enrollments";
        final String courseSceneButton = "View courses";
        final String webcastDataButton = "Webcasts";


        stage.setTitle(stageTitle + "Home");
        stage.setHeight(500);
        stage.setWidth(800);


        BorderPane borderPane = new BorderPane();
        Text title = new Text(stageTitle);
        borderPane.setTop(title);

        VBox layout = new VBox();

        Button studentScene = new Button(studentSceneButton);
        Button enrollmentScene = new Button(enrollmentSceneButton);
        Button courseScene = new Button(courseSceneButton);
        Button webcastScene =  new Button(webcastDataButton);




        Insets buttonPadding = new Insets(10);
        studentScene.setPadding(buttonPadding);
        enrollmentScene.setPadding(buttonPadding);
        courseScene.setPadding(buttonPadding);
        webcastScene.setPadding(buttonPadding);

        layout.getChildren().addAll(studentScene, enrollmentScene, courseScene,webcastScene);
        borderPane.setCenter(layout);


        Scene home = new Scene(borderPane);

        stage.setScene(home);
        stage.show();

        studentScene.setOnAction(actionEvent -> {
            stage.setScene( new StudentOverview(home,stage).getStudentOverview());
        });

        courseScene.setOnAction(actionEvent -> {

            stage.setScene(new CourseOverview(home,stage).getCourseOverview());
        });

        enrollmentScene.setOnAction(actionEvent -> {
            stage.setScene(new EnrollmentOverview(home,stage).getEnrollmentOverview());
        });

        webcastScene.setOnAction(actionEvent -> {
            stage.setScene(new WebcastOverview(home,stage).getWebcastOverview());
        });
        //creates a simple menu with buttons to pages
    }
}
