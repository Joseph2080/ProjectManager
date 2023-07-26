package org.chitsa.projectmanagement.Model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "uid")
public class Employee extends User {
    @Column(name = "employee_role")
    private String employee_role;
    @Column(name = "fee")
    private double fee;
    public Employee(Long uid, String firstanme, String lastname, String phoneNumber, String description, String email, String password, Role role, String employee_role, double fee) {
        super(uid, firstanme, lastname, phoneNumber, description, email, password, role);
        this.employee_role = employee_role;
        this.fee = fee;
    }
}