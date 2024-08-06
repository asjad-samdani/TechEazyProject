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
    String message;
    Object data;

    public ResponseDTO setMessage(String msg) {
        this.message = msg;
        return this;
    }

    public ResponseDTO setData(Object data) {
        this.data = data;
        return this;
    }
}
