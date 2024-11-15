package com.polstat.ukmbulstik.mapper;

import com.polstat.ukmbulstik.dto.MemberDto;
import com.polstat.ukmbulstik.entity.Member;

public class MemberMapper {
    public static Member mapToMember(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .memberID(memberDto.getMemberID()) // Pastikan memberID disetel
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }

    public static MemberDto mapToMemberDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .memberID(member.getMemberID()) // Pastikan memberID diambil
                .name(member.getName())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
