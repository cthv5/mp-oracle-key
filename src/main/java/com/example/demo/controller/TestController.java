package com.example.demo.controller;

import com.example.demo.core.entity.Dpt;
import com.example.demo.core.mapper.DptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DptMapper dptMapper;

    @GetMapping("test")
    public String test() {
        Dpt dpt = new Dpt();
//        dpt.setDeptId(25L);
        dpt.setMemberCode("0000");
        dpt.setDeptCode("test3");
        dpt.setDeptName("测试部门");
        dpt.setDeptManager("xxxx");
        dpt.setDeptRemark("测试");
        dpt.setDeptIsleaf(0);
        dpt.setDeptParent("1");
        dpt.setDeptNodecode("1.test1");
        int res = dptMapper.insert(dpt);
//        int res = dptMapper.saveDpt(dpt);  //这种方式KeySequence不生效
        log.info(">>>" + res);
        return "test success";
    }
}
