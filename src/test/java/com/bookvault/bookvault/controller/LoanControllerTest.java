package com.bookvault.bookvault.controller;

import com.bookvault.bookvault.model.Loan;
import com.bookvault.bookvault.model.Status;
import com.bookvault.bookvault.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanService loanService;

    @Test
    void shouldBorrowBook() throws Exception {

        Loan loan = new Loan();
        loan.setId(UUID.randomUUID());
        loan.setStatus(Status.ACTIVE);

        when(loanService.borrowBook(any(), any())).thenReturn(loan);

        mockMvc.perform(post("/api/loans")
                        .param("bookId", UUID.randomUUID().toString())
                        .param("memberId", UUID.randomUUID().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("BORROWED"));
    }
}