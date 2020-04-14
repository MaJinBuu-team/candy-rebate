package cn.com.mjb.candyrebateweb.controller.mail;

import cn.com.mjb.candyrebatecore.module.dto.MessageBox;
import cn.com.mjb.candyrebatecore.service.EmailService;
import cn.com.mjb.candyrebatecore.service.RedisService;
import cn.com.mjb.candyrebatecore.utils.DateUtil;
import cn.com.mjb.candyrebatecore.utils.RandomUtil;
import cn.com.mjb.candyrebatecore.utils.RedisKeyUtil;
import cn.com.mjb.candyrebateweb.controller.BaseController;
import cn.com.mjb.candyrebateweb.module.dto.EmailSendDto;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/candy")
@Api(value = "邮件短信相关接口")
public class EmailController extends BaseController {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisService redisService;

    @Value("${registerMailTemplate}")
    private String templateLocation;

    @PostMapping("/send")
    public MessageBox send(@RequestBody EmailSendDto emailSendDto) {
        Map<String, Object> stringObjectMap = handleParamOfEmail(emailSendDto);

        return MessageBox.ok();
    }

    // 这个方法是用来组装调用邮件service的一些参数
    private Map<String, Object> handleParamOfEmail(EmailSendDto emailSendDto) {
        // 这个参数对应了你发送模板里面对应的参数
        Map<String, Object> modelParam = new HashMap<>();
        // 名称
        modelParam.put("userName", emailSendDto.getEmail());
        // 当前系统时间
        modelParam.put("dateTime", DateUtil.getBeforeTime(12));
        // 验证码，需要随机生成一个并且存放到redis中
        String numberCode = RandomUtil.createNumberCode(6);
        String activeToken = super.getUUID();
        redisService.setKey(RedisKeyUtil.getActiveUserKey(activeToken), numberCode, 1, TimeUnit.DAYS);


        String subject = "测试sendSimpleMail邮件";
        Set<String> to = Sets.newHashSet();
        to.add("xxxx@qq.com");
        return modelParam;
    }

}
