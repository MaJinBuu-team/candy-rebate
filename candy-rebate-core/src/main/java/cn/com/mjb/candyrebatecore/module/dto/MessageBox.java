package cn.com.mjb.candyrebatecore.module.dto;

import lombok.Data;

/**
 * 定义统一返回
 *
 * @author buu
 */
@Data
public class MessageBox {

    private static final int RESULT_OK = 200;
    private static final int RESULT_FAILED = 9999;
    private static final String SUCCESSFUL = "操作成功";
    private static final String FAILED = "操作失败";

    private int code;
    private Object data;
    private String msg;

    private MessageBox(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static MessageBox ok() {
        return createBaseResult(RESULT_OK, null, SUCCESSFUL);
    }

    public static MessageBox failed() {
        return createBaseResult(RESULT_FAILED, null, FAILED);
    }

    public static MessageBox ok(int code, String msg) {
        return createBaseResult(code, null, msg);
    }

    public static MessageBox ok(int code, Object data, String msg) {
        return createBaseResult(code, data, msg);
    }

    public static MessageBox ok(Object data) {
        return createBaseResult(RESULT_OK, data, SUCCESSFUL);
    }


    private static MessageBox createBaseResult(int code, Object data, String msg) {
        return new MessageBox(code, data, msg);
    }

}
