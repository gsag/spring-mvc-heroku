package controller.service;

import entity.user.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import util.Utils;

/**
 * Created by guilherme on 06/08/16.
 */

@Service
public class ControllerHelper {

    public ControllerHelper addUserAttributesToModel(Model model, User user){
        if(user != null) {
            model.addAttribute("activeUser", user.getFirstName() + " " + user.getLastName());
            model.addAttribute("gravatar", Utils.getGravatarUrl(user.getUsername()));
        }
        return this;
    }
}