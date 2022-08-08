//package com.stackroute.innovator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
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
//public class InnovatorRepositoryTest {
//
//    @Autowired
//    InnovatorRepository innovatorRepository;
//
//    private Innovator innovator1;
//    private Innovator innovator2;
//
//    @BeforeEach
//    public void setUp(){
//        innovator1 = new Innovator("100", "test1","first", "last","email", "profilePic","software developer", null, null, null);
//        innovator2 = new Innovator("101", "test2","mihir", "sharma","andro", "profilePic","software developer", null, null, null);
//    }
//
//
//    @AfterEach
//    public void tearDown(){
//        innovatorRepository.deleteAll();
//        innovator1 = null;
//        innovator2 = null;
//    }
//
//    @Test
//    public void saveInnovatorsTest(){
//        innovatorRepository.save(innovator1);
//        Innovator inno = innovatorRepository.findInnovatorById("100");
//        assertEquals(innovator1, inno);
//    }
//
//    @Test
//    public void getAllInnovatorsTest(){
//        innovatorRepository.save(innovator1);
//        innovatorRepository.save(innovator2);
//        assertEquals(2, innovatorRepository.findAll().size());
//    }
//
//    @Test
//    public void updateInnovatorsTest(){
//        innovatorRepository.save(innovator1);
//        Innovator inno = new Innovator("100", "test1","first", "last","email", "profilePic","software engineer", null, null, null);
//        innovatorRepository.save(inno);
//        assertEquals(1, innovatorRepository.findAll().size());
//        assertEquals(inno, innovatorRepository.findInnovatorById("100"));
//    }
//
//    @Test
//    public void getExpertsByUsernameTest(){
//        innovatorRepository.save(innovator1);
//        innovatorRepository.save(innovator2);
//        assertEquals(innovator1, innovatorRepository.findById("test1").get());
//        assertEquals(innovator2, innovatorRepository.findById("test2").get());
//    }
//
//    @Test
//    public void getInnovatorsByIdTest(){
//        innovatorRepository.save(innovator1);
//        innovatorRepository.save(innovator2);
//        assertEquals(innovator1, innovatorRepository.findInnovatorById("100"));
//        assertEquals(innovator2, innovatorRepository.findInnovatorById("101"));
//    }
//}
