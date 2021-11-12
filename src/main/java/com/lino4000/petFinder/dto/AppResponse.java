package com.lino4000.petFinder.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AppResponse {
	
	public String title;
	public String message;
	public String action;
}
