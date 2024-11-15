/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license/default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.EquipmentDto;
import java.util.List;

/**
 * Interface for managing equipment services in the `ukm bulstik` system.
 * Provides methods for creating, retrieving, and searching equipment data.
 * 
 * @version 1.0
 */
public interface EquipmentService {

    /**
     * Creates a new equipment record.
     *
     * @param equipmentDto Data transfer object containing equipment details
     */
    void createEquipment(EquipmentDto equipmentDto);

    /**
     * Retrieves a list of all equipment records.
     *
     * @return List of equipment DTOs
     */
    List<EquipmentDto> getEquipments();

    /**
     * Searches equipment records based on a specified keyword.
     *
     * @param keyword Search keyword to filter equipment records
     * @return List of matching equipment DTOs
     */
    List<EquipmentDto> searchEquipments(String keyword);
}
