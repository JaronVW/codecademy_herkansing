package UI;

import Domain.Webcast;
import Logic.WebcastManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WebcastOverview extends OverviewElements {

    public WebcastOverview(Scene homeScene, Stage stage) {
        super(homeScene, stage);
    }

    public Scene getWebcastOverview() {

        final String stageTitle = "Codecademy: Jaron van well, 2184746: ";
        Stage stage = getStage();
        stage.setTitle(stageTitle + "Students");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane layout = new BorderPane();
        WebcastManager webcastManager = new WebcastManager();
        ArrayList<Webcast> webcasts = webcastManager.topThreeWebcasts();

        TableView<Webcast> table = new TableView<>();
        table.getItems().addAll(webcasts);

        TableColumn<Webcast, String> courseNameCol = new TableColumn<>("Course name");
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Webcast, String> contentItemTitleCol = new TableColumn<>("Title");
        contentItemTitleCol.setCellValueFactory(new PropertyValueFactory<>("contentItemTitle"));

        TableColumn<Webcast, String> publicationDateCol = new TableColumn<>("Publication date");
        publicationDateCol.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

        TableColumn<Webcast, String> urlCol = new TableColumn<>("Url");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("webcastURL"));

        TableColumn<Webcast, Integer> durationCol = new TableColumn<>("duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Webcast, String> SpeakerMailCol = new TableColumn<>("mail speaker");
        SpeakerMailCol.setCellValueFactory(new PropertyValueFactory<>("speakerEmail"));

        TableColumn<Webcast, String> webcastDescriptionCol = new TableColumn<>("Description");
        webcastDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("webcastDescription"));


        table.getColumns().setAll(courseNameCol, contentItemTitleCol, publicationDateCol, urlCol,durationCol,SpeakerMailCol,webcastDescriptionCol);


        layout.setCenter(new VBox(table));
        BorderPane.setMargin(table, getTableInsets());

        Node sidebar = getNavigationSidebar();
        layout.setLeft(sidebar);
        BorderPane.setMargin(sidebar, getSidebarInsets());


        return new Scene(layout);
    }
}
