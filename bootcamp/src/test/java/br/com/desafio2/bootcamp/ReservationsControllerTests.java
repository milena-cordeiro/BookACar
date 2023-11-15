package br.com.desafio2.bootcamp;

import br.com.desafio2.bootcamp.dtos.ReservationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetReservations () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testInsertReservation () throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String start = "2023-11-16";
        String end = "2023-11-20";

        ReservationDto reservation = new ReservationDto();
        reservation.setStart(java.sql.Date.valueOf(start));
        reservation.setEnd(java.sql.Date.valueOf(end));
        reservation.setCarId(1);
        reservation.setClientId(2);

        String reservationJson = objectMapper.writeValueAsString(reservation);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationID").exists());
    }
}
