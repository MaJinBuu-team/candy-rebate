package cn.com.mjb.candyrebateadmin.service.uac;

import cn.com.buu.dailyfresh.module.vo.UacLoginVo;
import cn.com.mjb.candyrebateadmin.module.domain.PcUacUser;

public interface UacService {

    PcUacUser login(UacLoginVo uacLoginVo);

}
