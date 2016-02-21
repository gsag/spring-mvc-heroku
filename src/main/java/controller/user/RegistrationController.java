package controller.user;

import entity.user.Gender;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.Utils;

import javax.validation.Valid;

/**
 * Created by guilherme on 10/02/16.
 * Registration Controller for manipulating user operations related with sign up.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("genders",Gender.values());
        model.addAttribute("languages", Utils.getEnabledLocales());
        return "user/registration/register_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute User user, BindingResult result){
        logger.info(user.getGender());
        logger.info(user.getLastName());
        if (result.hasErrors()) {
            return getRegisterForm(model,user);
        }else{
            return "redirect:login";
        }
    }
}
