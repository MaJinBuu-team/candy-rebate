package cn.com.mjb.candyrebateadmin.service.uac;

import cn.com.mjb.candyrebateadmin.module.domain.PcUacUser;
import cn.com.mjb.candyrebateadmin.module.vo.UacLoginVo;

public interface UacService {

    PcUacUser login(UacLoginVo uacLoginVo);

}
