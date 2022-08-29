package org.adp.apitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.adp.apitest.utils.RequestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

/**
 * @author zzq
 */
@Controller
@RequestMapping("get")
@Slf4j
public class GetTestController {

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("file")
    public void getFile(HttpServletResponse response) {
        log.info("calling {}", RequestUtils.getUrl());
        response.setHeader("Content-type", "application/xlsx");
        response.setCharacterEncoding("utf-8");
        String fileName = "attachment; filename=" + URLEncoder.encode("账单") + "-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ".xlsx";
        response.setHeader(CONTENT_DISPOSITION, fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            final ServletOutputStream outputStream = response.getOutputStream();
            final InputStream templateInputStream = this.getClass().getResourceAsStream("/template/test.xlsx");
            StreamUtils.copy(templateInputStream, outputStream);
            outputStream.close();
            templateInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
