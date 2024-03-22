package yasmina.mns_dfsg2.tp_java.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import yasmina.mns_dfsg2.tp_java.dao.UserDao;
import yasmina.mns_dfsg2.tp_java.models.User;
import yasmina.mns_dfsg2.tp_java.security.AppUserDetailsService;
import yasmina.mns_dfsg2.tp_java.security.JwtUtils;
import yasmina.mns_dfsg2.tp_java.views.OneUserView;
import yasmina.mns_dfsg2.tp_java.views.UserView;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestBody User user){

        try {

            UserDetails userDetails= (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                            user.getPseudo(),
                            user.getPassword()))
                    .getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex){
            return null;
        }


    }

    @GetMapping("/user/list")
    @Secured("ROLE_ADMIN")
    @JsonView(UserView.class)
    public List<User> Liste(){

        List<User> userList = userDao.findAll();
        return userList;
    }


    @GetMapping("/user/{id}")
    @JsonView(OneUserView.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public ResponseEntity<User> get(@PathVariable int id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
