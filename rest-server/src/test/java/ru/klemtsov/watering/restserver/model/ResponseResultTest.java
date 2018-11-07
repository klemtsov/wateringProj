package ru.klemtsov.watering.restserver.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.*;

class ResponseResultTest {

    @Test
    void getSuccessfulResult() {
        ResponseResult<String> result = ResponseResult.getSuccessfulResult("123");
        assertEquals(SUCCESS, result.getStatus());
        ;
        assertEquals("123", result.getResult());
    }

    @Test
    void getErrorResult() {
        List<String> errors = new ArrayList<>();
        errors.add("error");
        ResponseResult<String> result = ResponseResult.getErrorResult(errors);
        assertEquals(ERROR, result.getStatus());
        ;
        assertEquals("error", result.getErrors().get(0));
        assertNull(result.getResult());
    }

    @Test
    void getErrorResult2() {
        ResponseResult<String> result = ResponseResult.getErrorResult("error");
        assertEquals(ERROR, result.getStatus());
        ;
        assertEquals("error", result.getErrors().get(0));
        assertNull(result.getResult());
    }

    @Test
    void gerWarningResult() {
        List<String> errors = new ArrayList<>();
        errors.add("error");
        ResponseResult<String> result = ResponseResult.getWarningResult("123", errors);
        assertEquals(WARNING, result.getStatus());
        ;
        assertEquals("error", result.getErrors().get(0));
        assertEquals("123", result.getResult());
    }


}