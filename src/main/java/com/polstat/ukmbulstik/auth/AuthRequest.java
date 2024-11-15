package com.polstat.ukmbulstik.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class AuthRequest {
    
    @NotNull 
    @Email
    @Size(max = 50) // Validasi panjang maksimum untuk email
    private String email;

    @NotNull
    @Size(max = 16) // Validasi panjang maksimum untuk password
    private String password;
}
