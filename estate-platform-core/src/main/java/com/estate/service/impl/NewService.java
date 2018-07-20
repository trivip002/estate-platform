package com.estate.service.impl;

import com.estate.dto.NewDTO;
import com.estate.entity.NewEntity;
import com.estate.repository.NewRepository;
import com.estate.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Override
	public List<NewDTO> getAll() {
		List<NewDTO> newDTOS = new ArrayList<>();
		List<NewEntity> newEntities = newRepository.findAll();
		for (NewEntity item: newEntities) {
			NewDTO newDTO = new NewDTO();
			newDTO.setTitle(item.getTitle());
			newDTO.setContent(item.getContent());
			newDTOS.add(newDTO);
		}
		return newDTOS;
	}
}
