package com.lino4000.petFinder.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SensorStatus {
	
	@Id @GeneratedValue
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
	@ManyToOne @NonNull
	private Sensor sensor;
	@NonNull
	private String value;
}
