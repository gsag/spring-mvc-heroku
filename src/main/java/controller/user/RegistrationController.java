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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repository.service.RegistrationService;
import security.AuthorityType;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;
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

    @ModelAttribute("languages")
    public Locale[] getEnabledLanguages(){
        return Utils.getEnabledLocales();
    }

    @ModelAttribute("genders")
    public Gender[] getGenders(){
        return Gender.values();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "user/registration/register_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, @Valid @ModelAttribute User user, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return "user/registration/register_form";
        }else{
            if(registrationService.isUserAlreadyRegistered(user)) {
                result.rejectValue("username", "register.form.field.username.error");
                return "user/registration/register_form";
            }

            if(!registrationService.isUserPasswordEqualToConfirmedPassword(user)) {
                result.rejectValue("confirmPassword", "register.form.field.confirmpassword.error");
                return "user/registration/register_form";
            }

            user.setActivateKey(registrationService.generateActivationKey());
            user.setRegisterDate(LocalDate.now());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setNewCredentialInAuthorities(AuthorityType.ROLE_USER);
            registrationService.saveEntity(user);
            registrationService.sendConfirmationEmailToUser(request, user);

            attributes.addFlashAttribute("mensagem",
                    registrationService.getMessageFromMessageSource("register.email.send.success", user));

            return "redirect:login";
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam(value = "k") String key, RedirectAttributes attributes){
        Optional<User> user = registrationService.findUserByUUID(key);
        if(user.isPresent()){
            logger.info("Usuário encontrado e será ativado - " + user.get().getActivateKey());
            User registered = user.get();
            registered.setActivated(Boolean.TRUE);
            registrationService.updateEntity(registered);
            attributes.addFlashAttribute("mensagem",
                    registrationService.getMessageFromMessageSource("register.email.confirmed.success", registered));
        }else{
            logger.warn("Usuário não encontrado, deverá ser tratado.");
        }
        return "redirect:/login";
    }
}