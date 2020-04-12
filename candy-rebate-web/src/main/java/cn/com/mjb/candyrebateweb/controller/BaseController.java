package cn.com.mjb.candyrebateweb.controller;

import java.util.UUID;

public class BaseController {

    protected String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
