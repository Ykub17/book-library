package mate.academy.booklibrary.service;

import mate.academy.booklibrary.dto.UserRegistrationRequestDto;
import mate.academy.booklibrary.dto.UserResponseDto;
import mate.academy.booklibrary.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
