package com.springboot.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue)); //Post not found with id : 1
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;

    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
