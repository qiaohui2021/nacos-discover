package nacos.dubbo.service1.service;

import nacos.dubbo.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author qiaohui
 * @date 2020/11/6 10:17
 */
@Service
public class Service1ApiImpl implements Service1Api{

    //service1调用service2
    @Reference
    private Service2Api service2Api;

    @Override
    public String dubboService1() {

        //远程调用service2
        String s = service2Api.dubboService2();
        return "dubboService1"+s;
    }
}
