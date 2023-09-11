package org.chitsa.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {
    @Column(name = "occupation")
    private String occupation;

    public Client(String firstName, String lastName, String login, String email, String password,
                  LocalDate dateRegistered, Role role, String occupation) {
        super(firstName, lastName, login, email, password, dateRegistered, role);
        this.occupation = occupation;
    }
}
