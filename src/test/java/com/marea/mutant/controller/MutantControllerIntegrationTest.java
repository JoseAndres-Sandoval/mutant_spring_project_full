
package com.marea.mutant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marea.mutant.dto.DnaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testMutantEndpoint200() throws Exception {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        DnaRequest req = new DnaRequest(dna);
        ObjectMapper om = new ObjectMapper();
        mvc.perform(post("/mutant/")
                .contentType("application/json")
                .content(om.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void testMutantEndpoint403() throws Exception {
        String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        DnaRequest req = new DnaRequest(dna);
        ObjectMapper om = new ObjectMapper();
        mvc.perform(post("/mutant/")
                .contentType("application/json")
                .content(om.writeValueAsString(req)))
                .andExpect(status().isForbidden());
    }
}
