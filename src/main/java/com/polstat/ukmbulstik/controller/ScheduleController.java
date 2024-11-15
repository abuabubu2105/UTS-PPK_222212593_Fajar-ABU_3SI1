package com.polstat.ukmbulstik.controller;

import com.polstat.ukmbulstik.dto.ScheduleDto;
import com.polstat.ukmbulstik.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Create Schedule", description = "Endpoint untuk membuat jadwal baru.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jadwal berhasil dibuat!", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/create")
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody @Parameter(description = "Data jadwal yang akan dibuat") ScheduleDto scheduleDto) {
        ScheduleDto createdSchedule = scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.ok(createdSchedule);
    }

    @Operation(summary = "Get Schedule by ID", description = "Endpoint untuk mengambil jadwal berdasarkan ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jadwal ditemukan.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Jadwal tidak ditemukan.", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable @Parameter(description = "ID jadwal yang akan diambil") Long id) {
        ScheduleDto schedule = scheduleService.getSchedule(id);
        if (schedule != null) {
            return ResponseEntity.ok(schedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get All Schedules", description = "Endpoint untuk mengambil daftar semua jadwal.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Berhasil mendapatkan daftar jadwal.", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        List<ScheduleDto> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @Operation(summary = "Delete Schedule", description = "Endpoint untuk menghapus jadwal berdasarkan ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jadwal berhasil dihapus!", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Jadwal tidak ditemukan.", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable @Parameter(description = "ID jadwal yang akan dihapus") Long id) {
        boolean deleted = scheduleService.deleteSchedule(id);
        if (deleted) {
            return ResponseEntity.ok("Jadwal berhasil dihapus!");
        }
        return ResponseEntity.badRequest().body("Jadwal tidak ditemukan.");
    }
}
