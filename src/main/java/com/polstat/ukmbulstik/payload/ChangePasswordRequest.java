/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.payload;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */
@Getter
@Setter
public class ChangePasswordRequest {
    
    private String oldPassword;
    private String newPassword;   
}