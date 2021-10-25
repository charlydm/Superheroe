package com.w2m.superheroe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="SUPER_HEROE")
public class Superheroe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, updatable = false, nullable=false)
	private Long id;
	
	@Column
	@NotNull
	private String name;
	
}
