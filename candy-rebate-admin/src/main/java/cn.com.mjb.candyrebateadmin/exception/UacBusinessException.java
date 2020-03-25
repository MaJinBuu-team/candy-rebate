package cn.com.mjb.candyrebateadmin.exception;


import cn.com.mjb.candyrebateadmin.module.enums.UacErrorEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UacBusinessException extends RuntimeException {
    private static final long serialVersionUID = -3205518496516817885L;

    /**
     * 异常码
     */
    private int code;

    public UacBusinessException(String message) {
        super(message);
    }

    public UacBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public UacBusinessException(UacErrorEnum uacErrorEnum) {
        super(uacErrorEnum.msg());
        this.code = uacErrorEnum.code();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
