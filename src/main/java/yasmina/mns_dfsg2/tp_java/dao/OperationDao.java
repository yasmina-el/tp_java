package yasmina.mns_dfsg2.tp_java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yasmina.mns_dfsg2.tp_java.models.Operation;

import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer> {
    Optional<Operation> findByNom(String nomRecherche);
}
