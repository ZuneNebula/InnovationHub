package com.stackroute.expert;

import com.stackroute.exception.ExpertNotFoundException;
import com.stackroute.exception.ExpertAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ExpertServiceTest {

    @Mock
    private ExpertRepository expertRepository;

    @InjectMocks
    private ExpertServiceImpl expertService;

    private Expert expert1;
    private Expert expert2;
    private String[] spec;

    @BeforeEach
    public void setUp(){
        spec = new String[]{"Software & Technology", "Food"};
        expert1 = new Expert("100", "test1","first", "last","email", "profilePic","software developer", null, null, null,spec, 2.0f);
        expert2 = new Expert("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null, spec, 3.0f);
    }

    @AfterEach
    public void tearDown(){
        expertRepository.deleteAll();
        expert1 = null;
        expert2 = null;
        spec=null;
    }

    @Test
    public void saveExpertsTest() throws ExpertAlreadyExistsException {
        when(expertRepository.save(any())).thenReturn(expert1);
        assertEquals(expert1, expertService.saveExpert(expert1));
        verify(expertRepository, times(1)).save(any());
        verify(expertRepository, times(1)).findById(any());
    }

    @Test
    public void getExpertsTest(){
        List<Expert> expertsList = new ArrayList<Expert>();
        expertsList.add(expert1);
        expertsList.add(expert2);
        when(expertRepository.findAll()).thenReturn(expertsList);
        assertEquals(2, expertService.getExperts().size());
        verify(expertRepository, times(1)).findAll();
    }

    @Test
    public void getExpertsByUsernameTest() throws ExpertNotFoundException {
        Optional<Expert> exp = Optional.of(expert1);
        when(expertRepository.findById(any())).thenReturn(exp);
        assertEquals(exp, expertService.getExpertByUsername("test1"));
        verify(expertRepository, times(2)).findById(any());
    }

    @Test
    public void getExpertsByIdTest() throws ExpertNotFoundException{
        when(expertRepository.findExpertById(any())).thenReturn(expert2);
        assertEquals(expert2, expertService.getExpertById("101"));
        verify(expertRepository, times(2)).findExpertById(any());
    }

    @Test
    public void updateExpertsTest() throws ExpertNotFoundException{
        Expert exp = new Expert("100", "test1","first", "last","email", "profilePic","software engineer", null, null, null, spec, 3.0f);
        when(expertRepository.findById(any())).thenReturn(Optional.of(expert1));
        when(expertRepository.save(any())).thenReturn(exp);
        assertEquals(exp, expertService.updateExpert(exp));
        verify(expertRepository, times(1)).save(any());
    }
}
