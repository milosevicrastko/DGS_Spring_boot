package com.dgsApp.poc.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String GRAPHQL_ENDPOINT = "/graphql";

    @Test
    void testGetAllUsers() throws Exception {

        String query = "{ \"query\": \"{ users { id name } }\" }";
        mockMvc.perform(post("/graphql")
                        .contentType("application/json")
                        .content(query))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        String mutation = "{ \"query\": \"mutation { createUser(input: { name: \\\"John Doe\\\", email: \\\"john@example.com\\\" }) { id name email } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mutation))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.createUser.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.createUser.email").value("john@example.com"));
    }

    @Test
    void testReadUser() throws Exception {
        String query = "{ \"query\": \"{ user(id: \\\"1\\\") { id name email } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(query))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user.email").value("john@example.com"));
    }

    @Test
    void testUpdateUser() throws Exception {
        String mutation = "{ \"query\": \"mutation { updateUser(id: \\\"1\\\", input: { name: \\\"Jane Doe\\\", email: \\\"jane@example.com\\\" }) { id name email } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mutation))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.updateUser.name").value("Jane Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.updateUser.email").value("jane@example.com"));
    }

    @Test
    void testDeleteUser() throws Exception {
        String mutation = "{ \"query\": \"mutation { deleteUser(id: \\\"1\\\") { id } }\" }";

        mockMvc.perform(post(GRAPHQL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mutation))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.deleteUser.id").value("1"));
    }

}
