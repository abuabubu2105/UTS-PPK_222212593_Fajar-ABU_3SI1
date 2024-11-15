package com.polstat.ukmbulstik.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.polstat.ukmbulstik.dto.EquipmentDto;
import com.polstat.ukmbulstik.rpc.JsonRpcRequest;
import com.polstat.ukmbulstik.rpc.JsonRpcResponse;
import com.polstat.ukmbulstik.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class JsonRpcController {
    
    @Autowired
    private EquipmentService equipmentService;

    @Operation(summary = "Handle JSON-RPC Request", description = "Menerima dan memproses request JSON-RPC untuk berbagai metode seperti createEquipment, getEquipments, dan searchEquipments.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request berhasil diproses", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Request tidak valid", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/jsonrpc")
    public ResponseEntity<Object> handleJsonRpcRequest(
        @RequestBody @Parameter(description = "Request JSON-RPC") JsonRpcRequest request) {
        try {
            String method = request.getMethod();
            JsonNode params = request.getParams();
            System.out.println("Method: " + method);
            switch (method) {
                case "createEquipment":
                    String namaBarang = params.get("namaBarang").asText();
                    String merkBarang = params.get("merkBarang").asText();
                    double hargaBarang = params.get("hargaBarang").asDouble();
                    EquipmentDto equipment = EquipmentDto.builder()
                            .namaBarang(namaBarang)
                            .merkBarang(merkBarang)
                            .hargaBarang(hargaBarang)
                            .build();
                    equipmentService.createEquipment(equipment);
                    return ResponseEntity.ok(new JsonRpcResponse("created", request.getId()));
                case "getEquipments":
                    List<EquipmentDto> equipments = equipmentService.getEquipments();
                    return ResponseEntity.ok(new JsonRpcResponse(equipments, request.getId()));
                case "searchEquipments":
                    String keyword = params.get("keyword").asText();
                    List<EquipmentDto> result = equipmentService.searchEquipments(keyword);
                    return ResponseEntity.ok(new JsonRpcResponse(result, request.getId()));
                default:
                    return ResponseEntity.badRequest().body(new JsonRpcResponse("Invalid method", request.getId()));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JsonRpcResponse("Error processing request: " + e.getMessage(), request.getId()));
        }
    }
}
