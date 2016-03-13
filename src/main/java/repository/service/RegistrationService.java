package repository.service;

import entity.user.User;
import mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by guilherme on 11/03/16.
 * Service for Registering logic
 */
@Service
public class RegistrationService extends AbstractRepositoryService<UserRepository> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public String generateActivationKey(){
        return UUID.randomUUID().toString();
    }

    public void sendConfirmationEmailToUser(HttpServletRequest request, User user){
        emailService.sendEmail(user,getfilledEmailAttributesMap(request,user));
    }

    private Map<String,String> getfilledEmailAttributesMap(HttpServletRequest request, User user){
        Map<String,String> attributes = emailService.getEmailAttributesMap();
        final String key = (user.getActivateKey().split("-"))[4];
        attributes.put("confirmURL","/confirm?k="+key);
        attributes.put("requestURL",request.getRequestURL().toString());
        return attributes;
    }
}
