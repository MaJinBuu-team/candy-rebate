package cn.com.mjb.candyrebateweb.service.uac.impl;

import cn.com.mjb.candyrebateweb.dao.UacWebUserMapper;
import cn.com.mjb.candyrebateweb.exception.UacWebBusinessException;
import cn.com.mjb.candyrebateweb.module.domain.UacWebUser;
import cn.com.mjb.candyrebateweb.module.dto.UacWebLoginDto;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;
import cn.com.mjb.candyrebateweb.module.enums.ErrorCodeEnum;
import cn.com.mjb.candyrebateweb.module.enums.UacWebUserSourceEnum;
import cn.com.mjb.candyrebateweb.module.enums.UacWebUserStatusEnum;
import cn.com.mjb.candyrebateweb.service.common.RedisService;
import cn.com.mjb.candyrebateweb.service.uac.UacWebService;
import cn.com.mjb.candyrebateweb.utils.Md5Util;
import cn.com.mjb.candyrebateweb.utils.RedisKeyUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UacWebServiceImpl extends BaseServiceImpl implements UacWebService {

    @Resource
    private UacWebUserMapper uacUserMapper;

    @Resource
    private RedisService redisService;

    @Override
    public void register(UserWebRegisterDto userWebRegisterDto) {
        // 校验注册信息
        validateRegisterInfo(userWebRegisterDto);

        String mobileNo = userWebRegisterDto.getMobileNo();
        String email = userWebRegisterDto.getEmail();
        Date row = new Date();
        String salt = String.valueOf(getUUID());

        // 封装注册信息
        UacWebUser uacUser = new UacWebUser();
        uacUser.setLoginName(userWebRegisterDto.getLoginName());
        uacUser.setSalt(salt);
        uacUser.setLoginPwd(Md5Util.encrypt(userWebRegisterDto.getLoginPwd()));
        uacUser.setMobileNo(mobileNo);
        uacUser.setStatus(UacWebUserStatusEnum.DISABLE.getKey());
        uacUser.setUserSource(UacWebUserSourceEnum.REGISTER.getKey());
        uacUser.setCreatedTime(row);
        uacUser.setUpdateTime(row);
        uacUser.setEmail(email);
        uacUser.setCreator(userWebRegisterDto.getLoginName());
        uacUser.setUserName(userWebRegisterDto.getLoginName());
        uacUser.setLastOperator(userWebRegisterDto.getLoginName());

        // 发送激活邮件
        String activeToken = super.getUUID();
        redisService.setKey(RedisKeyUtil.getActiveUserKey(activeToken), email, 1, TimeUnit.DAYS);

        int insert = uacUserMapper.insert(uacUser);
        if (0 == insert) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011012);
        }

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", userWebRegisterDto.getLoginName());
        param.put("email", userWebRegisterDto.getEmail());
    }

    @Override
    public UacWebUser login(UacWebLoginDto uacWebLoginDto) {

        // 判断用户是以为什么方式登录进来的
        Preconditions.checkArgument(!StringUtils.isEmpty(uacWebLoginDto.getLoginPwd()), "密码不能为空");
        UacWebUser uacWebUser = uacUserMapper.selectById(uacWebLoginDto.getEmail());
        return null;
    }

    private void validateRegisterInfo(UserWebRegisterDto userWebRegisterDto) {
        // 用户手机号
        String mobileNo = userWebRegisterDto.getMobileNo();

        // 一系列的非空校验
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getEmail()), ErrorCodeEnum.UAC10011018.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getConfirmPwd()), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getRegisterSource()), "验证类型错误");
        Preconditions.checkArgument(userWebRegisterDto.getLoginPwd().equals(userWebRegisterDto.getConfirmPwd()), "两次密码不一致");

        UacWebUser uacWebUser = new UacWebUser();
        uacWebUser.setLoginName(userWebRegisterDto.getLoginName());

        // 通过loginName看看数据库中有没有已经注册
        int count = uacUserMapper.findByLoginName(uacWebUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011012);
        }

        uacWebUser = new UacWebUser();
        uacWebUser.setMobileNo(userWebRegisterDto.getMobileNo());
        // 通过loginName看看数据库中有没有已经注册
        count = uacUserMapper.findByMobile(uacWebUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011013);
        }

        uacWebUser = new UacWebUser();
        uacWebUser.setEmail(userWebRegisterDto.getEmail());
        count = uacUserMapper.findByEmail(uacWebUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011019);
        }
    }

}
