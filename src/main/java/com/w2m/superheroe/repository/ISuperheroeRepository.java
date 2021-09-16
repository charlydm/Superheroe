package com.w2m.superheroe.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.w2m.superheroe.entity.Superheroe;

public interface ISuperheroeRepository extends CrudRepository<Superheroe, Long> {
	
	@Query("SELECT sp FROM Superheroe sp WHERE sp.name LIKE :name")
	public Iterable<Superheroe> findByLikeName(String name);
	
}
