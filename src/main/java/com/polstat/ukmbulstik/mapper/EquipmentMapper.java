/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.mapper;

import com.polstat.ukmbulstik.dto.EquipmentDto;
import com.polstat.ukmbulstik.entity.Equipment;

public class EquipmentMapper {
    public static Equipment mapToEquipment(EquipmentDto equipmentDto) {
        return Equipment.builder()
                .id(equipmentDto.getId())
                .namaBarang(equipmentDto.getNamaBarang())
                .merkBarang(equipmentDto.getMerkBarang())
                .hargaBarang(equipmentDto.getHargaBarang())
                .build();
    }

    public static EquipmentDto mapToEquipmentDto(Equipment equipment) {
        return EquipmentDto.builder()
                .id(equipment.getId())
                .namaBarang(equipment.getNamaBarang())
                .merkBarang(equipment.getMerkBarang())
                .hargaBarang(equipment.getHargaBarang())
                .build();
    }
}
