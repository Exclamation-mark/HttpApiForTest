package org.adp.apitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zzq
 */
@Controller
@RequestMapping("post")
public class PostController {

    @Resource
    private ObjectMapper objectMapper;

    @PostMapping("withQuery")
    @ResponseBody
    public String returnStr(@RequestParam String name) {
        return RandomStringUtils.random(20, true, false) + "_" + name;
    }

    @PostMapping("respIsJson")
    @ResponseBody
    public ObjectNode respIsJson(@RequestParam String name, @RequestParam(required = false) String str) {
        return objectMapper
                .createObjectNode()
                .put("name", name)
                .put("str", str)
                .put("random", RandomStringUtils.random(10, true, false))
                ;
    }

    @PostMapping("reqIsJson")
    @ResponseBody
    public ObjectNode reqIsJson(@RequestBody ObjectNode req) {
        return objectMapper
                .createObjectNode()
                .put("random", RandomStringUtils.random(10, true, false))
                .set("req", req)
                ;
    }

}
