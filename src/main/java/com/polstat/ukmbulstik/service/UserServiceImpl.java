package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.UserDto;
import com.polstat.ukmbulstik.entity.User;
import com.polstat.ukmbulstik.mapper.UserMapper;
import com.polstat.ukmbulstik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? UserMapper.mapToUserDto(user) : null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.mapToUser(userDto);
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserProfile(String email, UserDto userDto) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        user.setName(userDto.getName()); // Update fields sesuai kebutuhan
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

        return UserMapper.mapToUserDto(user);
    }

    @Override
    @Transactional
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false; // Password lama tidak cocok
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}