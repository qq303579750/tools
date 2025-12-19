package com.mingyuan.wxy.tools.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CommonException extends RuntimeException {

    /**
     * 错误信息模板
     */
    private static final String MSG_TEMPLATE = "错误码:%d, 描述:%s, 异常信息:%s";
    /**
     * 附加错误描述
     */
    private String extraMsg;
    /**
     * 错误描述
     */
    private String msg;

    /**
     * 根据错误信息模板返回错误信息
     *
     * @param extraMessage
     * @return
     */
    private static String getMessage(String msg, String extraMessage) {
        extraMessage = (null != extraMessage) ? extraMessage : "";
        try {
            return String.format(MSG_TEMPLATE, msg, extraMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public CommonException(String msg) {
        super(getMessage(msg, ""), null);
        this.msg = msg;
    }

    public CommonException(Throwable throwable, String msg) {
        super(msg, null);
        this.msg = msg;
        log.error(throwable.getMessage());
    }

}
