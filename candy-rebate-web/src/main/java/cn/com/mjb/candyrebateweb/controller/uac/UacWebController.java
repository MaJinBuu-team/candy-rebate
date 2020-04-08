package cn.com.mjb.candyrebateweb.controller.uac;

import cn.com.mjb.candyrebateweb.module.dto.CheckValidDto;
import cn.com.mjb.candyrebateweb.module.dto.MessageBox;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;
import cn.com.mjb.candyrebateweb.service.uac.UacWebService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * 校验数据.
     *
     * @return MessageBox
     */
    @PostMapping(value = "/checkValid")
    @ApiOperation(httpMethod = "POST", value = "校验数据")
    public MessageBox checkValid(@RequestBody CheckValidDto checkValidDto) {
        String type = checkValidDto.getType();
        String validValue = checkValidDto.getValidValue();

        Preconditions.checkArgument(StringUtils.isNotEmpty(validValue), "参数错误");
        String message = null;
        boolean result = false;
        return MessageBox.ok();
    }

    /**
     * 用户注册
     *
     * @return messageBox
     */
    @PostMapping(value = "/register")
    public MessageBox register(@RequestBody UserWebRegisterDto userWebRegisterDto) {
        return MessageBox.ok();
    }

    @PostMapping("/login")
    public MessageBox login() {
        return MessageBox.ok();
    }

}
