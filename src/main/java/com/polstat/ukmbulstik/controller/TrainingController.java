package com.polstat.ukmbulstik.controller;

import com.polstat.ukmbulstik.dto.TrainingDto;
import com.polstat.ukmbulstik.service.TrainingService;
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
@RequestMapping("/api/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Operation(summary = "Register New Training", description = "Endpoint untuk mendaftar training baru.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Training berhasil didaftarkan.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<TrainingDto> registerTraining(@RequestBody @Parameter(description = "Data training yang akan didaftarkan") TrainingDto trainingDto) {
        TrainingDto registeredTraining = trainingService.registerTraining(trainingDto);
        return ResponseEntity.ok(registeredTraining);
    }

    @Operation(summary = "Cancel Training", description = "Endpoint untuk membatalkan training berdasarkan ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Training berhasil dibatalkan.", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Training tidak ditemukan.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelTraining(@PathVariable @Parameter(description = "ID training yang akan dibatalkan") Long id) {
        boolean cancelled = trainingService.cancelTraining(id);
        if (cancelled) {
            return ResponseEntity.ok("Training cancelled successfully!");
        }
        return ResponseEntity.badRequest().body("Training not found.");
    }

    @Operation(summary = "Get Trainings by Schedule ID", description = "Endpoint untuk mengambil daftar training berdasarkan schedule ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Berhasil mendapatkan daftar training berdasarkan schedule ID.", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsBySchedule(@PathVariable @Parameter(description = "ID jadwal yang akan digunakan untuk mencari training") Long scheduleId) {
        List<TrainingDto> trainings = trainingService.getTrainingsBySchedule(scheduleId);
        return ResponseEntity.ok(trainings);
    }
}
