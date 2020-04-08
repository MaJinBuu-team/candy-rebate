package cn.com.mjb.candyrebateweb.service.uac.impl;

import cn.com.mjb.candyrebateweb.service.uac.BaseService;

import java.util.UUID;

public class BaseServiceImpl implements BaseService {
    @Override
    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
