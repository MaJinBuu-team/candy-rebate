package cn.com.mjb.candyrebateadmin.module.enums;

public enum UacErrorEnum {
    UAC_NULL_PARAM(6001, "缺少请求参数"),
    ;

    private int code;
    private String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    UacErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static UacErrorEnum getEnum(int code) {
        for (UacErrorEnum ele : UacErrorEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }

}
