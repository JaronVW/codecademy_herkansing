import Database.CourseDAO;
import Database.DatabaseConnection;
import Database.EnrollmentDAO;
import Database.StudentDAO;
import Domain.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;

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


        StudentDAO studentDAO = new StudentDAO();
        System.out.println(
                studentDAO.updateStudent(new Student(new Mail("jj@.f"),
                        "nee",
                        "misschien",
                        new ValidatedDate(19, 1, 2002),
                        Gender.NB, "a",
                        new Zipcode("1234aa"),
                        "Dordrecht",
                        "ja"),new Mail("ff@f.f")));


    }
}
