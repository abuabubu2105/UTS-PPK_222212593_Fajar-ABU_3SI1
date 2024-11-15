/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.BorrowDto;
import com.polstat.ukmbulstik.entity.Borrow;
import com.polstat.ukmbulstik.entity.Equipment;
import com.polstat.ukmbulstik.entity.Member;
import com.polstat.ukmbulstik.mapper.BorrowMapper;
import com.polstat.ukmbulstik.repository.BorrowRepository;
import com.polstat.ukmbulstik.repository.EquipmentRepository;
import com.polstat.ukmbulstik.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public void borrowEquipment(BorrowDto borrowDto) {
    if (borrowDto.getMemberId() == null) {
        throw new IllegalArgumentException("Member ID tidak boleh null");
    }
    if (borrowDto.getEquipmentId() == null) {
        throw new IllegalArgumentException("Equipment ID tidak boleh null");
    }

    Member member = memberRepository.findById(borrowDto.getMemberId())
            .orElseThrow(() -> new IllegalArgumentException("Member tidak ditemukan"));

    Equipment equipment = equipmentRepository.findById(borrowDto.getEquipmentId())
            .orElseThrow(() -> new IllegalArgumentException("Peralatan tidak ditemukan"));

    Borrow borrow = BorrowMapper.mapToBorrow(borrowDto, member, equipment);
    borrow.setBorrowDate(LocalDate.now());
    borrow.setBorrowStatus(Borrow.BorrowStatus.BORROWED); // Menggunakan enum untuk status
    borrowRepository.save(borrow); // Simpan objek 'borrow'
}


    @Override
    public void returnEquipment(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow tidak ditemukan"));

        borrow.setReturnDate(LocalDate.now());
        borrow.setBorrowStatus(Borrow.BorrowStatus.RETURNED); // Menggunakan enum untuk status

        LocalDate dueDate = borrow.getBorrowDate().plusDays(14);
        if (borrow.getReturnDate().isAfter(dueDate)) {
            borrow.setOverdueDays((int) borrow.getReturnDate().toEpochDay() - (int) dueDate.toEpochDay());
        } else {
            borrow.setOverdueDays(0);
        }

        borrowRepository.save(borrow);
    }

    @Override
    public List<BorrowDto> getAllBorrows() {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrows.stream()
                .map(BorrowMapper::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
