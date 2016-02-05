package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repository.service.ExemploService;
import repository.service.UserService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
    ExemploService exemploService;

	@RequestMapping(method = RequestMethod.GET)
	public String novo() {
        logger.info("Acessou url /welcome");

//        Exemplo exemplo = new Exemplo();
//        exemplo.setAccessDate(LocalDate.now());
//        exemploService.saveEntity(exemplo);
//
//        logger.info(exemploService.countEntities());
//        logger.info(exemploService.findEntityById(1L));
//        logger.info(exemploService.hasEntity(1L));
//        Optional<Exemplo> recuperado = exemploService.findEntityById(1L);
//        Exemplo e = recuperado.get();
//        e.setAccessDate(LocalDate.parse("2015-12-02"));
//        exemploService.updateEntity(e);
//        exemploService.deleteEntity(e);
//
//        logger.info(exemploService.findAllEntities());

        return "hello";
	}
}
