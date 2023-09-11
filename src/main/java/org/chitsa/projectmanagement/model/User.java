package org.chitsa.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    protected Long userId;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(name = "login")
    protected String login;
    @Column(name = "email")
    protected String email;
    @Column(name = "date_registered")
    protected LocalDate dateRegistered;
    @Column(name = "password")
    private String password;
        @ManyToOne
        @JoinColumn(name = "role_id")
    private Role role;

    public User(String firstName, String lastName, String login, String email, String password, LocalDate dateRegistered, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.dateRegistered = dateRegistered;
        this.role = role;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static LocalDate convertStringToDate(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(birthday, formatter);
    }

    public String periodUserActive() {
        if (dateRegistered != null) {
            LocalDate currentDate = LocalDate.now();
            Period days = Period.between(dateRegistered, currentDate);
            int numDays = days.getDays();
            String periodInMonths = numDays + " day";
            if (numDays > 1) {
                periodInMonths = numDays + " days";
            }
            return periodInMonths;
        }
        return null;
    }
}