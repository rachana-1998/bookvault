package com.bookvault.bookvault.service;

import com.bookvault.bookvault.exp.ResourceNotFoundException;
import com.bookvault.bookvault.model.*;
import com.bookvault.bookvault.repo.BookRepo;
import com.bookvault.bookvault.repo.LoanRepo;
import com.bookvault.bookvault.repo.MemberRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepo loanRepo;
    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;

    public LoanService(LoanRepo loanRepo, BookRepo bookRepo, MemberRepo memberRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    public List<Loan> getOverdueLoans() {
        return loanRepo.findOverdueLoans();
    }

    public List<Loan> getMemberLoans(UUID memberId) {
        return loanRepo.findByMemberId(memberId);
    }

    public Loan returnBook(UUID id) throws BadRequestException {
        Loan loan=loanRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("loan not found"));
        if(loan.getStatus()== Status.RETURNED){
            throw new BadRequestException("loan is already returned");
        }
        loan.setStatus(Status.RETURNED);
        Book book=loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        return loanRepo.save(loan);
    }

    public Loan borrowBook(UUID bookId,UUID memberId) throws BadRequestException {
        Member member=memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member is not available"));
      if(member.getStatus()== MembershipStatus.SUSPENDED){
         throw new BadRequestException("member is suspended");
       }
      Book book=bookRepo.findById(bookId).orElseThrow(()->new ResourceNotFoundException("book is not available"));
      if(book.getAvailableCopies()<=0){
          throw new BadRequestException("book copy are not available");
      }
      book.setAvailableCopies(book.getAvailableCopies()-1);
      bookRepo.save(book);

      Loan loan=new Loan();
      loan.setBook(book);
      loan.setBorrowedAt(LocalDateTime.now());
      loan.setMember(member);
      loan.setStatus(Status.ACTIVE);
      loan.setDueDate(LocalDateTime.now().plusDays(14));

      return loanRepo.save(loan);

    }
}
