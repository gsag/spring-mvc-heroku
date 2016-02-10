package controller;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guilherme on 10/02/16.
 * Registration Controller for manipulate user operations related with sign up.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(Model model, User user){
        model.addAttribute("user",user);
        return "user/register_form";
    }
}
