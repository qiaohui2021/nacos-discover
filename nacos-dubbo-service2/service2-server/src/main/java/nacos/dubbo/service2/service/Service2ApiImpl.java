package nacos.dubbo.service2.service;

import nacos.dubbo.service2.api.Service2Api;

/**
 * @author qiaohui
 * @date 2020/10/28 15:56
 */
//建议全路径
@org.apache.dubbo.config.annotation.Service
public class Service2ApiImpl implements Service2Api {
    @Override
    public String dubboService2() {
        return "dubboService2";
    }

}
