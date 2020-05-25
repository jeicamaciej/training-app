package jd.jeicam.trainingapp.security.role;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "TRAININGAPP_ROLE")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Role {

    public interface JsonViews{
        interface Get{}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "NAME")
    @JsonView(JsonViews.Get.class)
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}
