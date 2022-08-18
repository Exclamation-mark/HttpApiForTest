package org.adp.apitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.adp.apitest.utils.RequestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author zzq
 */
@Controller
@RequestMapping("post")
@Slf4j
public class PostController {

    @Resource
    private ObjectMapper objectMapper;

    @PostMapping("withQuery")
    @ResponseBody
    public String returnStr(@RequestParam String name) {
        log.info("calling {}", RequestUtils.getUrl());
        return RandomStringUtils.random(20, true, false) + "_" + name;
    }

    @PostMapping("respIsJson")
    @ResponseBody
    public ObjectNode respIsJson(@RequestParam String name, @RequestParam(required = false) String str) {
        log.info("calling {}", RequestUtils.getUrl());
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

    @PostMapping("file")
    @ResponseBody
    public ObjectNode reqIsJson(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam String name
    ) {
        return objectMapper
                .createObjectNode()
                .put("fileId", RandomStringUtils.random(10, true, false))
                .put("fileName", file.getName())
                .put(name, name)
                .put("originFileName", file.getOriginalFilename())
                .put("contentType", file.getContentType())
                .put("size", file.getSize())
                ;
    }

}
