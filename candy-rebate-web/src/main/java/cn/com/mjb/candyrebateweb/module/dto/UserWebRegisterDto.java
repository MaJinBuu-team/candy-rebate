package cn.com.mjb.candyrebateweb.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册
 *
 * @author buu
 */
@Data
@ApiModel(value = "用户注册Dto")
public class UserWebRegisterDto implements Serializable {

    private static final long serialVersionUID = 3358185530948322547L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobileNo;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 验证码类型
     */
    @ApiModelProperty(value = "验证码类型")
    private String registerCodeType;

    /**
     * 注册验证码
     */
    @ApiModelProperty(value = "验证码")
    private String registerCode;

    /**
     * 注册渠道
     */
    @ApiModelProperty(value = "注册渠道")
    private String registerResource;
}
