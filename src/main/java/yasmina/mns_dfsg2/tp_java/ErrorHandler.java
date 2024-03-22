package yasmina.mns_dfsg2.tp_java;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

//pour attraper les erreurs
@ControllerAdvice
public class ErrorHandler {
//    On attrape spécifiquement l'exception nommé MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
//    On précise la réponse http ici une mauvaise requète
    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    On précise que l'on va ducoup retourner une réponse JSON
    @ResponseBody
    public Map<String, Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, Object> listeErreur = new HashMap<>();

        for(ObjectError erreur : ex.getBindingResult().getAllErrors()){
            String nomChamps = ((FieldError) erreur).getField();
            String messageErreur = erreur.getDefaultMessage();
            listeErreur.put(nomChamps, messageErreur);
        }
        return listeErreur;
    }

//    Pour afficher un message lorsque l'on ajoute un nom déjà existant dans la bdd
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){

        return Map.of("erreur","une contrainte d'intégrité n'a pas été respectée");
    }

}
