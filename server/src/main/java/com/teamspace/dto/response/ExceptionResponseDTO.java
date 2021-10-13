package com.teamspace.dto.response;

import lombok.Getter;

@Getter
public class ExceptionResponseDTO {

    private String status;
    private String message;

    public ExceptionResponseDTO(String message) {
        this.status = "error";
        this.message = message;
    }
}
