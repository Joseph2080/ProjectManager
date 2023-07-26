package org.chitsa.projectmanagement.Model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@PrimaryKeyJoinColumn(name = "uid")
public class Client extends User{

    @Column(name = "industry",  nullable = false)
    private String industry;
    @Column(name = "profession", nullable = false)
    private String profession;

    public Client(Long uid, String firstanme, String lastname, String phoneNumber, String description, String email, String password, Role role, String industry, String profession) {
        super(uid, firstanme, lastname, phoneNumber, description, email, password, role);
        this.industry = industry;
        this.profession = profession;
    }

}
