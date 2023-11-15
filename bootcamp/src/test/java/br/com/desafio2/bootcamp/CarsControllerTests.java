package br.com.desafio2.bootcamp;

import br.com.desafio2.bootcamp.dtos.CarDto;
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

@SpringBootTest
@AutoConfigureMockMvc
public class CarsControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testListAllCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testGetById() throws Exception{
        int carId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/{carId}", carId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carID").value(1));}

    @Test
    void testSaveCar() throws Exception {
        CarDto carDto;
        carDto = new CarDto();
        carDto.setCarID(1);
        carDto.setModel("corsa");
        carDto.setBrand("chevrolet");
        carDto.setColor("white");
        carDto.setYear(1998);
        carDto.setPlate("MDA2584");
        carDto.setAvailable(true);

        String carjson = new Gson().toJson(carDto);

         mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carjson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.carID").exists());

    }
}
