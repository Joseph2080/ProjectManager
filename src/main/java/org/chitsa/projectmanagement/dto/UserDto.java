package org.chitsa.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.chitsa.projectmanagement.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    @NotEmpty(message = "First name name parameter can not be empty")
    String firstName;
    @NotEmpty(message = "Last name parameter can not be empty")
    String lastName;
    @NotEmpty(message = "Login parameter can not be empty")
    String login;
    @Email(message = "Email parameter provided is not valid")
    String email;
    @NotEmpty
    String dateRegistered;
    @NotEmpty(message = "Select a valid Role")
    protected String role;
    @NotEmpty(message = "Password parameter can not be empty")
    @ToString.Exclude
    private String password;
    @NotEmpty(message = "Verify password parameter can not be empty")
    @ToString.Exclude
    private String verifyPassword;

    boolean editable;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public boolean verifyPassword() {
        if (Optional.ofNullable(password).isEmpty() || Optional.ofNullable(verifyPassword).isEmpty()) {
            return false;
        }
        return password.equals(verifyPassword);
    }


    public static User convertToUser(UserDto userDto) {
        User user = new User();
        user.setDateRegistered(User.convertStringToDate(userDto.getDateRegistered()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.email);
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setDateRegistered(user.getDateRegistered().toString());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }

}
