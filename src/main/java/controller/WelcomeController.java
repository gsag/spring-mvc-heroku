package controller;

import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.Utils;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String welcomePage(Model model, @AuthenticationPrincipal User user) {
        logger.info("Acessou url /welcome");
        if(user != null){
            model.addAttribute("activeUser", user.getFirstName() + " " + user.getLastName());
            model.addAttribute("gravatar",Utils.getGravatarUrl(user.getUsername()));
            logger.info("Usuário ativo: "+ user);
        }
        return "welcome";
	}
}