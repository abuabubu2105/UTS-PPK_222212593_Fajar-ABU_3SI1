package com.polstat.ukmbulstik.controller;

import com.polstat.ukmbulstik.dto.BorrowDto;
import com.polstat.ukmbulstik.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Operation(summary = "Pinjam Peralatan", description = "Endpoint untuk meminjam peralatan berdasarkan data yang diberikan pada request body.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Peralatan berhasil dipinjam!")
    })
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowEquipment(@RequestBody @Parameter(description = "Data peminjaman peralatan") BorrowDto borrowDto) {
        borrowService.borrowEquipment(borrowDto);
        return ResponseEntity.ok("Peralatan berhasil dipinjam!");
    }

    @Operation(summary = "Kembalikan Peralatan", description = "Endpoint untuk mengembalikan peralatan berdasarkan ID peminjaman.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Peralatan berhasil dikembalikan!"),
        @ApiResponse(responseCode = "404", description = "ID peminjaman tidak ditemukan.")
    })
    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnEquipment(@PathVariable @Parameter(description = "ID peminjaman peralatan") Long id) {
        borrowService.returnEquipment(id);
        return ResponseEntity.ok("Peralatan berhasil dikembalikan!");
    }

    @Operation(summary = "Ambil Daftar Semua Peminjaman", description = "Endpoint untuk mendapatkan daftar semua peminjaman peralatan.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Berhasil mendapatkan daftar peminjaman."),
        @ApiResponse(responseCode = "500", description = "Terjadi kesalahan pada server.")
    })
    @GetMapping("/getall")
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }
}
