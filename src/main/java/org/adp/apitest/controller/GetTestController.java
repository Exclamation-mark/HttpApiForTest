package org.adp.apitest.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzq
 */
@Controller
@RequestMapping("get")
public class GetTestController {

    @GetMapping("str")
    @ResponseBody
    public String getStr() {
        return RandomStringUtils.random(20, true, false);
    }
}
