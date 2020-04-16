package cn.com.mjb.candyrebateweb.controller.uac;

import cn.com.mjb.candyrebatecore.module.dto.MessageBox;
import cn.com.mjb.candyrebatecore.module.enums.UacEmailTemplateEnum;
import cn.com.mjb.candyrebateweb.module.dto.EmailSendDto;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;
import cn.com.mjb.candyrebateweb.service.uac.UacWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/candy")
@Api(tags = "用户操作相关接口")
public class UacWebController {

    @Resource
    private UacWebService uacWebService;

    @PostMapping("/sendRegisterEmailCode")
    @ApiOperation(httpMethod = "POST", value = "发送注册验证码邮件")
    public MessageBox sendRegisterEmailCode(@RequestBody EmailSendDto emailSendDto) {

        return MessageBox.ok();
    }

    @PostMapping("/sendRestEmailCode")
    @ApiOperation(httpMethod = "POST", value = "发送重置密码邮件")
    public MessageBox sendRestEmailCode(@RequestBody EmailSendDto emailSendDto) {
        return MessageBox.ok();
    }

    /**
     * 用户注册
     *
     * @return messageBox
     */
    @PostMapping(value = "/register")
    @ApiOperation(httpMethod = "POST", value = "用户注册")
    public MessageBox register(@RequestBody UserWebRegisterDto userWebRegisterDto) {

        uacWebService.register(userWebRegisterDto);

        return MessageBox.ok();
    }

    @PostMapping("/login")
    @ApiOperation(httpMethod = "POST", value = "用户登录")
    public MessageBox login() {
        return MessageBox.ok();
    }

}
