package com.iron.rms.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDTO {

	private String code;
	private String message;
}
