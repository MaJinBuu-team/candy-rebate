package cn.com.mjb.candyrebateweb.dao;

import cn.com.mjb.candyrebateweb.module.domain.UacWebUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作员表数据访问层
 *
 * @author buu
 */
@Mapper
public interface UacUserMapper extends BaseMapper<UacWebUser> {

    int findByLoginName(UacWebUser uacWebUser);

    int findByMobile(UacWebUser uacWebUser);

    int findByEmail(UacWebUser uacWebUser);

}
