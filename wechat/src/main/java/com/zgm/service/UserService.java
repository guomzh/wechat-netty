package com.zgm.service;

import com.zgm.pojo.User;

/**
 * @author zgm
 * @description
 * @date 2018/10/6 17:38
 */
public interface UserService {

    public boolean queryUsernameIsExist(String username);

    public User queryUserForLogin(String username, String password);

    public User saveUser(User user);

}
