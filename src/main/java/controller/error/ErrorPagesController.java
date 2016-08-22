package controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by guilherme on 05/08/16.
 */
@Controller
public class ErrorPagesController {

    @RequestMapping("/404")
    public String notFound() {
        return "/error/404";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "/error/403";
    }
}
