package com.companeges.demo.services;

import java.util.List;

import com.companeges.demo.dto.MemberPostPutDTO;

public interface MemberService {
    MemberPostPutDTO createMember(MemberPostPutDTO memberPostPutDTO);
    List<MemberPostPutDTO> getMembers();
}
