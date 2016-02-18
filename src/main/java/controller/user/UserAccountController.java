package controller.user;

import controller.service.ViewModelService;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guilherme on 16/02/16.
 * User Account Controller for manipulating user account operations
 */

@Controller
@RequestMapping("/account")
public class UserAccountController {

    private static final Logger logger = Logger.getLogger(UserAccountController.class);

    @Autowired
    ViewModelService viewModelService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String getProfilePage(Model model, @AuthenticationPrincipal User user) {
        if(user != null){
            viewModelService.getModelWithUserAttributes(model,user);
        }
        return "user/account_profile";
    }
}