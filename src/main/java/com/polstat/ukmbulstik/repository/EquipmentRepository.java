/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.repository;

import com.polstat.ukmbulstik.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author User
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    @Query("SELECT e FROM Equipment e WHERE LOWER(e.namaBarang) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(e.merkBarang) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Equipment> searchEquipment(@Param("searchTerm") String searchTerm);

    @Query("SELECT e FROM Equipment e WHERE LOWER(e.merkBarang) LIKE LOWER(CONCAT('%', :merkBarang, '%')) AND LOWER(e.namaBarang) LIKE LOWER(CONCAT('%', :namaBarang, '%'))")
    List<Equipment> searchEquipmentByMerkAndName(@Param("merkBarang") String merkBarang, @Param("namaBarang") String namaBarang);
}
