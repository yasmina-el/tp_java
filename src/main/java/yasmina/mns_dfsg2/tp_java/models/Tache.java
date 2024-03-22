package yasmina.mns_dfsg2.tp_java.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tache {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected Integer id;
        protected String nom;
        protected Integer temps;

        @ManyToMany
        @JoinTable(name = "tache_consommable",
        joinColumns = @JoinColumn(name = "tache_id"),
                inverseJoinColumns = @JoinColumn(name = "consommable_id"))
        protected List<Consommable> consommableList = new ArrayList<>();



}
