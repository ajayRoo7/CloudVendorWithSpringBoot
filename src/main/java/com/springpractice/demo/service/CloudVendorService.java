package com.springpractice.demo.service;

import com.springpractice.demo.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public String createCloudVendor(CloudVendor cloudVendor);
    public String updateCloudVendor(CloudVendor cloudVendor);
    public String deleteCloudVendor(String cloudVendorId);
    public CloudVendor getCloudVendor(String cloudVendorId);
    public List<CloudVendor> getCloudVendorByName(String cloudVendorName);
    public List<CloudVendor> getAllCloudVendors();
}
