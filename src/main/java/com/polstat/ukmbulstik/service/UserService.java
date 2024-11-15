package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.UserDto;
import com.polstat.ukmbulstik.entity.User;

public interface UserService {
    UserDto getUserByEmail(String email);
    UserDto createUser(UserDto userDto);
    UserDto updateUserProfile(String email, UserDto userDto);
    boolean changePassword(String email, String oldPassword, String newPassword);
    void deleteUser(String email);
}
