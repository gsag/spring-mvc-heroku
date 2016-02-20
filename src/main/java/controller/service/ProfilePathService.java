package controller.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by guilherme on 19/02/16.
 * A Service for validating profile path strings
 */

@Service
public class ProfilePathService {

    private final Set<String> availablePaths;

    public static final String DEFAULT_PROFILE_PREFIX = "profile_";

    public ProfilePathService() {
        this.availablePaths = new HashSet<>();
    }

    @PostConstruct
    private void init(){
        this.availablePaths.add("info");
        this.availablePaths.add("preferences");
        this.availablePaths.add("settings");
    }

    public Boolean isPathValid(Optional<String> path){
        return this.availablePaths.contains(path.get());
    }

    public String getViewNameByPath(Optional<String> path){
        return (path.isPresent() && isPathValid(path))
                ? DEFAULT_PROFILE_PREFIX + path.get()
                : DEFAULT_PROFILE_PREFIX + "info";
    }

    public Model getModelWithRequestURI(Model model){
        return model.addAttribute("requestURI","/account/profile");
    }
}