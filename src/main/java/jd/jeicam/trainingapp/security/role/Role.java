package jd.jeicam.trainingapp.security.role;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "TRAININGAPP_ROLE")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "NAME")
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}
