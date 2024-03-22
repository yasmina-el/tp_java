package yasmina.mns_dfsg2.tp_java.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import yasmina.mns_dfsg2.tp_java.views.OneUserView;
import yasmina.mns_dfsg2.tp_java.views.UserView;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @JsonView({OneUserView.class, UserView.class})
    protected String designation;
}
