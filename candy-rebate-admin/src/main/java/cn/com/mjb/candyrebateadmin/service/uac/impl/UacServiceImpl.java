package cn.com.mjb.candyrebateadmin.service.uac.impl;

import cn.com.mjb.candyrebateadmin.module.vo.UacLoginVo;
import cn.com.mjb.candyrebateadmin.utils.PubUtils;
import cn.com.mjb.candyrebateadmin.dao.UacWebUserDao;
import cn.com.mjb.candyrebateadmin.exception.UacBusinessException;
import cn.com.mjb.candyrebateadmin.module.domain.PcUacUser;
import cn.com.mjb.candyrebateadmin.module.enums.UacErrorEnum;
import cn.com.mjb.candyrebateadmin.service.uac.UacService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
@Slf4j
public class UacServiceImpl implements UacService {

    @Resource
    private UacWebUserDao pcUacUserDao;

    @Override
    public PcUacUser login(UacLoginVo uacLoginVo) {

        // 判断用户是以为什么方式登录进来的
        Preconditions.checkArgument(!StringUtils.isEmpty(uacLoginVo.getLoginPwd()), "密码不能为空");
        String str = NotNullThenReturn(uacLoginVo.getEmail(), uacLoginVo.getLoginName(), uacLoginVo.getMobileNo());

        return pcUacUserDao.selectById(str);
    }

    private String NotNullThenReturn(Object... args) {
        for (Object object : args) {
            if (!PubUtils.isNull(object)) {
                return String.valueOf(object);
            }
        }
        throw new UacBusinessException(UacErrorEnum.UAC_NULL_PARAM);
    }
}
