package com.stackroute.innovator;

import com.stackroute.exception.InnovatorNotFoundException;
import com.stackroute.exception.InnovatorAlreadyExistsException;
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
public class InnovatorServiceTest {

    @Mock
    private InnovatorRepository innovatorRepository;

    @InjectMocks
    private InnovatorServiceImpl innovatorService;

    private Innovator innovator1;
    private Innovator innovator2;

    @BeforeEach
    public void setUp(){
        innovator1 = new Innovator("100", "test1","first", "last","email", "profilePic","software developer", null, null, null);
        innovator2 = new Innovator("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null);
    }

    @AfterEach
    public void tearDown(){
        innovatorRepository.deleteAll();
        innovator1 = null;
        innovator2 = null;
    }

    @Test
    public void saveInnovatorsTest() throws InnovatorAlreadyExistsException{
        when(innovatorRepository.save(any())).thenReturn(innovator1);
        assertEquals(innovator1, innovatorService.saveInnovator(innovator1));
        verify(innovatorRepository, times(1)).save(any());
        verify(innovatorRepository, times(1)).findById(any());
    }

    @Test
    public void getInnovatorsTest(){
        List<Innovator> innovatorList = new ArrayList<Innovator>();
        innovatorList.add(innovator1);
        innovatorList.add(innovator2);
        when(innovatorRepository.findAll()).thenReturn(innovatorList);
        assertEquals(2, innovatorService.getInnovators().size());
        verify(innovatorRepository, times(1)).findAll();
    }

    @Test
    public void getInnovatorsByUsernameTest() throws InnovatorNotFoundException{
        Optional<Innovator> inno = Optional.of(innovator1);
        when(innovatorRepository.findById(any())).thenReturn(inno);
        assertEquals(inno, innovatorService.getInnovatorByUsername("test1"));
        verify(innovatorRepository, times(2)).findById(any());
    }

    @Test
    public void getInnovatorsByIdTest() throws InnovatorNotFoundException{
        when(innovatorRepository.findInnovatorById(any())).thenReturn(innovator2);
        assertEquals(innovator2, innovatorService.getInnovatorById("101"));
        verify(innovatorRepository, times(2)).findInnovatorById(any());
    }

    @Test
    public void updateInnovatorsTest() throws InnovatorNotFoundException{
        Innovator inno = new Innovator("100", "test1","first", "last","email", "profilePic","software engineer", null, null, null);
        when(innovatorRepository.findById(any())).thenReturn(Optional.of(innovator1));
        when(innovatorRepository.save(any())).thenReturn(inno);
        assertEquals(inno, innovatorService.updateInnovator(inno));
        verify(innovatorRepository, times(1)).save(any());
    }


}
