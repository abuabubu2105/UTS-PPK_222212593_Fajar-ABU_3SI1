/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.BorrowDto;
import java.util.List;

/**
 *
 * @author User
 */
public interface BorrowService {
    void borrowEquipment(BorrowDto borrowDto); // Mengubah nama metode ke borrowEquipment
    void returnEquipment(Long borrowId);       // Mengubah nama metode ke returnEquipment
    List<BorrowDto> getAllBorrows();           // Mengubah nama metode ke getAllBorrows
}
