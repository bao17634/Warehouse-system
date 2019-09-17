package com.pro.warehouse.util;

/**
 * 统一响应信息
 */
public class ResultData {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    public ResultData() {}

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultData success() {
        ResultData result = new ResultData();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static ResultData success(Object data) {
        ResultData result = new ResultData();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultData failure(ResultCode resultCode,String Message) {
        ResultData result = new ResultData();
        result.code = resultCode.code();
        result.setMsg(Message);
        return result;
    }

    public static ResultData failure(ResultCode resultCode) {
        ResultData result = new ResultData();
        result.setResultCode(resultCode);
        return result;
    }

    public static ResultData failure(ResultCode resultCode, Object data) {
        ResultData result = new ResultData();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
