package cn.com.mjb.candyrebatecore.service;

import cn.com.mjb.candyrebatecore.module.enums.UacEmailTemplateEnum;

import java.util.Set;

public interface EmailService {

    /**
     * 发送简单邮件，没有模板
     *
     * @param subject the subject
     * @param text    the text
     * @param to      the to
     * @return the int
     */
    int sendSimpleMail(String subject, String text, Set<String> to);

    /**
     * 发送邮箱验证码（6位随机数字）
     *
     * @param email 邮箱
     */
    void sendTemplateMail(String code, String email, UacEmailTemplateEnum uacEmailTemplateEnum);

}
