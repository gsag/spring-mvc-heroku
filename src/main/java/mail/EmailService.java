package mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by guilherme on 09/03/16.
 * Email Service
 */
@Service
public class EmailService{

    @Autowired
    private MailSender mailSender;

    public void sendEmail(String toAddress, String subject, String msgBody) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(msgBody);
        mailSender.send(mailMessage);
    }
}