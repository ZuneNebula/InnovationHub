//package com.niit.innovations;
//
//import org.bson.types.Binary;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class InnovationRepoTest {
//
//    @Autowired
//    private InnovationRepo innovationRepo;
//    private Innovation innovation;
//
//    @BeforeEach
//    public void setup(){
//        Files file=null;
//        List<Files> allFiles = new LinkedList<>();
//        innovation = new Innovation("123456","Name Testing","ID testing","Name","testing@gmail.com","innovationDesc",
//                "Challenges","domain","status", (float) 4.2,"date of creation",file,allFiles);
//    }
//
//    @AfterEach
//    public void tearDown(){
//        innovation = null;
//    }
////
////    @Test
////    public void addInnovationTest(){
////        innovationRepo.save(innovation);
////        assertTrue(innovationRepo.findById("123456").isPresent());
////    }
////
////    @Test
////    public void deleteInnovationTest(){
////        innovationRepo.deleteById("123456");
////        assertFalse(innovationRepo.findById("123456").isPresent());
////    }
////
////    @Test
////    public void findByIdTest(){
////        innovationRepo.save(innovation);
////        assertEquals(java.util.Optional.ofNullable(innovation),innovationRepo.findById(innovation.getInnovationId()));
////    }
////
////    @Test
////    public void findAllTest(){
////        innovationRepo.save(innovation);
////        assertEquals(1,innovationRepo.findAll().size());
////    }
//}
