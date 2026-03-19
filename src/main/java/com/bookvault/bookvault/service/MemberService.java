package com.bookvault.bookvault.service;

import com.bookvault.bookvault.model.Member;
import com.bookvault.bookvault.repo.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }


    public Member getMember(UUID id) {
        return  memberRepo.findById(id).get();
    }


    public Member createMember(Member member) {
      return  memberRepo.save(member);
    }

    public Member updateMember(Member member) {
        return memberRepo.save(member);
    }
    public void deleteMember(UUID id) {
        memberRepo.deleteById(id);
    }
}
