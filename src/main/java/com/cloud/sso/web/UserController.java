package com.cloud.sso.web;

import com.cloud.sso.web.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/info")
    public String list() {
        return "userList..";
    }

}
