package com.niit.innovations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.exception.InnovationNotFoundException;
import org.bson.types.Binary;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class InnovationControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Mock
    public InnovationService innovationService;
    public Innovation innovation;
    public Files files1;
    public Files files2;

    @InjectMocks
    public InnovationController innovationController;

    @BeforeEach
    public void setup(){
        List<Files> allFiles = new LinkedList<>();
        innovation = new Innovation("123456","Name Testing","ID testing","Name","testing@gmail.com","innovationDesc",
                "Challenges","domain","status", (float) 4.2,"date of creation",files1,allFiles);
        mockMvc = MockMvcBuilders.standaloneSetup(innovationController).build();
    }

    @AfterEach
    public void tearDown(){
        innovation = null;
        mockMvc = null;
    }

//    @Test
//    public void saveInnovationTest() throws Exception {
//        when(innovationService.saveInnovation(innovation)).thenReturn(innovation);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/innovations/save"))
//                .andExpect(status().isOk());
//        verify(innovationService, times(1)).saveInnovation(any());
//    }

//    @Test
//    public void allInnovationsTest() throws Exception, InnovationNotFoundException {
//        List<Innovation> allInnovatons = new LinkedList<>();
//        allInnovatons.add(innovation);
//        when(innovationService.getAllInnovations(innovation.getInnovatorId())).thenReturn(allInnovatons);
//        mockMvc
//                .perform(get("/api/v1/innovations/"+"testing"))
//                        .andExpect(status().isOk());
//    }
//
//    @Test
//    public void innovationByIdTest() throws Exception {
//        when(innovationService.getInnovationById(any())).thenReturn(innovation);
//        mockMvc.perform(get("/api/v1/innovations/id/"+"testing")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteInnovationTest() throws Exception {
//        when(innovationService.deleteInnovation(any())).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/innovations/"+"testing"))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void updateInnovationTest() throws Exception {
//        when(innovationService.updateInnovation(any())).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/innovations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsontoString(innovation)))
//                .andExpect(status().isAccepted());
//    }

    private String jsontoString(final Object obj) {
        String result;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonContent = objectMapper.writeValueAsString(obj);
            result = jsonContent;
        }
        catch (JsonProcessingException ex){
            result="error while converting to string";
        }
        return result;
    }


}
