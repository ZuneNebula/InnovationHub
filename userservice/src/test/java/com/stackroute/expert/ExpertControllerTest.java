package com.stackroute.expert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.exception.ExpertNotFoundException;
import com.stackroute.innovator.Innovator;
import com.stackroute.innovator.InnovatorController;
import com.stackroute.innovator.InnovatorService;
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
public class ExpertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ExpertService expertService;

    @InjectMocks
    ExpertController expertController;

    private Expert expert1;
    private Expert expert2;
    private String[] spec;

    @BeforeEach
    void setup(){
        spec = new String[]{"Software & Technology", "Food"};
        expert1 = new Expert("100", "test1","first", "last","email", "profilePic","software developer", null, null, null, spec, 2.0f);
        expert2 = new Expert("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null, spec, 3.0f);
        mockMvc= MockMvcBuilders.standaloneSetup(expertController).build();
    }

    @AfterEach
    void tearDown(){
        expert1 = null;
        expert2 = null;
        spec=null;
        mockMvc = null;
    }

    @Test
    public void getAllExpertsTest() throws Exception{
        List<Expert> expertList = new ArrayList<Expert>();
        expertList.add(expert1);
        expertList.add(expert2);
        when(expertService.getExperts()).thenReturn(expertList);
        mockMvc.perform(get("/api/v1/experts/"))
                .andExpect(status().isOk());
        verify(expertService, times(1)).getExperts();
    }

    @Test
    public void getExpertsByIdTest() throws Exception{
        when(expertService.getExpertById(any())).thenReturn(expert1);
        mockMvc.perform(get("/api/v1/experts/"+expert1.getExpertId()))
                .andExpect(status().isOk());
        verify(expertService, times(1)).getExpertById(any());
    }

    @Test
    public void updateInnovatorTest() throws Exception{
        when(expertService.updateExpert(any())).thenReturn(expert1);
        mockMvc.perform(put("/api/v1/experts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsontoString(expert1)))
                .andExpect(status().isAccepted());
        verify(expertService, times(1)).updateExpert(any());
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
