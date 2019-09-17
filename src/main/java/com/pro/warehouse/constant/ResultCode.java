package com.pro.warehouse.constant;

/**
 * 封装向前台返回的数据
 */
public class ResultCode {
    private String resultCode;
    private String resultMessage;

    public ResultCode() {
    }

    public ResultCode(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
