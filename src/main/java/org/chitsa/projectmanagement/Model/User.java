package org.chitsa.projectmanagement.Model;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "uid ",nullable = false)
    private Long uid;
    @Column(name = "firstname", nullable = false)
    private String firstanme;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "email", nullable = false)
    private String email;
    @Getter(AccessLevel.NONE)
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User(Long uid, String firstanme, String lastname, String phoneNumber, String description, String email, String password, Role role) {
        this.uid = uid;
        this.firstanme = firstanme;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
        this.password = password;
        this.role = role;
    }
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
