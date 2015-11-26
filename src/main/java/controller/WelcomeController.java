package controller;

import entity.LogAccess;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repository.LogAccessRepository;

import java.time.LocalDate;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
    LogAccessRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String novo() {
        logger.info("Acessou url /welcome");

        LogAccess access = new LogAccess(LocalDate.now());
        try {
            repository.save(access);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return "hello";
	}
}
