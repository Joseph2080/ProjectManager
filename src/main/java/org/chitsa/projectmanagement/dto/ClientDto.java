package org.chitsa.projectmanagement.dto;

import lombok.Data;
import org.chitsa.projectmanagement.model.Client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ClientDto extends UserDto {
    @NotEmpty(message = "Occupation parameter can not be empty")
    String occupation;
    public ClientDto(@NotEmpty @NotEmpty(message = "Firstname name parameter can not be empty") String firstName
            , @NotEmpty(message = "Last name parameter can not be empty") String lastName
            , @NotEmpty(message = "Login parameter can not be empty") String login
            , @Email(message = "Email parameter provided is not valid") String email
            , @NotEmpty String dateRegistered, @NotEmpty(message = "Role parameter can not be empty") String role
            , @NotEmpty(message = "Password parameter can not be empty") String password
            , @NotEmpty(message = "Verify password parameter can not be empty") String verifyPassword
            , String occupation, boolean editable) {
        super(firstName, lastName, login, email, dateRegistered, role, password, verifyPassword, editable);
        this.occupation = occupation;
    }

    public static Client convertToClient(ClientDto clientDto){
        Client client  = (Client) convertToUser(clientDto);
        client.setOccupation(clientDto.getOccupation());
        return client;
    }

    public static ClientDto convertClientToDto(Client client){
        ClientDto clientDto = (ClientDto) convertUserToDto(client);
        clientDto.setOccupation(client.getOccupation());
        return clientDto;
    }
}
