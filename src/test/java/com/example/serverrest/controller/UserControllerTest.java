package com.example.serverrest.controller;

import com.example.serverrest.models.User;
import com.example.serverrest.repository.Userrepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    Userrepository userrepository;
    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void addUser() throws Exception {
    //   Object randomObj = new Object() {
    //      String name = "Alisadddd";
    //               String email="44444432aal@google.com";
    //               boolean status= true;
    //   };
    //   ObjectMapper objectMapper = new ObjectMapper();
    //   objectMapper.setVisibility(JsonMethod.FIELD)
    //   String json = objectMapper.writeValueAsString(randomObj);

       // ResultActions aa= mockMvc.perform(post("/addpictur","user").).
       //         andExpect((ResultMatcher) UserTestData.user);

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createEmployeeAPI() throws Exception {
       // MockMultipartFile image = new MockMultipartFile("file", "appllication/json","{\"image\" :\"C:\\Users\\ruzga\\IdeaProjects\\ServerRest\\uplode\\3BTFnv0817253.970.jpg\"}".getBytes());
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/addpictur")
                        .content(asJsonString(UserTestData.user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.UserTestData.user").exists());
    }

        //MockMultipartFile image = new MockMultipartFile("image", "", "application/json", "{\"image\": \"C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg\"}".getBytes());


    @Test
    void addUserWithOutUri(){
        //mockMvc.perform(post("/").par)
    }

    @Test
    void statusUser() throws Exception {
        Long id = 1l;
        User user = userrepository.findById(id).get();
        boolean status = !user.isStatus();
    MvcResult a =  mockMvc.perform(put("/1").param("current",status + "")).
              andExpect(request().asyncStarted()).andReturn();
      System.out.println(a);
    }

    @Test
    void allUserStatus() throws Exception {
       List<User>userList = userrepository.findAll();
        for (User i: userList) {
            boolean status =!i.isStatus();
            mockMvc.perform(put("/" +i.getId()).param("current",status + "")).
                    andExpect(request().asyncStarted()).andReturn();
        }



    }








    @Test
    void viewUser() throws Exception {
        for (int i =1; i < 5;  i++){
        mockMvc.perform(get("/" +i)).andExpect(status().isOk()).andDo(print()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }

    @Test
    void statistics() throws Exception {
        mockMvc.perform(get("/all?status=1")).andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get("/all?id=1&status=1")).andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get("/all?id=1")).andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get("/all")).andExpect(status().is5xxServerError()).andDo(print());
    }

    @Test
    void getAllOnline() throws Exception {
        mockMvc.perform(get("/all").param("status","true")).andExpect(status().isOk()).andDo(print());
    }
    @Test
    void allAfterId() throws Exception {// берёт всех после 4 id
        mockMvc.perform(get("/all").param("id","4")).andExpect(status().isOk()).andDo(print());
    }

}