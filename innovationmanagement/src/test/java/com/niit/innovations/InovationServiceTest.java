package com.niit.innovations;

import com.niit.exception.InnovationAlreadyExistsException;
import com.niit.exception.InnovationNotFoundException;
import org.bson.types.Binary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class InovationServiceTest {

    @Mock
    private InnovationRepo innovationRepo;
    @InjectMocks
    private InnovationServiceImpl innovationService;

    private Innovation innovation;

    @BeforeEach
    public void setup(){
        Files file=null;
        List<Files> allFiles = new LinkedList<>();
        innovation = new Innovation("123456","Name Testing","ID testing","Name","testing@gmail.com","innovationDesc",
                "Challenges","domain","status", (float) 4.2,"date of creation",file,allFiles);
    }

    @AfterEach
    public void tearDown(){
        innovation = null;
    }

//    @Test
//    public void saveInnovationTest() throws InnovationAlreadyExistsException, IOException {
//        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
//        when(innovationRepo.save(any())).thenReturn(innovation);
//        assertEquals(innovation,innovationService.saveInnovation(innovation));
//        verify(innovationRepo,times(1)).save(any());
//    }
//
//    @Test
//    public void allInnovationsTest()  {
//        List<Innovation> allInnovations = new LinkedList<>();
//        allInnovations.add(innovation);
//        when(innovationRepo.findAll()).thenReturn(allInnovations);
//        assertEquals(0,innovationService.getallInnovations("test@gmail.com").size());
//    }
//
//    @Test
//    public void deleteInnovationTest() throws InnovationNotFoundException {
//        assertThrows(InnovationNotFoundException.class, () ->{innovationService.deleteInnovation("1234567");});
//    }
//
//    @Test
//    public void updateInnovationTest() throws InnovationNotFoundException{
//        assertThrows(InnovationNotFoundException.class, () ->{innovationService.updateInnovation(innovation);});
//    }
//
//    @Test
//    public void getInnovationIdTest() throws InnovationNotFoundException{
//        when(innovationRepo.existsById("123456")).thenReturn(true);
//        when(innovationRepo.findById("123456")).thenReturn(java.util.Optional.ofNullable(innovation));
//        assertEquals(innovation,innovationService.getInnovationById("123456"));
//    }
}
