package yasmina.mns_dfsg2.tp_java.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yasmina.mns_dfsg2.tp_java.views.ChantierView;
import yasmina.mns_dfsg2.tp_java.views.UnChantierView;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChantierView.class,UnChantierView.class})
    protected Integer id;
    @JsonView({ChantierView.class,UnChantierView.class})
    protected String nom;
    @JsonView(UnChantierView.class)
    protected String adresse;

    @OneToMany(mappedBy = "chantier")
    protected List<Operation> operationList = new ArrayList<>();

    @Transient
    @JsonView(UnChantierView.class)
    protected Integer temps_total;



}
