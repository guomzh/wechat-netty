package com.zgm.service.impl;

import com.zgm.mapper.UserMapper;
import com.zgm.pojo.User;
import com.zgm.pojo.vo.UserVo;
import com.zgm.service.UserService;
import com.zgm.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author zgm
 * @description
 * @date 2018/10/6 17:39
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private Sid sid;

    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = new User();
        user.setUsername(username);
        User result = userMapper.selectOne(user);
        return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String password) {
        Example userExample = new Example(UserVo.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        User result = userMapper.selectOneByExample(userExample);
        return result;
    }

    @Override
    public User saveUser(User user) {
        user.setNickname(user.getUsername());
        user.setFaceImage("");
        user.setFaceImageBig("");

        try {
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        } catch (Exception e){

        }
        String userId = sid.nextShort();
        user.setId(userId);
        //为每个用户生成唯一的二维码
        user.setQrcode("");
        userMapper.insert(user);
        return user;
    }


}
