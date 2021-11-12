package com.lino4000.petFinder.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@Entity
@Table(name="users")
public class User {
	
	@Id @GeneratedValue
	private long id;
	@Column(unique=true)
	private String username;
	@NonNull
	private String password;
	@Column(unique=true) @NonNull
	private String email;
	private String info;
	@OneToMany(mappedBy="user")
	private Set<Device> devices;
}