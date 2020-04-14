package cn.com.mjb.candyrebateweb.exception;


import cn.com.mjb.candyrebatecore.module.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UacWebBusinessException extends RuntimeException {
    private static final long serialVersionUID = -3205518496516817885L;

    /**
     * 异常码
     */
    private int code;

    public UacWebBusinessException(String message) {
        super(message);
    }

    public UacWebBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public UacWebBusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.msg());
        this.code = errorCodeEnum.code();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
