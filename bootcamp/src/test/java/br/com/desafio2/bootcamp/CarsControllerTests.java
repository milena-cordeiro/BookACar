package br.com.desafio2.bootcamp;

import br.com.desafio2.bootcamp.dtos.CarDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarsControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    void testListAllCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
    @Test
    void testSaveCar() throws Exception {
        CarDto carDto;
        carDto = new CarDto();
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

    @Test
    void testGetById() throws Exception{
        CarDto carDto;
        carDto = new CarDto();
        carDto.setModel("uno");
        carDto.setBrand("fiat");
        carDto.setColor("black");
        carDto.setYear(2015);
        carDto.setPlate("MVA2584");
        carDto.setAvailable(true);

        String carjson = new Gson().toJson(carDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carjson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andReturn();
//        recupera o conteudo do response Body
        String responseBody = result.getResponse().getContentAsString();

//        converte para elemnto json e recupera o id
        JsonElement element = JsonParser.parseString(responseBody);
        int idFromResponse = element.getAsJsonObject().get("carID").getAsInt();

//        com id recuperado faz a busca para retornar o carro referente ao id passado
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/{carId}", idFromResponse))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.carID").value(idFromResponse));
    }

    @Test
    void testDeleteCar() throws Exception {
        CarDto carDto;
        carDto = new CarDto();
        carDto.setModel("uno");
        carDto.setBrand("fiat");
        carDto.setColor("black");
        carDto.setYear(2015);
        carDto.setPlate("MVA2584");
        carDto.setAvailable(true);

        String carjson = new Gson().toJson(carDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carjson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
//        ao inserir um novo carro, recupera seu conteudo
        String responseBody = result.getResponse().getContentAsString();

//converte para jsonElement e recupera o id
        JsonElement element = JsonParser.parseString(responseBody);
        int idFromResponse = element.getAsJsonObject().get("carID").getAsInt();

//com o id do carro inserido testamos se deleta corretamente
        mockMvc.perform(MockMvcRequestBuilders.delete("/cars/{carId}", idFromResponse))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void testGetByAvailable() throws Exception {
        CarDto carDto1 = new CarDto();
        carDto1.setModel("corsa");
        carDto1.setBrand("chevrolet");
        carDto1.setColor("white");
        carDto1.setYear(1998);
        carDto1.setPlate("MDA2584");
        carDto1.setAvailable(false);

        String carJson1 = new Gson().toJson(carDto1);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson1))
                .andExpect(MockMvcResultMatchers.status().isCreated());

// ao inserir novo carro indisponivel, faz a busca apenas pelos que estão disponiveis
        MvcResult results = mockMvc.perform(MockMvcRequestBuilders.get("/cars/available"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

//        recupera o retorno
        String responseBody = results.getResponse().getContentAsString();

//        transforma em lista
        Type carListType = new TypeToken<List<CarDto>>(){}.getType();
        List<CarDto> cars = new Gson().fromJson(responseBody, carListType);

// verifica se todos os carros da lista estão disponiveis
        for (CarDto car : cars) {
            assert  car.getAvailable();
        }
    }
}
