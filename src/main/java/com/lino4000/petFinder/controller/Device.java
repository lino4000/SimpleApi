package com.lino4000.petFinder.controller;

import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lino4000.petFinder.dto.DeviceReceiveLocation;


@RestController
public class Device {
	
	@PostMapping("/newlocation")
	@ResponseBody
    public String newLocation(@RequestBody @Valid final DeviceReceiveLocation locationReceived, @Param("nouce") long nounce) {
		return "Funcionou!";
	}


}
