package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.MemberDto;
import com.polstat.ukmbulstik.entity.Member;
import com.polstat.ukmbulstik.mapper.MemberMapper;
import com.polstat.ukmbulstik.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void addMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        memberRepository.save(member);
    }

    @Override
    public boolean updateMember(Long id, MemberDto memberDto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setName(memberDto.getName());
            member.setAddress(memberDto.getAddress());
            member.setPhoneNumber(memberDto.getPhoneNumber());
            memberRepository.save(member);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MemberDto> getAllMembers() {
        List<Member> members = (List<Member>) memberRepository.findAll();
        return members.stream()
                .map(MemberMapper::mapToMemberDto)
                .collect(Collectors.toList());
    }
}
