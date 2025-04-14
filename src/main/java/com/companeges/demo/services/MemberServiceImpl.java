package com.companeges.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.companeges.demo.models.Member;
import com.companeges.demo.repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MemberRepository memberRepository;

    public MemberPostPutDTO createMember(MemberPostPutDTO memberPostPutDTO) {
        Member member = modelMapper.map(memberPostPutDTO, Member.class);

        memberRepository.save(member);

        return modelMapper.map(member, MemberPostPutDTO.class);
    }

    @Override
    public List<MemberPostPutDTO> getMembers() {
        List<Member> members = this.memberRepository.findAll();

        return members.stream()
                .map(member -> new MemberPostPutDTO(member))
                .collect(Collectors.toList());
    }
}
