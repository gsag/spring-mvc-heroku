package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String novo() {
        logger.info("Acessou url /welcome");
		return "hello";
	}
}
