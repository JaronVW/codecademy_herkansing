package Logic;

import Database.CourseDAO;
import Database.ModuleDAO;
import Database.StudentDAO;
import Domain.ContentItemProgress;
import Domain.Course;
import Domain.Module;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {

    public HashMap<String, Integer> getModuleCompletionPercentage(Course course) {
        ModuleDAO moduleDAO = new ModuleDAO();
        ArrayList<Module> courseNameModules = new ArrayList<>();
        moduleDAO.selectAllModules().forEach(m -> {
            if (m.getCoursename().equals(course.getCourseName()))
                courseNameModules.add(m);
        });

        //TODO: finish this method

        HashMap<String, Integer> results = new HashMap<>();
        return results;
    }

}

