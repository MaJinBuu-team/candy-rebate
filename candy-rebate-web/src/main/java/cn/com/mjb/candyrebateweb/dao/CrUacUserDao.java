/**
 * @filename:CrUacUserDao 2020年04月15日
 * @project candy-rebate-web  v1.0.0
 * Copyright(c) 2020 buu Co. Ltd.
 * All right reserved.
 */
package cn.com.mjb.candyrebateweb.dao;

import cn.com.mjb.candyrebateweb.module.domain.CrUacUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>自动生成工具：mybatis-dsc-generator</p>
 *
 * <p>说明： 用户表数据访问层</p>
 * @version: v1.0.0
 * @author: buu
 *
 */
@Mapper
public interface CrUacUserDao extends BaseMapper<CrUacUser> {
    int findByLoginName(CrUacUser uacWebUser);

    int findByMobile(CrUacUser uacWebUser);

    int findByEmail(CrUacUser uacWebUser);
}
