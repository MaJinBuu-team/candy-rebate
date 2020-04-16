package cn.com.mjb.candyrebateweb.controller.uac;

import cn.com.mjb.candyrebatecore.module.dto.MessageBox;
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
@Api(value = "用户操作相关接口")
public class UacWebController {

    @Resource
    private UacWebService uacWebService;

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
