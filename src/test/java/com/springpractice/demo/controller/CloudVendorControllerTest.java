package com.springpractice.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springpractice.demo.model.CloudVendor;
import com.springpractice.demo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;

    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    List<CloudVendor> cloudVendors=new ArrayList<>();


    @BeforeEach
    void setUp() {
        cloudVendorOne=new CloudVendor("1","Amazon","USA","8797987");
        cloudVendorTwo=new CloudVendor("2","Netflix","India","8797907");
        cloudVendors.add(cloudVendorOne);
        cloudVendors.add(cloudVendorTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetVendor() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendorOne);
        this.mockMvc.perform(get("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllVendors() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendors);
        this.mockMvc.perform(get("/cloudvendor")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendor() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter=objectMapper.writer().withDefaultPrettyPrinter();
        String  cloudVendorOneJson=objectWriter.writeValueAsString(cloudVendorOne);


        when(cloudVendorService.createCloudVendor(cloudVendorOne)).thenReturn("Successfully Created");
        this.mockMvc.perform(post("/cloudvendor")
                        .contentType(MediaType.APPLICATION_JSON).
                content(cloudVendorOneJson)
                ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendor() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter=objectMapper.writer().withDefaultPrettyPrinter();
        String  cloudVendorOneJson=objectWriter.writeValueAsString(cloudVendorOne);


        when(cloudVendorService.updateCloudVendor(cloudVendorOne)).thenReturn("Successfully Created");
        this.mockMvc.perform(put("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON).
                content(cloudVendorOneJson)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteVendor() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("Successfully Deleted");
        this.mockMvc.perform(delete("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }
}