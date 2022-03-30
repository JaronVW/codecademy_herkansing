import Logic.StudentManager;
import UI.CourseOverview;
import UI.EnrollmentOverview;
import UI.StudentOverview;
import javafx.application.Application;
import javafx.geometry.Insets;
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
        stage.setMinHeight(500);
        stage.setMinWidth(800);


        BorderPane borderPane = new BorderPane();
        Text title = new Text(stageTitle);
        borderPane.setTop(title);

        HBox layout = new HBox();

        Button studentScene = new Button(studentSceneButton);
        Button enrollmentScene = new Button(enrollmentSceneButton);
        Button courseScene = new Button(courseSceneButton);




        Insets buttonPadding = new Insets(10);
        studentScene.setPadding(buttonPadding);
        enrollmentScene.setPadding(buttonPadding);
        courseScene.setPadding(buttonPadding);

        layout.getChildren().addAll(studentScene, enrollmentScene, courseScene);
        borderPane.setCenter(layout);


        Scene home = new Scene(borderPane);
        try {
            home.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }catch (Exception e){
            System.out.println(e);
        }

        stage.setScene(home);
        stage.show();

        StudentManager courseManager = new StudentManager();
        System.out.println(courseManager.allStudents());


        studentScene.setOnAction(actionEvent -> {
            stage.setScene( new StudentOverview(home,stage).getStudentOverview());
        });

        courseScene.setOnAction(actionEvent -> {

            stage.setScene(new CourseOverview(home,stage).getCourseOverview());
        });

        enrollmentScene.setOnAction(actionEvent -> {
            stage.setScene(new EnrollmentOverview(home,stage).getEnrollmentOverview());
        });




    }
}
