package yasmina.mns_dfsg2.tp_java.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import yasmina.mns_dfsg2.tp_java.views.OneUserView;
import yasmina.mns_dfsg2.tp_java.views.UserView;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({OneUserView.class,UserView.class})
    protected Integer id;

    @Length(min = 1, max =55,message = "Le pseudo doit avoir entre 1 et 55 caractères")
    @JsonView({OneUserView.class,UserView.class})
    protected String pseudo;
    @Length(min = 4 ,message = "Le mot de passe doit avoir à minima 4 caractères")
    protected String password;

    @ManyToOne(optional = false)
    @JsonView({OneUserView.class,UserView.class})
    protected Role role;


    @OneToMany(mappedBy = "user")
    @JsonView(OneUserView.class)
    protected List<Operation> operationList = new ArrayList<>();

}
