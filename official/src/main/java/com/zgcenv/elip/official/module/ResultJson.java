package com.zgcenv.elip.official.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgcenv.elip.official.utils.RespCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName JsonResult
 * @Description 相应json数据
 * @Author Mr.Jangni
 * @Date 2019/10/31 10:18
 * @Version 1.0
 **/

@ApiModel(value = "返回响应数据")
@Data
public class ResultJson implements Serializable {
    /**
     * ApiModelProperty()用于方法，字段 表示对model属性的说明或者数据操作更改
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    @ApiModelProperty(value = "返回码")
    private int code;
    @ApiModelProperty(value = "返回描述")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ResultJson() {

    }

    public ResultJson(RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }

    public ResultJson(RespCode respCode, Object data) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
        this.data = data;
    }

    public ResultJson(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<?> success() {
        return success(new HashMap<>(0));
    }

    public static ResponseEntity<?> success(Object data) {

        return ResponseEntity.ok(new ResultJson(RespCode.REQUEST_SUCCESS, data));
    }

    public static ResponseEntity<?> failed() {
        return ResponseEntity.ok(new ResultJson(RespCode.REQUEST_FAILED, new HashMap<>(0)));
    }

    public static ResponseEntity<?> failed(String message) {
        return ResponseEntity.ok(new ResultJson(500, message, new HashMap<>(0)));
    }

    public static ResponseEntity<?> failed(int code, String message) {
        return ResponseEntity.ok(new ResultJson(code, message, new HashMap<>(0)));
    }

    public static ResponseEntity<?> failed(RespCode respCode) {
        return ResponseEntity.ok(new ResultJson(respCode.getCode(), respCode.getMessage(), new HashMap<>(0)));
    }

    public static String toJson(RespCode respCode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(new ResultJson(respCode.getCode(), respCode.getMessage(), new HashMap<>(0)));
    }
}
