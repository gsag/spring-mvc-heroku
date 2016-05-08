package util;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by guilherme on 10/02/16.
 * Utils class for util methods
 */
@Service
public class Utils {

    public static String getGravatarUrl(String email){
        Gravatar gravatar = new Gravatar(34, GravatarRating.GENERAL_AUDIENCES, GravatarDefaultImage.GRAVATAR_ICON);
        return gravatar.getUrl(email);
    }

    public static Locale[] getEnabledLocales(){
        return new Locale[]{LocaleConstants.ENGLISH, LocaleConstants.PORTUGUESE_BR};
    }

    public static Locale localeParser(String locale){
        String[] lang_country = locale.split("_");
        return (lang_country.length == 1) ? new Locale(lang_country[0]) : new Locale(lang_country[0], lang_country[1]);
    }
}