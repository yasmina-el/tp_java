package yasmina.mns_dfsg2.tp_java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yasmina.mns_dfsg2.tp_java.dao.UserDao;
import yasmina.mns_dfsg2.tp_java.models.User;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
//        pour récupéré un utilisateur il nous faut un class User dans model

        Optional<User> optionalUser = userDao.findByPseudo(pseudo);

        if (optionalUser.isEmpty()) {
            throw  new UsernameNotFoundException("introuvable");

        }
        return new AppUserDetails(optionalUser.get());
    }
}
