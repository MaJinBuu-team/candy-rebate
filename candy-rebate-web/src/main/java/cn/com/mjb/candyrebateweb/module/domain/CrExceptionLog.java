/**
 * @filename:CrExceptionLog 2020年04月15日
 * @project candy-rebate-web  v1.0.0
 * Copyright(c) 2020 buu Co. Ltd. 
 * All right reserved. 
 */
package cn.com.mjb.candyrebateweb.module.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * <p>自动生成工具：mybatis-dsc-generator</p>
 * 
 * <p>说明： 全局异常记录表实体类</P>
 * @version: v1.0.0
 * @author: buu
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CrExceptionLog extends Model<CrExceptionLog> {

	private static final long serialVersionUID = 1586880574798L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "applicationName" , value = "系统应用名")
	private String applicationName;
    
	@ApiModelProperty(name = "exceptionSimpleName" , value = "异常类型")
	private String exceptionSimpleName;
    
	@ApiModelProperty(name = "exceptionMessage" , value = "异常信息(通过exception.getMessage()获取到的内容)")
	private String exceptionMessage;
    
	@ApiModelProperty(name = "exceptionCause" , value = "异常原因(通过exception.getCause()获取到的内容)")
	private String exceptionCause;
    
	@ApiModelProperty(name = "exceptionStack" , value = "异常堆栈信息")
	private String exceptionStack;
    
	@ApiModelProperty(name = "creator" , value = "操作者姓名")
	private String creator;
    
	@ApiModelProperty(name = "creatorId" , value = "操作者id")
	private Long creatorId;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间")
	private Date createTime;
    

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
}
