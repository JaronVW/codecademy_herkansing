package UI;

import Domain.*;
import Logic.EnrollmentManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EnrollmentOverview extends OverviewElements {
    private EnrollmentManager enrollmentManager;
    private TableView<Enrollment> table;

    public EnrollmentOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
        enrollmentManager = new EnrollmentManager();
    }

    public Scene getEnrollmentOverview() {
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Enrollments");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();

        ArrayList<Enrollment> enrollments = enrollmentManager.allEnrollments();

         table = new TableView<>();
        table.getItems().addAll(enrollments);

        TableColumn<Enrollment, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("emailaddress"));

        TableColumn<Enrollment, String> courseNameCol = new TableColumn<>("Course name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory("courseName"));

        TableColumn<Enrollment, String> registerDateCol = new TableColumn<>("Register date");
        registerDateCol.setCellValueFactory(new PropertyValueFactory("registerDate"));


        table.getColumns().setAll(emailCol, courseNameCol, registerDateCol);

        layout.setCenter(table);
        BorderPane.setMargin(table, getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, getSidebarInsets());

        Button deleteEnrollment = new Button("Delete enrollment");

        deleteEnrollment.setOnAction(actionEvent -> {
            Enrollment selectedEnrollment = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedEnrollment);
            enrollmentManager.deleteEnrollment(selectedEnrollment);
            table.refresh();
        });

        Button editEnrollment = new Button("Edit enrollment");
        editEnrollment.setOnAction(actionEvent -> {
            Enrollment selectedEnrollment = table.getSelectionModel().getSelectedItem();
            Popup popup = editEnrollmentMenu(selectedEnrollment);
            popup.show(stage);
        });


        Button addEnrollment = new Button("Add enrollment");

        addEnrollment.setOnAction(actionEvent -> {
            Popup popup = addEnrollmentMenu();
            popup.show(stage);
        });

        Node bottomBar = getCRUDButtons(deleteEnrollment, editEnrollment, addEnrollment);
        layout.setBottom(bottomBar);

        return new Scene(layout);
    }

    private Popup addEnrollmentMenu() {

        Popup addMenu = new Popup();
        addMenu.setOpacity(1f);
        addMenu.setHeight(500);
        addMenu.setWidth(500);


        VBox popupLayout = new VBox();
        popupLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popupLayout.setMinHeight(300);
        popupLayout.setMinWidth(400);


        TextField email = new TextField();
        email.setPromptText("Email");

        TextField courseName = new TextField();
        courseName.setPromptText("Course name");

        Spinner<Integer> day = new Spinner<>();
        day.setPromptText("Day");
        day.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1));

        Spinner<Integer> month = new Spinner<>();
        month.setPromptText("Month");
        month.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));

        TextField year = new TextField();
        year.setPromptText("Year");


        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addButton, cancelButton);

        popupLayout.getChildren().addAll(
                new Label("Add student"),
                email,
                courseName,
                day,
                month,
                year,
                buttons);
        popupLayout.setPadding(new Insets(10, 100, 10, 10));
        popupLayout.setSpacing(10);

        addMenu.getContent().add(popupLayout);


        addButton.setOnAction(actionEvent -> {
            enrollmentManager.newEnrollment(new Enrollment(
                            new Mail(email.getText()),
                            courseName.getText(),
                            new ValidatedDate(
                                    day.getValue(),
                                    month.getValue(),
                                    Integer.parseInt(year.getText())
                            )
                    )
            );
            table.getItems().clear();
            table.getItems().addAll(enrollmentManager.allEnrollments());
            table.refresh();
            addMenu.hide();
        });

        cancelButton.setOnAction(actionEvent -> {
            addMenu.hide();
        });

        return addMenu;
        // makes a popup with form fields to add a student to the database
    }

    private Popup editEnrollmentMenu(Enrollment currentEnrollment) {

        Popup addMenu = new Popup();
        addMenu.setOpacity(1f);
        addMenu.setHeight(500);
        addMenu.setWidth(500);


        VBox popupLayout = new VBox();
        popupLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popupLayout.setMinHeight(300);
        popupLayout.setMinWidth(400);


        TextField email = new TextField();
        email.setText(currentEnrollment.getEmailaddress().getMail());

        TextField courseName = new TextField();
        courseName.setText(currentEnrollment.getCourseName());

        Spinner<Integer> day = new Spinner<>();
        day.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, currentEnrollment.getRegisterDate().getDate().toLocalDate().getDayOfMonth()));

        Spinner<Integer> month = new Spinner<>();
        month.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, currentEnrollment.getRegisterDate().getDate().toLocalDate().getMonthValue()));

        TextField year = new TextField();
        year.setText(""+ currentEnrollment.getRegisterDate().getDate().toLocalDate().getYear());


        Button editButton = new Button("Edit");
        Button cancelButton = new Button("Cancel");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(editButton, cancelButton);

        popupLayout.getChildren().addAll(
                new Label("Add student"),
                email,
                courseName,
                new HBox(day, new Text("Day")),
                new HBox(month, new Text("Month")),
                year,
                buttons);
        popupLayout.setPadding(new Insets(10, 100, 10, 10));
        popupLayout.setSpacing(10);

        addMenu.getContent().add(popupLayout);


        editButton.setOnAction(actionEvent -> {
            enrollmentManager.editEnrollment(new Enrollment(
                            new Mail(email.getText()),
                            courseName.getText(),
                            new ValidatedDate(
                                    day.getValue(),
                                    month.getValue(),
                                    Integer.parseInt(year.getText())
                            )
                    ),currentEnrollment
            );
            table.getItems().clear();
            table.getItems().addAll(enrollmentManager.allEnrollments());
            table.refresh();
            addMenu.hide();
        });

        cancelButton.setOnAction(actionEvent -> {
            addMenu.hide();
        });

        return addMenu;
        // makes a popup with form fields to add a student to the database
    }
}
