package repository.service;

import entity.user.User;
import factory.MessageSourceBuilder;
import mail.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import util.Utils;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    MessageSourceBuilder messageBuilder;

    public String generateActivationKey(){
        return UUID.randomUUID().toString();
    }

    public void sendConfirmationEmailToUser(HttpServletRequest request, User user){
        emailService.sendConfirmationEmail(request, user, getEmailAttributesMap(request,user));
    }

    private Map<String,String> getEmailAttributesMap(HttpServletRequest request, User user){
        Map<String,String> attributes = emailService.getEmailAttributesMap();
        attributes.put("name",user.getFirstName());
        attributes.put("confirmURL","/confirm?k=" + (user.getActivateKey().split("-"))[4]);
        attributes.put("requestURL",request.getRequestURL().toString());
        return attributes;
    }

    @Transactional
    public Optional<User> findUserByUUID(String uuid){
        return userRepository.findUserByUUID(uuid);
    }

    @Transactional
    public UserDetails findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public Boolean isUserAlreadyRegistered(User user){
        Boolean anwser = Boolean.TRUE;
        try{
            findUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e){
            anwser = Boolean.FALSE;
        }
        return anwser;
    }

    public Boolean isUserPasswordEqualToConfirmedPassword(User user){
        return user.getConfirmPassword().equals(user.getPassword());
    }

    public String getMessageFromMessageSource(String code, User user){
        return messageBuilder
                .addMessageCode(code)
                .addMessageArguments(user.getFirstName())
                .addMessageLocale(Utils.localeParser(user.getLangKey()))
                .build();
    }
}