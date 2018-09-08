package com.estate.service.impl;

import com.estate.converter.DistrictConverter;
import com.estate.entity.DistrictEntity;
import com.estate.repository.DistrictRepository;
import com.estate.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictConverter districtConverter;

    @Override
    public Map<String, String> getDistricts() {
        Map<String, String> districts = new HashMap<>();
        List<DistrictEntity> districtEntities = districtRepository.findAll();
        districtEntities.forEach(item -> {
            districts.put(item.getName(), item.getName());
        });
        return districts;
    }
}
