package com.bookvault.bookvault.repo;

import com.bookvault.bookvault.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepo extends JpaRepository<Loan, UUID> {


    @Query("SELECT l FROM Loan l WHERE l.dueDate < CURRENT_TIMESTAMP AND l.status = 'BORROWED'")
    List<Loan> findOverdueLoans();

    List<Loan> findByMemberId(UUID memberId);
}
