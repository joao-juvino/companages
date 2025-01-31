package com.companeges.demo.services;

import java.lang.reflect.Member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companeges.demo.dto.MemberPostPutDTO;
import com.companeges.demo.repositories.MemberRepository;

@Service
public class MemberServiceImpl {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MemberRepository memberRepository;

    public MemberPostPutDTO createMember(MemberPostPutDTO memberPostPutDTO) {
        Member member = modelMapper.map(memberPostPutDTO, Member.class);

        memberRepository.save(member);

        return modelMapper.map(member, MemberPostPutDTO.class);
    }
}
