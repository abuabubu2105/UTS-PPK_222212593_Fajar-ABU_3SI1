package com.polstat.ukmbulstik.controller;

import com.polstat.ukmbulstik.dto.MemberDto;
import com.polstat.ukmbulstik.service.MemberService;
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
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Operation(summary = "Tambah Member", description = "Endpoint untuk menambahkan member baru.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Member berhasil ditambahkan!", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestBody @Parameter(description = "Data member yang akan ditambahkan") MemberDto memberDto) {
        memberService.addMember(memberDto);
        return ResponseEntity.ok("Member berhasil ditambahkan!");
    }

    @Operation(summary = "Edit Member", description = "Endpoint untuk mengedit data member berdasarkan ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Member berhasil diperbarui!", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Member tidak ditemukan.", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editMember(@PathVariable @Parameter(description = "ID member yang akan diedit") Long id, 
                                              @RequestBody @Parameter(description = "Data member yang baru") MemberDto memberDto) {
        boolean updated = memberService.updateMember(id, memberDto);
        if (updated) {
            return ResponseEntity.ok("Member berhasil diperbarui!");
        } else {
            return ResponseEntity.badRequest().body("Member tidak ditemukan.");
        }
    }

    @Operation(summary = "Hapus Member", description = "Endpoint untuk menghapus member berdasarkan ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Member berhasil dihapus!", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Member tidak ditemukan.", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable @Parameter(description = "ID member yang akan dihapus") Long id) {
        boolean deleted = memberService.deleteMember(id);
        if (deleted) {
            return ResponseEntity.ok("Member berhasil dihapus!");
        } else {
            return ResponseEntity.badRequest().body("Member tidak ditemukan.");
        }
    }

    @Operation(summary = "Ambil Semua Member", description = "Endpoint untuk mengambil daftar semua member.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Berhasil mendapatkan daftar member.", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/getall")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        List<MemberDto> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
