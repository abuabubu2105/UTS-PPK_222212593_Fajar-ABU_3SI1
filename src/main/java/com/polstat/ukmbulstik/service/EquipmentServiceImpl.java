/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.EquipmentDto;
import com.polstat.ukmbulstik.entity.Equipment;
import com.polstat.ukmbulstik.mapper.EquipmentMapper;
import com.polstat.ukmbulstik.repository.EquipmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public void createEquipment(EquipmentDto equipmentDto) {
        equipmentRepository.save(EquipmentMapper.mapToEquipment(equipmentDto));
    }

    @Override
    public List<EquipmentDto> getEquipments() {
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments.stream()
                .map(EquipmentMapper::mapToEquipmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> searchEquipments(String keyword) {
        List<Equipment> equipments = equipmentRepository.searchEquipment(keyword);
        return equipments.stream()
                .map(EquipmentMapper::mapToEquipmentDto)
                .collect(Collectors.toList());
    }
}
