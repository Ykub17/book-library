package mate.academy.booklibrary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.booklibrary.dto.UserLoginRequestDto;
import mate.academy.booklibrary.dto.UserLoginResponseDto;
import mate.academy.booklibrary.dto.UserRegistrationRequestDto;
import mate.academy.booklibrary.dto.UserResponseDto;
import mate.academy.booklibrary.exception.RegistrationException;
import mate.academy.booklibrary.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User authentication endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data"),
            @ApiResponse(responseCode = "409", description = "User with this email already exists")
    })
    public UserResponseDto register(
            @Valid @RequestBody UserRegistrationRequestDto request)
            throws RegistrationException {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(
            @Valid @RequestBody UserLoginRequestDto request
    ) {
        return authenticationService.login(request);
    }
}
