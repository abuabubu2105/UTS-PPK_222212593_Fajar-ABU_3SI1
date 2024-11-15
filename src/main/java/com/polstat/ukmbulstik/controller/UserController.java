package com.polstat.ukmbulstik.controller;

import com.polstat.ukmbulstik.dto.UserDto;
import com.polstat.ukmbulstik.payload.ChangePasswordRequest;
import com.polstat.ukmbulstik.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Mendapatkan profil pengguna saat ini
    @Operation(summary = "Get User Profile", description = "Endpoint untuk mendapatkan profil pengguna yang sedang login.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Berhasil mendapatkan profil pengguna", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "401", description = "Tidak terautentikasi", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(Authentication authentication) {
        String email = authentication.getName();
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    // 2. Mengedit profil pengguna
    @Operation(summary = "Update User Profile", description = "Endpoint untuk mengedit profil pengguna berdasarkan data yang diberikan.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profil pengguna berhasil diperbarui", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Request tidak valid", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UserDto userDto, Authentication authentication) {
        String email = authentication.getName();
        UserDto updatedUser = userService.updateUserProfile(email, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // 3. Mengganti password
    @Operation(summary = "Change User Password", description = "Endpoint untuk mengganti password pengguna yang sedang login.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password berhasil diganti", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Password lama tidak sesuai", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request, Authentication authentication) {
        String email = authentication.getName();
        boolean isPasswordChanged = userService.changePassword(email, request.getOldPassword(), request.getNewPassword());
        if (isPasswordChanged) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect");
        }
    }

    // 4. Menghapus akun pengguna
    @Operation(summary = "Delete User Account", description = "Endpoint untuk menghapus akun pengguna yang sedang login.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Akun berhasil dihapus", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Akun tidak ditemukan", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteUser(email);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
