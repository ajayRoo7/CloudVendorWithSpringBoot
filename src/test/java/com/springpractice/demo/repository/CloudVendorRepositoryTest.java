package com.springpractice.demo.repository;

import com.springpractice.demo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest{
    @Autowired
    CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor=new CloudVendor("1","Amazon","USA","8797987");
        cloudVendorRepository.save(cloudVendor);

    }

    @AfterEach
    void tearDown() {
        cloudVendor=null;
        cloudVendorRepository.deleteAll();

    }
    @Test
    void testFindByVendorName_Found()
    {
      List<CloudVendor> cloudVendors= cloudVendorRepository.findByVendorName("Amazon");
      assertThat(cloudVendors.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
      assertThat(cloudVendors.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }
    @Test
    void testFindByVendorName_NotFound()
    {
        List<CloudVendor> cloudVendors= cloudVendorRepository.findByVendorName("GCP");
        assertThat(cloudVendors.isEmpty()).isTrue();
    }
}
