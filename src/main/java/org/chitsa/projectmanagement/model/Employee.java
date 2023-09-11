package org.chitsa.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {
    @Column(name = "qualifications")
    private String qualifications;

    public Employee(String firstName, String lastName, String login, String email, String password, LocalDate dateRegistered,
                    Role role, String qualifications) {
        super(firstName, lastName, login, email, password, dateRegistered, role);
        this.qualifications = qualifications;
    }
}