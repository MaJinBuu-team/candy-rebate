package cn.com.mjb.candyrebateweb.controller.uac;

import cn.com.mjb.candyrebateweb.module.dto.MessageBox;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;
import cn.com.mjb.candyrebateweb.service.uac.UacWebService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/candy/web")
@Api(value = "Web-AuthRestController")
public class UacWebController {

    @Resource
    private UacWebService uacWebService;

    /**
     * 用户注册
     *
     * @return messageBox
     */
    @PostMapping(value = "/register")
    public MessageBox register(@RequestBody UserWebRegisterDto userWebRegisterDto) {

        uacWebService.register(userWebRegisterDto);

        return MessageBox.ok();
    }

    @PostMapping("/login")
    public MessageBox login() {
        return MessageBox.ok();
    }

}
