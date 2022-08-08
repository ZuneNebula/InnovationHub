//package com.stackroute.expert;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
////@SpringBootTest(properties = {"server.port=8080","spring.data.mongodb.uri=mongodb://localhost:27017","spring.data.mongodb.database=innovationhub"})
//@DataMongoTest
//public class ExpertRepositoryTest {
//
//    //save expert, get all experts, get expert(id), update expert, get expert(username)
//    @Autowired
//    ExpertRepository expertRepository;
//
//    private Expert expert1;
//    private Expert expert2;
//
//    @BeforeEach
//    public void setUp(){
//        expert1 = new Expert("100", "test1","first", "last","email", "profilePic","software developer", null, null, null,null,2.0f);
//        expert2 = new Expert("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null, null,3.0f);
//    }
//
//
//    @AfterEach
//    public void tearDown(){
//        expertRepository.deleteAll();
//        expert1 = null;
//        expert2 = null;
//    }
//
//    @Test
//    public void saveExpertsTest(){
//        expertRepository.save(expert1);
//        Expert exp = expertRepository.findExpertById("100");
//        assertEquals(expert1, exp);
//    }
//
//    @Test
//    public void getAllExpertsTest(){
//        expertRepository.save(expert1);
//        expertRepository.save(expert2);
//        assertEquals(2, expertRepository.findAll().size());
//    }
//
//    @Test
//    public void updateExpertsTest(){
//        expertRepository.save(expert1);
//        Expert exp = new Expert("100", "test1","first", "last","email", "profilePic","software developer", null, null, null, null,2.0f);
//        expertRepository.save(exp);
//        assertEquals(1, expertRepository.findAll().size());
//        assertEquals(exp, expertRepository.findExpertById("100"));
//    }
//
//    @Test
//    public void getExpertsByUsernameTest(){
//        expertRepository.save(expert1);
//        expertRepository.save(expert2);
//        assertEquals(expert1, expertRepository.findById("test1").get());
//        assertEquals(expert2, expertRepository.findById("test2").get());
//    }
//
//    @Test
//    public void getExpertsByIdTest(){
//        expertRepository.save(expert1);
//        expertRepository.save(expert2);
//        assertEquals(expert1, expertRepository.findExpertById("100"));
//        assertEquals(expert2, expertRepository.findExpertById("101"));
//    }
//}
