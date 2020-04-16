package cn.com.mjb.candyrebatecore.module.enums;

/**
 * 邮箱验证码枚举类
 *
 * @author buu
 */
public enum UacEmailTemplateEnum {

    /**
     * 重置密码-获取验证码
     */
    RESET_PWD_CODE("RESET_PWD_CODE", "重置密码-获取验证码", "/sendRestUserEmailTemplate.ftl"),
    /**
     * 重置密码-重置密码成功
     */
    RESET_PWD_SUCCESSFUL("RESET_PWD_SUCCESSFUL", "修改邮箱-邮件验证码", "/sendRestUserEmailTemplate.ftl"),
    /**
     * 注册用户-获取邮箱验证码
     */
    REGISTER_USER_CODE("REGISTER_USER_CODE", "注册用户-获取邮箱验证码", "/userRegisterTemplate.ftl"),
    /**
     * 注册成功-发送注册成功邮件
     */
    REGISTER_USER_SUCCESSFUL("REGISTER_USER_SUCCESSFUL", "注册成功-发送注册成功邮件", "/userRegisterSuccessful.ftl"),
    ;

    /**
     * The Key.
     */
    String key;
    /**
     * The Subject.
     */
    String subject;
    /**
     * The Location.
     */
    String location;

    UacEmailTemplateEnum(String key, String subject, String location) {
        this.key = key;
        this.subject = subject;
        this.location = location;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

}
