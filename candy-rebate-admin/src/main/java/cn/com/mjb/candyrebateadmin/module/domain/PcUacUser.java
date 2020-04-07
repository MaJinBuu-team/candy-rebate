package cn.com.mjb.candyrebateadmin.module.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作员表实体类
 *
 * @author buu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PcUacUser extends Model<PcUacUser> {

    private static final long serialVersionUID = 1576826857582L;

    @TableId(value = "id")
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "version", value = "版本号")
    private Integer version;

    @ApiModelProperty(name = "loginName", value = "登录名")
    private String loginName;

    @ApiModelProperty(name = "loginPwd", value = "登录密码")
    private String loginPwd;

    @ApiModelProperty(name = "salt", value = "盐,用于shiro加密, 字段停用")
    private String salt;

    @ApiModelProperty(name = "userCode", value = "工号")
    private String userCode;

    @ApiModelProperty(name = "userName", value = "姓名")
    private String userName;

    @ApiModelProperty(name = "mobileNo", value = "手机号")
    private String mobileNo;

    @ApiModelProperty(name = "email", value = "邮件地址")
    private String email;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "userSource", value = "用户来源")
    private String userSource;

    @ApiModelProperty(name = "type", value = "操作员类型（2000伙伴，3000客户，1000运营）")
    private String type;

    @ApiModelProperty(name = "lastLoginIp", value = "最后登录IP地址")
    private String lastLoginIp;

    @ApiModelProperty(name = "lastLoginLocation", value = "最后登录位置")
    private String lastLoginLocation;

    @ApiModelProperty(name = "remark", value = "描述")
    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "lastLoginTime", value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(name = "isChangedPwd", value = "是否更改过密码")
    private Integer isChangedPwd;

    @ApiModelProperty(name = "pwdErrorCount", value = "连续输错密码次数（连续5次输错就冻结帐号）")
    private Integer pwdErrorCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "pwdErrorTime", value = "最后输错密码时间")
    private Date pwdErrorTime;

    @ApiModelProperty(name = "creator", value = "创建人")
    private String creator;

    @ApiModelProperty(name = "creatorId", value = "创建人ID")
    private Long creatorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "createdTime", value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(name = "lastOperator", value = "最近操作人")
    private String lastOperator;

    @ApiModelProperty(name = "lastOperatorId", value = "最后操作人ID")
    private Long lastOperatorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
