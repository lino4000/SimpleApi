package com.lino4000.petFinder.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@Entity
@Table(name="devices")
public class Device {
	
	@Id @GeneratedValue
	private long id;
	@ManyToOne @NonNull
	private User user;
	@OneToMany(mappedBy="device")
	private Set<Sensor> sensores;
}