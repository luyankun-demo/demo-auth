package com.demo.auth.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ResponseMessage<T> {

    @AllArgsConstructor
    @Getter
    enum ResponseCode {
        SUCCESS(200, "处理成功"),
        BASE_FAIL(500, "处理失败");

        private Integer code;
        private String message;
    }

    private Integer returnCode;
    private String returnMessage;
    private T responseBody;
    private List<T> responseList;

    public ResponseMessage(Integer returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public ResponseMessage(Integer returnCode, String returnMessage, T responseBody) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.responseBody = responseBody;
    }

    public ResponseMessage(Integer returnCode, String returnMessage, List<T> responseList) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.responseList = responseList;
    }


    public static ResponseMessage success(){
        return success(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseMessage success(Integer returnCode, String returnMessage){
        return success(returnCode, returnMessage, null);
    }

    public static <T> ResponseMessage<T> success(T responseBody) {
        return success(ResponseCode.SUCCESS.getMessage(), responseBody);
    }

    public static <T> ResponseMessage<T> success(String returnMessage, T responseBody) {
        return success(ResponseCode.SUCCESS.getCode(), returnMessage, responseBody);
    }

    public static <T> ResponseMessage<T> success(Integer returnCode, String returnMessage, T responseBody) {
        return new ResponseMessage<>(returnCode, returnMessage, responseBody);
    }

    public static <T> ResponseMessage<T> success(List<T> responseBody) {
        return success(ResponseCode.SUCCESS.getMessage(), responseBody);
    }

    public static <T> ResponseMessage<T> success(String returnMessage, List<T> responseBody) {
        return success(ResponseCode.SUCCESS.getCode(), returnMessage, responseBody);
    }

    public static <T> ResponseMessage<T> success(Integer returnCode, String returnMessage, List<T> responseBody) {
        return new ResponseMessage<>(returnCode, returnMessage, responseBody);
    }

    public static  ResponseMessage fail() {
        return fail(ResponseCode.BASE_FAIL.getCode(), ResponseCode.BASE_FAIL.getMessage());
    }

    public static  ResponseMessage fail(Integer returnCode, String returnMessage) {
        return new ResponseMessage(returnCode, returnMessage);
    }


}
