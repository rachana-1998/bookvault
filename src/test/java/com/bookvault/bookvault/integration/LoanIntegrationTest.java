package com.bookvault.bookvault.integration;

import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.model.Member;
import com.bookvault.bookvault.model.MembershipStatus;
import com.bookvault.bookvault.repo.BookRepo;
import com.bookvault.bookvault.repo.MemberRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private MemberRepo memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldBorrowAndReturnBook() throws Exception {

        // Create Book
        Book book = new Book();
        book.setAvailableCopies(2);
        book = bookRepository.save(book);

        // Create Member
        Member member = new Member();
        member.setStatus(MembershipStatus.ACTIVE);
        member = memberRepository.save(member);

        // Borrow
        String response = mockMvc.perform(post("/api/loans")
                        .param("bookId", book.getId().toString())
                        .param("memberId", member.getId().toString()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode json = objectMapper.readTree(response);
        String loanId = json.get("data").get("id").asText();

        // Return
        mockMvc.perform(put("/api/loans/" + loanId + "/return"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.data.status").value("RETURNED"));
    }
}