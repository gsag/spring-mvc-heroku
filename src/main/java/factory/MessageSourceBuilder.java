package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import util.Utils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by guilherme on 24/08/16.
 * A builder factory to message sources
 */
@Component
public class MessageSourceBuilder {

    @Autowired
    private MessageSource messageSource;

    private String messageCode;

    private Object[] args;

    private Locale locale;

    public MessageSourceBuilder() {
        this.messageCode = "";
        this.args = null;
        this.locale = Utils.getEnabledLocales()[0];
    }

    public MessageSourceBuilder(String code) {
        this();
        this.messageCode = code;
    }

    public MessageSourceBuilder addMessageCode(String code) {
        this.messageCode = code;
        return this;
    }

    public MessageSourceBuilder addMessageArguments(Object... args) {
        if(args != null) {
            this.args = new Object[args.length];
            IntStream.range(0, args.length).forEachOrdered(index -> this.args[index] = args[index]);
        }
        return this;
    }

    public MessageSourceBuilder addMessageLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public String build() {
        return this.messageSource.getMessage(this.messageCode, this.args, this.locale);
    }
}
