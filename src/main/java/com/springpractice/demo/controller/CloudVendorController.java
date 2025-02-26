package com.springpractice.demo.controller;

import com.springpractice.demo.model.CloudVendor;
import com.springpractice.demo.response.ResponseHandler;
import com.springpractice.demo.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getVendor(@PathVariable("vendorId")String vendorId)
    {
        return ResponseHandler.responseBuilder("Requested vendor details Are Given", HttpStatus.OK,cloudVendorService.getCloudVendor(vendorId)) ;
    }
    @GetMapping()
    public List<CloudVendor> getAllVendors()
    {
        return cloudVendorService.getAllCloudVendors();
    }
    @PostMapping
    public String createVendor(@RequestBody CloudVendor cloudVendor)
    {

        return cloudVendorService.createCloudVendor(cloudVendor);
    }
    @PutMapping
    public String updateVendor(@RequestBody CloudVendor cloudVendor)
    {
        return cloudVendorService.createCloudVendor(cloudVendor);
    }
    @DeleteMapping("{vendorId}")
    public String deleteVendor(@PathVariable("vendorId") String vendorId)
    {
        return  cloudVendorService.deleteCloudVendor(vendorId);
    }
}
