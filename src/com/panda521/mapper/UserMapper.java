package com.panda521.mapper;

import com.panda521.entity.UserDO;

/**
 * Created by jack on 2018/10/15.
 */
public interface UserMapper {
    UserDO queryUserById(Integer id);
}