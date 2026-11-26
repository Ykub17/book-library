package mate.academy.booklibrary.service.impl;

import jakarta.transaction.Transactional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.booklibrary.dto.UserLoginRequestDto;
import mate.academy.booklibrary.dto.UserLoginResponseDto;
import mate.academy.booklibrary.dto.UserRegistrationRequestDto;
import mate.academy.booklibrary.dto.UserResponseDto;
import mate.academy.booklibrary.exception.EntityNotFoundException;
import mate.academy.booklibrary.exception.RegistrationException;
import mate.academy.booklibrary.mapper.UserMapper;
import mate.academy.booklibrary.model.Role;
import mate.academy.booklibrary.model.RoleName;
import mate.academy.booklibrary.model.User;
import mate.academy.booklibrary.repository.RoleRepository;
import mate.academy.booklibrary.repository.UserRepository;
import mate.academy.booklibrary.security.JwtUtil;
import mate.academy.booklibrary.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException(
                    "User with this email already exists " + request.getEmail()
            );
        }

        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new EntityNotFoundException(
                        RoleName.USER + " role not found"
                ));

        user.setRoles(Set.of(userRole));

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());

        UserLoginResponseDto response = new UserLoginResponseDto();
        response.setToken(token);

        return response;
    }
}
