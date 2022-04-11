package Logic;

import Database.WebcastDAO;
import Domain.Webcast;

import java.util.ArrayList;
import java.util.HashMap;

public class WebcastManager {
    private final WebcastDAO webcastDAO;

    public WebcastManager() {
        this.webcastDAO = new WebcastDAO();
    }


    public ArrayList<Webcast> allWebcasts() {
        return webcastDAO.selectAllWebcasts();
    }

    public ArrayList<Webcast> topFourWebcasts() {
        ArrayList<Webcast> webcastArrayList = webcastDAO.selectAllWebcasts();
        HashMap<Integer, String> hashMap = webcastDAO.selectTopFourWebcasts();

        ArrayList<Webcast> returnList = new ArrayList<>();
        webcastArrayList.forEach(webcast -> {
            for (String ct : hashMap.values()) {
                if (webcast.getContentItemTitle().equals(ct))
                    returnList.add(webcast);
            }
        });
        return returnList;
    }
}
