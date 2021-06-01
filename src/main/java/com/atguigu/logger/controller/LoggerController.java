package com.atguigu.logger.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接受模拟器生成的数据，并对数据进行处理
 * http://localhost:8080/test/testDemo?haha=zs&heihei=18
 * 提供方法接收请求，将参数打印输出，并响应给客户端一个sucess
 */

//@Controller //将对象的创建交给Spring容器  反转的是对对象控制的权限
    @RestController
    @Slf4j
public class LoggerController {
        @Autowired
        private KafkaTemplate kafkaTemplate;

    @RequestMapping("/applog")
    public String getLogger(@RequestParam("param") String jsonStr ) {

        //todo 将数据落盘
        log.info(jsonStr);
        //todo 将数据发送kafkaODS主题
        kafkaTemplate.send("ods_base_log",jsonStr);

        return "SUCCESS";

    }
}
