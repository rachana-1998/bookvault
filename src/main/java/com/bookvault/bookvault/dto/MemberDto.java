package com.bookvault.bookvault.dto;

import com.bookvault.bookvault.model.MembershipStatus;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String email;

    private String name;


    private MembershipStatus status=MembershipStatus.ACTIVE;

    private LocalDateTime joinedAt;


    @PrePersist
    public void prePersist(){
        this.joinedAt = LocalDateTime.now();
    }
}
