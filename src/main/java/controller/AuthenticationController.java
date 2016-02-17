package controller;

import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guilherme on 08/02/16.
 * Authentication Controller
 */
@Controller
public class AuthenticationController{
    private static final Logger logger = Logger.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        logger.info("Acessou url /login");
        return "login";
    }
}