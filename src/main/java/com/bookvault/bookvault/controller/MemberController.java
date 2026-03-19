package com.bookvault.bookvault.controller;

import com.bookvault.bookvault.config.ApiResponse;
import com.bookvault.bookvault.model.Member;
import com.bookvault.bookvault.repo.MemberRepo;
import com.bookvault.bookvault.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepo memberRepo;

    MemberController(MemberService memberService, MemberRepo memberRepo) {
        this.memberService = memberService;
        this.memberRepo = memberRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> getMember(@PathVariable UUID id) {
      return ResponseEntity.status(HttpStatus.FOUND).body( ApiResponse.success(  memberService.getMember(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Member>> createMember(@Valid @RequestBody Member member) {
      return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success( memberService.createMember(member)));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<Member>> updateMember(@Valid  @RequestBody Member member){
        return ResponseEntity.status(HttpStatus.FOUND).body(ApiResponse.success(memberService.updateMember(member)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteMember(@PathVariable UUID id){

        memberRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(ApiResponse.success(true));
    }

}
