package yasmina.mns_dfsg2.tp_java.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import yasmina.mns_dfsg2.tp_java.views.OneUserView;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView(OneUserView.class)
    protected String nom;
    protected Date temps;

    @ManyToOne(optional = false)
    protected Chantier chantier;

    @ManyToOne(optional = false)
    protected User user;

    @ManyToOne(optional = false)
    protected Tache tache;
}
