//package br.com.desafio2.bootcamp;
//
//import br.com.desafio2.bootcamp.dtos.ReservationDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.text.SimpleDateFormat;
//import java.util.Locale;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ReservationsControllerTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void testGetReservations () throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/reservations"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
//    }
//}
