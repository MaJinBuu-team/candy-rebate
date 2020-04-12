package cn.com.mjb.candyrebateweb.module.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmailSendDto implements Serializable {

    private static final long serialVersionUID = 7980620364738867374L;

    @ApiModelProperty(name = "email", value = "邮件地址")
    private String email;

}
