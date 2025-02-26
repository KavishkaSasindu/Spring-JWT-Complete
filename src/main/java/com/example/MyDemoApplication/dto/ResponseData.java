package com.example.MyDemoApplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseData {

    private String message;

    public ResponseData(String message){
        this.message = message;
    }
}
