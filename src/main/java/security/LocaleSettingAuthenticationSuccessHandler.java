package security;

import entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by guilherme on 13/02/16.
 * Handler for dynamic locale setting by user preference.
 */
@Component
public class LocaleSettingAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setLocale(authentication, request, response);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    protected void setLocale(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null){
            User user = (User) authentication.getPrincipal();
            Locale preferredLocale = localeParser(user.getLangKey());
            localeResolver.setLocale(request,response,preferredLocale);
        }
    }

    protected Locale localeParser(String locale){
        String[] lang_country = locale.split("_");
        return (lang_country.length == 1) ? new Locale(lang_country[0]) : new Locale(lang_country[0], lang_country[1]);
    }
}