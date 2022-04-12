package Logic;

import Database.ModuleDAO;
import Domain.Module;

import java.util.ArrayList;
import java.util.HashMap;

//manager classes are used to prevent code duplication and make code migration easier. they can also easily implement needed logic
public class ModuleManager {

    private final ModuleDAO moduleDAO;

    public ModuleManager() {
        this.moduleDAO = new ModuleDAO();
    }

    public ArrayList<Module> allModules(){
        return moduleDAO.selectAllModules();
    }

    public HashMap<String, Integer> ModulePercentage(String courseName) {
        return moduleDAO.selectModulePercentage(courseName);
    }

}

