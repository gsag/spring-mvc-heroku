package controller;

import controller.service.ControllerHelper;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeController{

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
	ControllerHelper helper;

	@RequestMapping(method = RequestMethod.GET)
	public String getWelcomePage(Model model, @AuthenticationPrincipal User user) {
		helper.addUserAttributesToModel(model, user);
	    return (user != null) ? "user/dashboard" : "welcome";
	}
}