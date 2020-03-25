package cn.com.mjb.candyrebateadmin.controller.uac;

import cn.com.buu.dailyfresh.module.vo.UacLoginVo;
import cn.com.mjb.candyrebateadmin.module.vo.MessageBox;
import cn.com.mjb.candyrebateadmin.service.uac.UacService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制
 *
 * @author: buu
 */
@RestController
@RequestMapping("/fresh/uac")
@Slf4j
public class UacController {

    @Resource
    private UacService uacService;

    @PostMapping("/login")
    @ApiOperation(httpMethod = "POST", value = "用户登录")
    public MessageBox login(@RequestBody UacLoginVo uacLoginVo) {
        return MessageBox.ok(uacService.login(uacLoginVo));
    }

}
