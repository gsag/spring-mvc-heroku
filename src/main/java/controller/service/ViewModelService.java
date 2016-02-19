package controller.service;

import entity.user.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import util.Utils;

/**
 * Created by guilherme on 16/02/16.
 * Page Model Service
 */

@Service
public class ViewModelService {

    public Model getModelWithUserAttributes(Model model, User user){
        if(user != null) {
            model.addAttribute("activeUser", user.getFirstName() + " " + user.getLastName());
            model.addAttribute("gravatar", Utils.getGravatarUrl(user.getUsername()));
        }
        return model;
    }
}