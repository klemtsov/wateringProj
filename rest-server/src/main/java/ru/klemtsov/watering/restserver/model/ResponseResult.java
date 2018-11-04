package ru.klemtsov.watering.restserver.model;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T result;

    private List<String> errors;

    private ResponseStatus status;

    private ResponseResult(T result, List<String> errors, ResponseStatus status) {
        this.result = result;
        this.errors = errors;
        this.status = status;
    }

    public static <T> ResponseResult<T> getSuccessfulResult(T result) {
        return new ResponseResult<>(result, null, ResponseStatus.SUCCESS);
    }

    public static <T> ResponseResult<T> getErrorResult(List<String> errors) {
        return new ResponseResult<>(null, errors, ResponseStatus.ERROR);
    }

    public static <T> ResponseResult<T> getWarningResult(T result, List<String> errors) {
        return new ResponseResult<>(result, errors, ResponseStatus.WARNING);
    }

    public static <T> ResponseResult<T> getErrorResult(@NonNull String error){
        List<String> errors = new ArrayList<>();
        errors.add(error);
        return getErrorResult(errors);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public List<String> getErrors() {
        return errors;
    }

}
