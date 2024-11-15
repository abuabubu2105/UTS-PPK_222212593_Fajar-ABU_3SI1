package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.MemberDto;
import java.util.List;

public interface MemberService {
    void addMember(MemberDto memberDto);
    boolean updateMember(Long id, MemberDto memberDto);
    boolean deleteMember(Long id);
    List<MemberDto> getAllMembers();
}
