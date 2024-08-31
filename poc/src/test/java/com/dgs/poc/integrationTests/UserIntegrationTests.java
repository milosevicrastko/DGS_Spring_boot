 package com.dgs.poc.integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
class UserIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String GRAPHQL_ENDPOINT = "/graphql";

    @Test
    void testGetAllUsers() throws Exception {

        String query = "{ \"query\": \"{ users { id name } }\" }";
        mockMvc.perform(post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(query))
                .andExpect(status().isOk());
    }

    @Test
    void testReadUser() throws Exception {
        String query = "{ \"query\": \"{ user(id: 2) { id name email } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(query))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        String mutation = "{ \"query\": \"mutation { deleteUser(id: 1) { id } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mutation))
                .andExpect(status().isOk());
    }

}
