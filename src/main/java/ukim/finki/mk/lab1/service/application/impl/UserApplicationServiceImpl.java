package ukim.finki.mk.lab1.service.application.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.dto.CreateUserDto;
import ukim.finki.mk.lab1.dto.LoginResponseDto;
import ukim.finki.mk.lab1.dto.LoginUserDto;
import ukim.finki.mk.lab1.dto.UpdateUserDto;
import ukim.finki.mk.lab1.model.domain.User;
import ukim.finki.mk.lab1.security.JWTHelper;
import ukim.finki.mk.lab1.service.application.UserApplicationService;
import ukim.finki.mk.lab1.service.domain.UserService;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JWTHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JWTHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<UpdateUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(UpdateUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<UpdateUserDto> findByUsername(String username) {
        return Optional.of(UpdateUserDto.from(userService.findByUsername(username)));
    }

}
