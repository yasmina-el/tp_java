package yasmina.mns_dfsg2.tp_java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yasmina.mns_dfsg2.tp_java.models.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByPseudo(String pseudo);
}
