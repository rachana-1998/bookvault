package com.bookvault.bookvault.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Member extends Base {
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private   MembershipStatus status=MembershipStatus.ACTIVE;

    private LocalDateTime joinedAt;


    @PrePersist
    public void prePersist(){
        this.joinedAt = LocalDateTime.now();
    }
}
