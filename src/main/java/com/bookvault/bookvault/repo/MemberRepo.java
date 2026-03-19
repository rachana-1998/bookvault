package com.bookvault.bookvault.repo;

import com.bookvault.bookvault.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Repository
public interface MemberRepo extends JpaRepository<Member, UUID> {


}
