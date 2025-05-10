package ukim.finki.mk.lab1.dto;


import ukim.finki.mk.lab1.model.domain.User;
import ukim.finki.mk.lab1.model.enums.Role;

public record UpdateUserDto(String username, String name, String surname, Role role) {

    public static UpdateUserDto from(User user) {
        return new UpdateUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}
