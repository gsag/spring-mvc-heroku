package util;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by guilherme on 12/02/16.
 * Constants for useful use on language settings
 */
public final class LocaleConstants implements Cloneable, Serializable {
    private static final long serialVersionUID = -3417795369060512901L;

    /** Useful constant for language.
     *  International English
     */
    static public final Locale ENGLISH = new Locale("en");

    /** Useful constant for language.
     *  Brazilian Portuguese
     */
    static public final Locale PORTUGUESE_BR = new Locale("pt","BR");
}