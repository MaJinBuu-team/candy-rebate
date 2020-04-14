package cn.com.mjb.candyrebatecore.service.impl;

import cn.com.mjb.candyrebatecore.exception.CoreBusinessException;
import cn.com.mjb.candyrebatecore.module.dto.MailEntity;
import cn.com.mjb.candyrebatecore.module.enums.ErrorCodeEnum;
import cn.com.mjb.candyrebatecore.module.enums.UacEmailTemplateEnum;
import cn.com.mjb.candyrebatecore.service.EmailService;
import cn.com.mjb.candyrebatecore.service.FreeMarkerService;
import cn.com.mjb.candyrebatecore.utils.DateUtil;
import cn.com.mjb.candyrebatecore.utils.PubUtils;
import cn.com.mjb.candyrebatecore.utils.PublicUtil;
import cn.com.mjb.candyrebatecore.utils.RandomUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Resource
    private TaskExecutor taskExecutor;

    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Resource
    private FreeMarkerService freeMarkerService;

    /**
     * 先写死
     */
    private static String from = "FatMaJin_Buu@126.com";

    @Override
    public int sendSimpleMail(String subject, String text, Set<String> to) {
        log.info("sendSimpleMail - 发送简单邮件. subject={}, text={}, to={}", subject, text, to);
        int result = 1;
        try {
            SimpleMailMessage message = MailEntity.createSimpleMailMessage(subject, text, to);

            message.setFrom(from);
            taskExecutor.execute(() -> javaMailSender.send(message));
        } catch (Exception e) {
            log.info("sendSimpleMail [FAIL] ex={}", e.getMessage(), e);
            result = 0;
        }
        return result;
    }

    @Override
    public int sendTemplateMail(String email) {
        log.info("sendTemplateMail - 发送模板邮件. email={}", email);
        int result = 1;
        try {
            // 设置邮箱的基本格式(配合邮件模板试用的)
            Map<String, Object> mailContent = new HashMap<>(3);
            mailContent.put("loginName", email);
            mailContent.put("registerCode", RandomUtil.createNumberCode(6));
            mailContent.put("dateTime", DateUtil.getStringDate());

            Set<String> to = new HashSet<>();
            to.add(email);

            String text = freeMarkerService.getTemplate(mailContent, UacEmailTemplateEnum.REGISTER_USER.getLocation());
            MimeMessage mimeMessage = getMimeMessage(UacEmailTemplateEnum.REGISTER_USER.getSubject(), text, to);
            taskExecutor.execute(() -> javaMailSender.send(mimeMessage));
        } catch (Exception e) {
            log.info("sendTemplateMail [FAIL] ex={}", e.getMessage(), e);
            throw new CoreBusinessException(ErrorCodeEnum.OPC10040006);
        }
        return result;
    }

    private MimeMessage getMimeMessage(String subject, String text, Set<String> to) {

        // 校验发送邮件基本信息是否为空
        Preconditions.checkArgument(!PubUtils.isNull(subject, text, to), "参数异常, 邮件信息不完整");

        String[] toArray = setToArray(to);
        Preconditions.checkArgument(PublicUtil.isNotEmpty(toArray), "请输入收件人邮箱");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(toArray);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            log.error("生成邮件消息体, 出现异常={}", e.getMessage(), e);
            throw new CoreBusinessException(ErrorCodeEnum.OPC10040005);
        }
        return mimeMessage;
    }

    private String[] setToArray(Set<String> to) {
        Preconditions.checkArgument(PublicUtil.isNotEmpty(to), "请输入收件人邮箱");

        return getStrings(to);
    }

    public static String[] getStrings(Set<String> to) {
        Set<String> toSet = Sets.newHashSet();
        for (String toStr : to) {
            toStr = toStr.trim();
            if (PubUtils.isEmail(toStr)) {
                toSet.add(toStr);
            }
        }
        if (PublicUtil.isEmpty(toSet)) {
            return null;
        }
        return toSet.toArray(new String[0]);
    }
}
