package cn.com.mjb.candyrebateweb.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户登录Dto")
public class UacWebLoginDto implements Serializable {

    private static final long serialVersionUID = 2914741209067451857L;

    @ApiModelProperty(name = "mobileNo", value = "手机号")
    private String mobileNo;

    @ApiModelProperty(name = "loginPwd", value = "登录密码")
    private String loginPwd;

    @ApiModelProperty(name = "loginName", value = "登录名")
    private String loginName;

    @ApiModelProperty(name = "email", value = "邮件地址")
    private String email;

}
