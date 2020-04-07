package cn.com.mjb.candyrebateadmin.dao;

import cn.com.mjb.candyrebateadmin.module.domain.PcUacUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作员表数据访问层
 *
 * @author buu
 */
@Mapper
public interface PcUacUserDao extends BaseMapper<PcUacUser> {

    PcUacUser findUserByAccount(String userAccount);

}
