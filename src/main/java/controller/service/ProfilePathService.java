package controller.service;

import org.springframework.stereotype.Service;

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
    public final String DEFAULT_PROFILE_PREFIX = "profile_";
    public final String DEFAULT_PROFILE_SUFFIX = ":: profile-content";
    public final String USER_ACCOUNT_VIEW = "user/account/" + DEFAULT_PROFILE_PREFIX;

    private final Set<String> availablePaths;

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

    public String getViewByPath(Optional<String> path){
        return (path.isPresent() && isPathValid(path))
                ? USER_ACCOUNT_VIEW + path.get() + DEFAULT_PROFILE_SUFFIX
                : USER_ACCOUNT_VIEW + "home";
    }
}