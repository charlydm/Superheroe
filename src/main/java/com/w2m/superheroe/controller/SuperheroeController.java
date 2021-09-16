package com.w2m.superheroe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.superheroe.entity.Superheroe;
import com.w2m.superheroe.exception.NotFoundException;
import com.w2m.superheroe.service.SuperheroeService;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(SuperheroeController.SUPERHEROE_URI)
public class SuperheroeController {
	
	public static final String SUPERHEROE_URI = "v1/superheroes";

	@Autowired
	private SuperheroeService superheroeService;

	@GetMapping("/{id}")
	@Cacheable(value="superheroes")
	public ResponseEntity<Superheroe> findSuperheroe(@PathVariable Long id) {
		Optional<Superheroe> superheroe = superheroeService.findIdSuperheroe(id);
		if (superheroe.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(superheroe.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping
	@Cacheable(value="superheroes")
	public ResponseEntity<Iterable<Superheroe>> findAllSuperheroe() {
		Iterable<Superheroe> superheroeList = superheroeService.findAllSuperheroe();
		return ResponseEntity.status(HttpStatus.OK).body(superheroeList);
	}
	
	@GetMapping("/search")
	@Cacheable(value="superheroes")
	public ResponseEntity<Iterable<Superheroe>> findLikeName(@RequestParam String name) {
		Iterable<Superheroe> superheroeList = superheroeService.findLikeName(name);
		return ResponseEntity.status(HttpStatus.OK).body(superheroeList);
	}

	@PreAuthorize("hasRole('MANAGER') OR hasRole('ADMIN')")
	@PostMapping
	@CacheEvict(value="superheroes", allEntries = true)
	public ResponseEntity<Superheroe> createSuperheroe(@RequestBody Superheroe superheroe) {
		Superheroe newSuperheroe = superheroeService.create(superheroe);
		return ResponseEntity.status(HttpStatus.OK).body(newSuperheroe);
	}
	
	@PreAuthorize("hasRole('MANAGER') OR hasRole('ADMIN')")
	@PutMapping("/{id}")
	@CacheEvict(value="superheroes", allEntries = true)
	public ResponseEntity<Superheroe> updateSuperheroe(@PathVariable Long id, @RequestBody Superheroe superheroe) {
		Optional<Superheroe> updateSuperheroe = superheroeService.findIdSuperheroe(id);
		if (updateSuperheroe.isPresent()) {
			updateSuperheroe.get().setName(superheroe.getName());
			return ResponseEntity.status(HttpStatus.OK).body(superheroeService.update(updateSuperheroe.get()));
		}
		throw new NotFoundException("id:" + id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@CacheEvict(value="superheroes", allEntries = true)
	public ResponseEntity<Object> deleteSuperheroe(@PathVariable Long id) {
		superheroeService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
