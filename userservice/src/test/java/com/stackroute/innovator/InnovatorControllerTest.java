package com.stackroute.innovator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.exception.InnovatorNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InnovatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    InnovatorService innovatorService;

    @InjectMocks
    InnovatorController innovatorController;

    private Innovator innovator1;
    private Innovator innovator2;

    @BeforeEach
    void setup(){
        innovator1 = new Innovator("100", "test1","first", "last","email", "profilePic","software developer", null, null, null);
        innovator2 = new Innovator("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null);
        mockMvc= MockMvcBuilders.standaloneSetup(innovatorController).build();
    }

    @AfterEach
    void tearDown(){
        innovator1 = null;
        innovator2 = null;
        mockMvc = null;
    }

    @Test
    public void getAllInnovatorsTest() throws Exception{
        List<Innovator> innovatorList = new ArrayList<Innovator>();
        innovatorList.add(innovator1);
        innovatorList.add(innovator2);
        when(innovatorService.getInnovators()).thenReturn(innovatorList);
        mockMvc.perform(get("/api/v1/innovators/"))
                .andExpect(status().isOk());
        verify(innovatorService, times(1)).getInnovators();
    }

    @Test
    public void getInnovatorTest() throws Exception{
        when(innovatorService.getInnovatorById(any())).thenReturn(innovator1);
        mockMvc.perform(get("/api/v1/innovators/"+innovator1.getInnovatorId()))
                .andExpect(status().isOk());
        verify(innovatorService, times(1)).getInnovatorById(any());
    }

    @Test
    public void updateInnovatorTest() throws Exception{
        when(innovatorService.updateInnovator(any())).thenReturn(innovator1);
        mockMvc.perform(put("/api/v1/innovators/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsontoString(innovator1)))
                .andExpect(status().isAccepted());
        verify(innovatorService, times(1)).updateInnovator(any());
    }


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
