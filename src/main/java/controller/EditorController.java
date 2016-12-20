package controller;

import mail.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;

/**
 * Created by guilherme on 16/12/16.
 */
@Controller
@RequestMapping("/editores")
public class EditorController {
    private static final Logger logger = Logger.getLogger(EditorController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPaginaPrincipal(){
        return "editores";
    }

    @RequestMapping("/ckeditor")
    public String getPaginaCKEditor(){
        return "editor_ckeditor";
    }

    @RequestMapping("/tinymce")
    public String getPaginaTinyMCE(){
        return "editor_tinymce";
    }

    @RequestMapping("textboxio")
    public String getPaginaTextBoxIO(){
        return "editor_textboxio";
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String jsonString, HttpServletRequest request) {
        ResponseEntity<String> response = null;
        try {
            jsonString = jsonString.replace("\\n", "");
            String[] array = jsonString.split("\"_");
            System.out.println(jsonString);
            Map<String, String> atributos = emailService.getEmailAttributesMap();
            atributos.put("id", array[2]);
            atributos.put("content", array[0]);
            atributos.put("editor", array[1]);
            emailService.sendEmail(request, atributos);
            response = new ResponseEntity<String>("Os resultados foram salvos com sucesso!", HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<String>("Houve um problema ao processar a requisição...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
