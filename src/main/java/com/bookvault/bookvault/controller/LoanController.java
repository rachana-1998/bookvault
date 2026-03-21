package com.bookvault.bookvault.controller;


import com.bookvault.bookvault.dto.ApiResponse;
import com.bookvault.bookvault.model.Loan;
import com.bookvault.bookvault.service.LoanService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<List<Loan>>> getOverdueBook() {
        return ResponseEntity.ok(ApiResponse.success(loanService.getOverdueLoans()));
    }
    @GetMapping("/member/{id}/loans")
    public  ResponseEntity<ApiResponse<List<Loan>>> memberLoans(@PathVariable UUID id){
        return ResponseEntity.ok(
                ApiResponse.success(loanService.getMemberLoans(id))
        );
    }


    @PutMapping("/loans/{id}/return")
    public ResponseEntity<ApiResponse<Loan>> returnBook(@PathVariable UUID id) throws BadRequestException {

        Loan loan = loanService.returnBook(id);
        return ResponseEntity.ok(ApiResponse.success(loan));
    }


    @PostMapping("/loans")
    public ResponseEntity<ApiResponse<Loan>> borrowBook(@RequestParam UUID bookId,@RequestParam UUID memberId) throws BadRequestException {
       Loan loan= loanService.borrowBook(bookId,memberId);

        return ResponseEntity.ok(ApiResponse.success(loan));
    }

}
