package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.YourEntity;

@RestController
@RequestMapping("/Your-Entity")

public class YourEntityController 
{
	@Autowired
	private YourEntityController repository;
	
	@GetMapping
	public List<YourEntity>getAllEntities()
	{
		return ((Object) repository).findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<YourEntity> getEntityById(@PathVariable Long id)
	{
		Optional<YourEntity>entity = repository.findById(id);
		return entity.map(ResponseEntity::ok).orElseGet(()-> 
		ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public YourEntity createEntity(@RequestBody YourEntity entity)
	{
		return repository.save(entity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<YourEntity>updateEntity
	(@PathVariable Long id, @RequestBody YourEntity updateEntity)
	{
		if(!repository.existsById(id))
		{
			return ResponseEntity.notFound().build();
		}
		updateEntity.setId(id);
		repository.save(updateEntity);
		return ResponseEntity.ok(updateEntity);
		
	}
	
	@DeleteMapping("/id)")

	public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {

	if (!repository.existsById(id)) {

	return ResponseEntity.notFound().build();

	repository.deleteById(id);

	return ResponseEntity.noContent().build();
	}
	
	

}
