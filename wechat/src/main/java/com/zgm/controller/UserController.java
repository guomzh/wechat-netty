package com.zgm.controller;

import com.zgm.pojo.User;
import com.zgm.pojo.vo.UserVo;
import com.zgm.service.UserService;
import com.zgm.utils.JSONResult;
import com.zgm.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zgm
 * @description
 * @date 2018/10/6 15:41
 */

@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registOrLogin")
    public JSONResult registOrLogin(@RequestBody User user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JSONResult.errorMsg("用户名或密码不能为空 ！");
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        User userResult = null;
        if (usernameIsExist) {
            //登录
            userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return JSONResult.errorMsg("用户名或密码不正确 ！");
            }
        } else {
            //注册
            userResult = userService.saveUser(user);
        }

        UserVo userVo =new UserVo();
        BeanUtils.copyProperties(userResult,userVo);
        return JSONResult.ok(userVo);
    }
}
