package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    String errorMessage;
    Integer code;
    Object data;

    public ResponseDTO setErrorMessage(String msg) {
        this.errorMessage = msg;
        return this;
    }

    public ResponseDTO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseDTO setData(Object data) {
        this.data = data;
        return this;
    }
}
