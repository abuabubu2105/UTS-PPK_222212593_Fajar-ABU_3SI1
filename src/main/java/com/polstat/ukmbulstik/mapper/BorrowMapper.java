/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.mapper;

import com.polstat.ukmbulstik.dto.BorrowDto;
import com.polstat.ukmbulstik.entity.Borrow;
import com.polstat.ukmbulstik.entity.Equipment;
import com.polstat.ukmbulstik.entity.Member;

public class BorrowMapper {

    public static Borrow mapToBorrow(BorrowDto borrowDto, Member member, Equipment equipment) {
    Borrow.BorrowStatus status;
    try {
        status = Borrow.BorrowStatus.valueOf(borrowDto.getBorrowStatus().toUpperCase());
    } catch (IllegalArgumentException | NullPointerException e) {
        throw new IllegalArgumentException("Status peminjaman tidak valid");
    }

    return Borrow.builder()
            .id(borrowDto.getId())
            .member(member)
            .equipment(equipment)
            .borrowDate(borrowDto.getBorrowDate())
            .returnDate(borrowDto.getReturnDate())
            .borrowStatus(status) // Gunakan status yang sudah divalidasi
            .overdueDays(borrowDto.getOverdueDays())
            .build();
}


    public static BorrowDto mapToBorrowDto(Borrow borrow) {
        return BorrowDto.builder()
                .id(borrow.getId())
                .memberId(borrow.getMember().getId())
                .equipmentId(borrow.getEquipment().getId())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .borrowStatus(borrow.getBorrowStatus().name()) // Convert Enum to String
                .overdueDays(borrow.getOverdueDays())
                .build();
    }
}
