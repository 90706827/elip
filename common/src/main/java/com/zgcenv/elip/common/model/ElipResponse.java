package com.zgcenv.elip.common.model;

import java.util.HashMap;

/**
 * @ClassName ElipResponse
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 21:51
 * @Version 1.0
 **/
public class ElipResponse extends HashMap<String, Object> {
    public ElipResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public ElipResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public ElipResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
