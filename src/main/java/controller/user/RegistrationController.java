package controller.user;

import entity.user.Gender;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repository.service.RegistrationService;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by guilherme on 10/02/16.
 * Registration Controller for manipulating user operations related with sign up.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("genders",Gender.values());
        model.addAttribute("languages", Utils.getEnabledLocales());
        return "user/registration/register_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, Model model,
                           @Valid @ModelAttribute User user, BindingResult result){
        if (result.hasErrors()) {
            return getRegisterForm(model,user);
        }else{
            try{
                user.setActivateKey(registrationService.generateActivationKey());
                user.setRegisterDate(LocalDate.now());
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                registrationService.saveEntity(user);
                registrationService.sendConfirmationEmailToUser(request,user);
            }catch (Exception e) {
                logger.error(e.getMessage());
                return getRegisterForm(model, user);
            }
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam(value = "k") String key){
        logger.info(key);
        Optional<User> user = registrationService.findUserByUUID(key);
        logger.info(user.isPresent()? user.get().getActivateKey():user);
        return "welcome";
    }
}