package cn.com.mjb.candyrebateweb.service.uac.impl;

import cn.com.mjb.candyrebatecore.module.enums.ErrorCodeEnum;
import cn.com.mjb.candyrebatecore.module.enums.UacEmailTemplateEnum;
import cn.com.mjb.candyrebatecore.service.EmailService;
import cn.com.mjb.candyrebatecore.service.RedisService;
import cn.com.mjb.candyrebatecore.utils.Md5Util;
import cn.com.mjb.candyrebatecore.utils.RandomUtil;
import cn.com.mjb.candyrebateweb.dao.CrUacUserDao;
import cn.com.mjb.candyrebateweb.exception.UacWebBusinessException;
import cn.com.mjb.candyrebateweb.module.domain.CrUacUser;
import cn.com.mjb.candyrebateweb.module.dto.EmailSendDto;
import cn.com.mjb.candyrebateweb.module.dto.UacWebLoginDto;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;
import cn.com.mjb.candyrebateweb.module.enums.UacWebUserSourceEnum;
import cn.com.mjb.candyrebateweb.module.enums.UacWebUserStatusEnum;
import cn.com.mjb.candyrebateweb.service.uac.UacWebService;
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
    private CrUacUserDao crUacUserDao;

    @Resource
    private RedisService redisService;

    @Resource
    private EmailService emailService;

    @Override
    public void sendRegisterEmailCode(EmailSendDto emailSendDto) {
        // 先生成一个随机数
        String numberCode = RandomUtil.createNumberCode(6);
        // 发送
        emailService.sendTemplateMail(numberCode, emailSendDto.getEmail(), UacEmailTemplateEnum.REGISTER_USER_CODE);
        // 将随机数进行存储
        redisService.setKey(emailSendDto.getEmail(), numberCode, 10, TimeUnit.MINUTES);

    }

    @Override
    public void register(UserWebRegisterDto userWebRegisterDto) {
        // 校验注册信息
        validateRegisterInfo(userWebRegisterDto);

        String mobileNo = userWebRegisterDto.getMobileNo();
        String email = userWebRegisterDto.getEmail();
        Date row = new Date();
        String salt = String.valueOf(getUUID());

        // 封装注册信息
        CrUacUser crUacUser = new CrUacUser();
        crUacUser.setLoginName(userWebRegisterDto.getLoginName());
        crUacUser.setSalt(salt);
        crUacUser.setLoginPwd(Md5Util.encrypt(userWebRegisterDto.getLoginPwd()));
        crUacUser.setMobileNo(mobileNo);
        crUacUser.setStatus(UacWebUserStatusEnum.DISABLE.getKey());
        crUacUser.setUserSource(UacWebUserSourceEnum.REGISTER.getKey());
        crUacUser.setCreatedTime(row);
        crUacUser.setUpdateTime(row);
        crUacUser.setEmail(email);
        crUacUser.setCreator(userWebRegisterDto.getLoginName());
        crUacUser.setUserName(userWebRegisterDto.getLoginName());
        crUacUser.setLastOperator(userWebRegisterDto.getLoginName());

        int insert = crUacUserDao.insert(crUacUser);
        if (0 == insert) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011012);
        }

        Map<String, Object> param = Maps.newHashMap();
        param.put("loginName", userWebRegisterDto.getLoginName());
        param.put("email", userWebRegisterDto.getEmail());
    }

    @Override
    public CrUacUser login(UacWebLoginDto uacWebLoginDto) {

        // 判断用户是以为什么方式登录进来的
        Preconditions.checkArgument(!StringUtils.isEmpty(uacWebLoginDto.getLoginPwd()), "密码不能为空");
        CrUacUser crUacUser = crUacUserDao.selectById(uacWebLoginDto.getEmail());
        return null;
    }

    private void validateRegisterInfo(UserWebRegisterDto userWebRegisterDto) {
        // 用户手机号
        String mobileNo = userWebRegisterDto.getMobileNo();

        // 一系列的非空校验
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getEmail()), ErrorCodeEnum.UAC10011018.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getConfirmPwd()), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getRegisterCodeType()), "验证类型错误");
        Preconditions.checkArgument(!StringUtils.isEmpty(userWebRegisterDto.getRegisterResource()), "注册来源错误");
        Preconditions.checkArgument(userWebRegisterDto.getLoginPwd().equals(userWebRegisterDto.getConfirmPwd()), "两次密码不一致");

        CrUacUser crUacUser = new CrUacUser();
        crUacUser.setLoginName(userWebRegisterDto.getLoginName());

        // 通过loginName看看数据库中有没有已经注册
        int count = crUacUserDao.findByLoginName(crUacUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011012);
        }

        crUacUser = new CrUacUser();
        crUacUser.setMobileNo(userWebRegisterDto.getMobileNo());
        // 通过loginName看看数据库中有没有已经注册
        count = crUacUserDao.findByMobile(crUacUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011013);
        }

        crUacUser = new CrUacUser();
        crUacUser.setEmail(userWebRegisterDto.getEmail());
        count = crUacUserDao.findByEmail(crUacUser);
        if (count > 0) {
            throw new UacWebBusinessException(ErrorCodeEnum.UAC10011019);
        }
    }

}
