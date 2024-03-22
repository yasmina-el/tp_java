package yasmina.mns_dfsg2.tp_java.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import yasmina.mns_dfsg2.tp_java.dao.ChantierDao;
import yasmina.mns_dfsg2.tp_java.dao.OperationDao;
import yasmina.mns_dfsg2.tp_java.dao.TacheDao;
import yasmina.mns_dfsg2.tp_java.models.Chantier;
import yasmina.mns_dfsg2.tp_java.models.Operation;
import yasmina.mns_dfsg2.tp_java.models.Tache;
import yasmina.mns_dfsg2.tp_java.views.ChantierView;
import yasmina.mns_dfsg2.tp_java.views.UnChantierView;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {

    @Autowired
    ChantierDao chantierDao;

    @Autowired
    TacheDao tacheDao;

    @Autowired
    OperationDao operationDao;

    @GetMapping("/chantier/list")
    @Secured("ROLE_ADMIN")
    @JsonView(ChantierView.class)
    public List<Chantier> Liste(){
        List<Chantier> chantierList = chantierDao.findAll();

        return chantierList;
    }


    @GetMapping("/chantier/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(UnChantierView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);
        List<Tache> tacheList = tacheDao.findAll();
        int total=0;

        for (Operation operation : optionalChantier.get().getOperationList()) {
            for (Tache tache : tacheList) {
                if (operation.getTache().getId() == tache.getId()) {
                    total += tache.getTemps();
                }
            }
            optionalChantier.get().setTemps_total(total);
        }

        if (optionalChantier.isPresent()){
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/chantier/{id}")
    @Secured("ROLE_ADMIN")
    @JsonView(UnChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("/chantier")
    @Secured("ROLE_ADMIN")
    public Chantier create(@RequestBody @Valid Chantier chantier){
        chantier.setId(null);
        chantierDao.save(chantier);
        return chantier;
    }

    @PutMapping("/chantier/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id){

        chantier.setId(id);
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.save(chantier);
            return new ResponseEntity<>(chantier, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}
