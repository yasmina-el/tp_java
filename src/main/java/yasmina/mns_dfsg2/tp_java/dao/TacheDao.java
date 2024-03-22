package yasmina.mns_dfsg2.tp_java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yasmina.mns_dfsg2.tp_java.models.Tache;

import java.util.Optional;

@Repository
public interface TacheDao extends JpaRepository<Tache, Integer> {
    Optional<Tache> findByNom(String nomRechercher);
}
