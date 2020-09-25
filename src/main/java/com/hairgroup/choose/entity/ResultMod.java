package com.hairgroup.choose.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created on 2020/9/22
 *
 * 返回对象
 *
 * @author Yue Wu
 */
public class ResultMod extends HashMap<String, Object> implements Serializable {
    private ResultMod() {
    }

    public static ResultMod getInstance() {
        return new ResultMod();
    }

    public ResultMod success() {
        this.put("result", "success");
        this.code(200);
        return this;
    }

    public ResultMod fail() {
        this.put("result", "fail");
        this.code(400);
        return this;
    }

    public ResultMod code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMod message(Object message) {
        this.put("message", message);
        return this;
    }

    public ResultMod add(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
