package cn.com.mjb.candyrebateweb.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Check valid dto.
 *
 * @author buu
 */
@Data
@ApiModel
public class CheckValidDto implements Serializable {

	private static final long serialVersionUID = -6680267209271915352L;

	/**
	 * 校验的参数值
	 */
	@ApiModelProperty(value = "校验参数值")
	private String validValue;

	/**
	 * 参数类型(列)
	 */
	@ApiModelProperty(value = "参数类型")
	private String type;
}
