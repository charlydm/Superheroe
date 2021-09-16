package com.w2m.superheroe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.superheroe.common.TrackExecutionTime;
import com.w2m.superheroe.entity.Superheroe;
import com.w2m.superheroe.repository.ISuperheroeRepository;

@Service
public class SuperheroeService {
	
	@Autowired
	private ISuperheroeRepository superheroeRepository;
	
	@TrackExecutionTime
	public Iterable<Superheroe> findLikeName(String name) {
		String likeName = "%" + name.strip() + "%";
		return superheroeRepository.findByLikeName(likeName);
	}
	
	@TrackExecutionTime
	public Optional<Superheroe> findIdSuperheroe(Long id) {
		return superheroeRepository.findById(id);
	}
	
	@TrackExecutionTime
	public Iterable<Superheroe> findAllSuperheroe() {
		return superheroeRepository.findAll();
	}
	
	@TrackExecutionTime
	public Superheroe create(Superheroe superheroe) {
		return superheroeRepository.save(superheroe);
	}
	
	@TrackExecutionTime
	public Superheroe update(Superheroe superheroe) {
		return superheroeRepository.save(superheroe);
	}
	
	@TrackExecutionTime
	public void delete(Long id) {
		superheroeRepository.deleteById(id);
	}

}
