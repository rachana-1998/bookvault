package com.bookvault.bookvault.repo;

import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.model.Loan;
import com.bookvault.bookvault.model.Member;
import com.bookvault.bookvault.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LoanRepoTest {

        @Autowired
        private LoanRepo loanRepository;

        @Autowired
        private TestEntityManager entityManager;

        @Test
        void shouldFindOverdueLoans() {

            Member member = new Member();
            member.setId(UUID.randomUUID());
            entityManager.persist(member);

            Book book = new Book();
            book.setId(UUID.randomUUID());
            book.setAvailableCopies(5);
            entityManager.persist(book);

            Loan loan = new Loan();
            loan.setMember(member);
            loan.setBook(book);
            loan.setStatus(Status.ACTIVE);
            loan.setDueDate(LocalDateTime.now().minusDays(1)); // overdue

            entityManager.persist(loan);

            List<Loan> overdueLoans = loanRepository.findOverdueLoans();

            assertEquals(1, overdueLoans.size());
        }

}
