package cn.com.mjb.candyrebateweb.service.uac;


import cn.com.mjb.candyrebateweb.module.domain.CrUacUser;
import cn.com.mjb.candyrebateweb.module.dto.UacWebLoginDto;
import cn.com.mjb.candyrebateweb.module.dto.UserWebRegisterDto;

public interface UacWebService extends BaseService{

    /**
     * 注册用户.
     *
     * @param userWebRegisterDto 注册dto
     */
    void register(UserWebRegisterDto userWebRegisterDto);

    CrUacUser login(UacWebLoginDto uacLoginVo);

}
