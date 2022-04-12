package UI;

import Domain.*;
import Logic.CourseManager;
import Logic.StudentManager;
import javafx.collections.FXCollections;
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
import java.util.HashMap;
import java.util.Map;

public class StudentOverview extends OverviewElements {

    private final StudentManager studentManager;
    private TableView<Student> table;

    public StudentOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
        this.studentManager = new StudentManager();
    }

    public Scene getStudentOverview() {
        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Students");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();
        ArrayList<Student> students = studentManager.allStudents();

        table = new TableView<>();
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


        Button modulePercentage = new Button("View students module percentage");
        modulePercentage.setOnAction(actionEvent -> {
            Popup popup = ModuleCompletionPerCourse();
            popup.show(stage);
        });

        layout.setCenter(new VBox(table, modulePercentage));
        BorderPane.setMargin(table, getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, getSidebarInsets());

        javafx.scene.control.Button deleteStudent = new javafx.scene.control.Button("Delete student");

        deleteStudent.setOnAction(actionEvent -> {
            Student student = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(student);
            studentManager.deleteStudent(student);
            table.refresh();
        });

        javafx.scene.control.Button editStudent = new javafx.scene.control.Button("Edit student");
        javafx.scene.control.Button addStudent = new Button("Add student");

        addStudent.setOnAction(actionEvent -> {
            Popup popup = addStudentMenu();
            popup.show(stage);

        });

        editStudent.setOnAction(actionEvent -> {
            Popup popup = editStudentMenu(table.getSelectionModel().getSelectedItem());
            popup.show(stage);

        });


        Node bottomBar = getCRUDButtons(deleteStudent, editStudent, addStudent);
        layout.setBottom(bottomBar);


        return new Scene(layout);
    }

    private Popup addStudentMenu() {
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
        TextField firstname = new TextField();
        firstname.setPromptText("First name");
        TextField lastname = new TextField();
        lastname.setPromptText("Last name");
        Spinner<Integer> day = new Spinner<>();
        day.setPromptText("Day");
        day.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1));
        Spinner<Integer> month = new Spinner<>();
        month.setPromptText("Month");
        month.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        TextField year = new TextField();
        year.setPromptText("Year");
        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("M", "F", "NB");
        TextField address = new TextField();
        address.setPromptText("Address");
        TextField zipcode = new TextField();
        zipcode.setPromptText("Zipcode");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField country = new TextField();
        country.setPromptText("Country");

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addButton, cancelButton);

        popupLayout.getChildren().addAll(
                new Label("Add student"),
                email,
                firstname,
                lastname,
                new HBox(day, new Label("Day")),
                new HBox(month, new Label("Month")),
                year,
                gender,
                address,
                zipcode,
                city,
                country,
                buttons);
        popupLayout.setPadding(new Insets(10, 100, 10, 10));
        popupLayout.setSpacing(10);

        addMenu.getContent().add(popupLayout);


        addButton.setOnAction(actionEvent -> {
            studentManager.newStudent(new Student(
                    new Mail(email.getText()),
                    firstname.getText(),
                    lastname.getText(),
                    new ValidatedDate(
                            day.getValue(),
                            month.getValue(),
                            Integer.parseInt(year.getText())),
                    Gender.valueOf(gender.getValue().toString()),
                    address.getText(),
                    new Zipcode(zipcode.getText()),
                    city.getText(),
                    country.getText()));
            table.getItems().clear();
            table.getItems().addAll(studentManager.allStudents());
            table.refresh();
            addMenu.hide();
        });

        cancelButton.setOnAction(actionEvent -> {
            addMenu.hide();
        });

        return addMenu;
        // makes a popup with form fields to add a student to the database
    }


    private Popup editStudentMenu(Student currentStudent) {
        Popup editMenu = new Popup();
        editMenu.setOpacity(1f);
        editMenu.setHeight(500);
        editMenu.setWidth(500);


        VBox popupLayout = new VBox();
        popupLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popupLayout.setMinHeight(300);
        popupLayout.setMinWidth(400);


        TextField email = new TextField(currentStudent.getEmailaddress().getMail());

        TextField firstname = new TextField(currentStudent.getFirstname());

        TextField lastname = new TextField(currentStudent.getLastname());

        Spinner<Integer> day = new Spinner<>();

        day.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, currentStudent.getDateOfBirth().getDate().toLocalDate().getDayOfMonth()));
        Spinner<Integer> month = new Spinner<>();

        month.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, currentStudent.getDateOfBirth().getDate().toLocalDate().getMonthValue()));
        TextField year = new TextField(currentStudent.getDateOfBirth().getDate().toLocalDate().getYear() + "");

        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("M", "F", "NB");
        gender.getSelectionModel().select(currentStudent.getGender().toString());

        TextField address = new TextField(currentStudent.getAddress());

        TextField zipcode = new TextField(currentStudent.getZipcode().getZipcode());

        TextField city = new TextField(currentStudent.getCity());

        TextField country = new TextField(currentStudent.getCountry());


        Button addButton = new Button("Edit");
        Button cancelButton = new Button("Cancel");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addButton, cancelButton);

        popupLayout.getChildren().addAll(
                new Label("Add student"),
                email,
                firstname,
                lastname,
                new HBox(day, new Label("Day")),
                new HBox(month, new Label("Month")),
                year,
                gender,
                address,
                zipcode,
                city,
                country,
                buttons);
        popupLayout.setPadding(new Insets(10, 100, 10, 10));
        popupLayout.setSpacing(10);

        editMenu.getContent().add(popupLayout);


        addButton.setOnAction(actionEvent -> {
            studentManager.editStudent(new Student(
                            new Mail(email.getText()),
                            firstname.getText(),
                            lastname.getText(),
                            new ValidatedDate(
                                    day.getValue(),
                                    month.getValue(),
                                    Integer.parseInt(year.getText())),
                            Gender.valueOf(gender.getValue().toString()),
                            address.getText(),
                            new Zipcode(zipcode.getText()),
                            city.getText(),
                            country.getText()),
                    currentStudent);
            table.getItems().clear();
            table.getItems().addAll(studentManager.allStudents());
            table.refresh();
            editMenu.hide();
        });

        cancelButton.setOnAction(actionEvent -> {
            editMenu.hide();
        });

        return editMenu;
        // makes a popup with form fields to add a student to the database
    }

    private Popup ModuleCompletionPerCourse() {

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

        ListView<Course> courselist = new ListView<Course>(FXCollections.observableArrayList(new CourseManager().allCourses()));
        courselist.setMaxHeight(100);
        courselist.maxHeight(200);
        VBox modulePercentages = new VBox();

        courselist.setOnMouseClicked(e -> {
            modulePercentages.getChildren().clear();
            final HashMap<String, ContentItemProgress>[] hashMap = new HashMap[]{new HashMap<>()};
            String courseName = courselist.getSelectionModel().getSelectedItem().getCourseName();
            String mail = table.getSelectionModel().getSelectedItem().getEmailaddress().getMail();
            hashMap[0] = studentManager.studentModulePercentage(courseName, mail);
            if (!hashMap[0].isEmpty()) {
                int i = 1;
                for (Map.Entry<String, ContentItemProgress> entry : hashMap[0].entrySet()) {
                    modulePercentages.getChildren().add(new Text(i + ": Module: " +
                            entry.getKey() +
                            " avg percentage " +
                            entry.getValue().getPercentage()+ "%"));
                    i++;
                }
            }else {
                modulePercentages.getChildren().add(new Text("Student has not yet started any modules for this course"));
            }
        });


        popupLayout.getChildren().addAll(courselist,new Text("select a course to view percentages"), modulePercentages, backButton);
        menu.getContent().add(popupLayout);
        return menu;
    }

}
