package br.com.desafio2.bootcamp;

import br.com.desafio2.bootcamp.dtos.ClientDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testInsertClients() throws Exception {
        ClientDto client;
        client = new ClientDto();
        client.setName("José Maria");
        client.setEmail("joseM@gmail.com");

        String clientJson = new Gson().toJson(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientID").exists());
    }
}
