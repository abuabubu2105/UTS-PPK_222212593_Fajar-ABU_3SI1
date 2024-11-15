package com.polstat.ukmbulstik.auth;

import com.polstat.ukmbulstik.dto.UserDto;
import com.polstat.ukmbulstik.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;

    @Operation(summary = "Login", description = "Authenticate a user and generate an access token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully logged in", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "401", description = "Invalid email or password"),
        @ApiResponse(responseCode = "500", description = "An error occurred during login")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String accessToken = jwtUtil.generateAccessToken(authentication);
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email or password");
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during login");
        }
    }

    @Operation(summary = "Register", description = "Register a new user and create an account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User successfully registered", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Email already registered"),
        @ApiResponse(responseCode = "500", description = "An error occurred during registration")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto request) {
        try {
            UserDto existingUser = userService.getUserByEmail(request.getEmail());
            if (existingUser != null) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email sudah terdaftar. Gunakan email lain.");
            }

            UserDto newUser = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Terjadi kesalahan selama proses registrasi: " + ex.getMessage());
        }
    }
}
