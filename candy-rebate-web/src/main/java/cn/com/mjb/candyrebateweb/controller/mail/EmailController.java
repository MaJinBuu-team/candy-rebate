package cn.com.mjb.candyrebateweb.controller.mail;

import cn.com.mjb.candyrebatecore.module.dto.MessageBox;
import cn.com.mjb.candyrebatecore.service.EmailService;
import cn.com.mjb.candyrebateweb.controller.BaseController;
import cn.com.mjb.candyrebateweb.module.dto.EmailSendDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/candy")
@Api(value = "邮件短信相关接口")
public class EmailController extends BaseController {

    @Resource
    private EmailService emailService;

    @PostMapping("/send")
    public MessageBox send(@RequestBody EmailSendDto emailSendDto) {
        emailService.sendTemplateMail(emailSendDto.getEmail());
        return MessageBox.ok();
    }

}
