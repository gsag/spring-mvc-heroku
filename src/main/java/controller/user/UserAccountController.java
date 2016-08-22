package controller.user;

import controller.service.ControllerHelper;
import controller.service.ProfilePathService;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * Created by guilherme on 16/02/16.
 * User Account Controller for manipulating user account operations
 */

@Controller
@RequestMapping("/account")
public class UserAccountController {

    private static final Logger logger = Logger.getLogger(UserAccountController.class);

    @Autowired
    ControllerHelper helper;

    @Autowired
    ProfilePathService profilePathService;

    @RequestMapping(value = {"profile", "profile/{path}"}, method = RequestMethod.GET)
    public String getProfilePage(@PathVariable Optional<String> path, Model model, @AuthenticationPrincipal User user) {
        helper.addUserAttributesToModel(model, user);
        return profilePathService.getViewByPath(path);
    }
}