package com.lino4000.petFinder.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="sensors")
public class Sensor {
	
	@Id @GeneratedValue
	private long id;
	@ManyToOne @NonNull
	private Device device;
	@NonNull
	private SensorType type;
	@OneToMany(mappedBy="sensor")
	private Set<SensorStatus> status;
	
	public enum SensorType {
		LATITUDE,
		LONGITUDE,
		GYROSCOPE
	}
}
