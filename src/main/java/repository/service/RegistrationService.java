package repository.service;

import entity.user.User;
import mail.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by guilherme on 11/03/16.
 * Service for Registering logic
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class RegistrationService extends AbstractRepositoryService<UserRepository> {

    private static final Logger logger = Logger.getLogger(RegistrationService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public String generateActivationKey(){
        return UUID.randomUUID().toString();
    }

    public void sendConfirmationEmailToUser(HttpServletRequest request, User user){
        emailService.sendConfirmationEmail(user,getfilledEmailAttributesMap(request,user));
    }

    private Map<String,String> getfilledEmailAttributesMap(HttpServletRequest request, User user){
        Map<String,String> attributes = emailService.getEmailAttributesMap();
        final String key = (user.getActivateKey().split("-"))[4];
        attributes.put("name",user.getFirstName());
        attributes.put("confirmURL","/confirm?k="+key);
        attributes.put("requestURL",request.getRequestURL().toString());
        return attributes;
    }

    @Transactional
    public Optional<User> findUserByUUID(String uuid){
        Optional<User> userFound = userRepository.findUserByUUID(uuid);
        return userFound;
    }
}