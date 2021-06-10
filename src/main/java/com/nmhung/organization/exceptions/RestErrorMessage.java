package com.nmhung.organization.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RestErrorMessage {
    private HttpStatus status;
    private List<String> messages = new ArrayList<>();

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Vietnam")
    private LocalDateTime timestamp;

    private RestErrorMessage() {
        this.timestamp = LocalDateTime.now();
    }

    public RestErrorMessage(String message, HttpStatus status) {
        this();
        this.status = status;
        this.messages.add(message);
    }

    public RestErrorMessage(List<String> messages, HttpStatus status) {
        this();
        this.status = status;
        this.messages = messages;
    }

}
