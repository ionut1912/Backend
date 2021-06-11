package licenta.backend.controller;


import licenta.backend.jwt.JwtUtils;
import licenta.backend.model.Erole;
import licenta.backend.model.User;
import licenta.backend.payload.request.LoginRequest;
import licenta.backend.payload.request.SignupRequest;
import licenta.backend.payload.response.JwtResponse;
import licenta.backend.payload.response.MessageResponse;
import licenta.backend.repository.UserRepository;
import licenta.backend.service.EmailService;
import licenta.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Resource

    AuthenticationManager authenticationManager;
    @Resource

    UserRepository userRepository;
    @Resource

    PasswordEncoder encoder;
    @Resource

    UserService userService;
    @Resource

    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwtUtils.generateJwtToken(authentication),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Numele de utilizator  este existent!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email-ul este existent!"));
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getUsername(),

                encoder.encode(signUpRequest.getPassword()));


        user.setType(Erole.ROLE_USER);
        user.setEnabled(true);


        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
