package util;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.springframework.stereotype.Service;

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
}