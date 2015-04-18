package com.circular.browser.auth.service.message;

/**
 * Created by senlin.xsl on 2015/4/17.
 */
public class MessageItem {
    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 具体内容
     */
    private String content;

    /**
     * 重试次数
     */
    private int retryTimes;

    /**
     * 错误消息
     */
    private String errorMsg;

    @Override
    public String toString() {
        return "phoneNumber: " + this.phoneNumber + " content: " + this.content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
