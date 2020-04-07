package cn.com.buu.dailyfresh.module.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UacLoginVo {

    @ApiModelProperty(name = "mobileNo" , value = "手机号")
    private String mobileNo;

    @ApiModelProperty(name = "loginPwd" , value = "登录密码")
    private String loginPwd;

    @ApiModelProperty(name = "loginName" , value = "登录名")
    private String loginName;

    @ApiModelProperty(name = "email" , value = "邮件地址")
    private String email;

}
