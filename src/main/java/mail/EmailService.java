package mail;

import config.WebMvcConfiguration;
import entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guilherme on 09/03/16.
 * Email Service
 */
@Service
public class EmailService{

    private static final Logger logger = Logger.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(final String toAddress, final String subject, final String msgBody) {
        try{
            // Prepare message using a Spring helper
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, WebMvcConfiguration.DEFAULT_CHAR_ENCODING);

            // Prepare the evaluation context
            final Context context = new Context(LocaleContextHolder.getLocale());
            context.setVariable("message",msgBody);

            message.setTo(toAddress);
            message.setSubject(subject);

            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("simple-email.html", context);
            message.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch(MailException | MessagingException me){
            logger.error(me);
        }
    }

    public void sendEmail(User user, Map<String,String> attributes){
        try{
            // Prepare message using a Spring helper
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, WebMvcConfiguration.DEFAULT_CHAR_ENCODING);

            // Prepare the evaluation context
            final Context context = new Context(LocaleContextHolder.getLocale());
            attributes.forEach(context::setVariable);

            message.setTo(user.getUsername());
            message.setSubject("");

            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("simple-email.html", context);
            message.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch(MailException | MessagingException me){
            logger.error(me);
        }
    }

    public Map<String,String> getEmailAttributesMap(){
        return new HashMap<>();
    }
}