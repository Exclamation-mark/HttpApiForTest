package org.adp.apitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.adp.apitest.utils.RequestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zzq
 */
@Controller
@RequestMapping("get")
@Slf4j
public class GetTestController {

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("str")
    @ResponseBody
    public String getStr() {
        log.info("calling {}", RequestUtils.getUrl());
        return RandomStringUtils.random(20, true, false);
    }

    @GetMapping("json")
    @ResponseBody
    public ObjectNode getStr(@RequestParam String name, @RequestParam(name = "str", required = false) String str) {
        log.info("calling {}", RequestUtils.getUrl());
        ObjectNode resource = objectMapper.createObjectNode()
                .put("name", name)
                .put("salt", RandomStringUtils.random(15, true, false))
                .put("str", str);
        return resource;
    }
}
