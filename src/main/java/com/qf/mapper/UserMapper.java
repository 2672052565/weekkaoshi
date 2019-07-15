package com.qf.mapper;

import com.qf.pojo.User;

import java.util.List;

public interface UserMapper {
    User queryUserByUsername(String username);

    List<String> queryRoleByUsername(String username);

    List<String> queryPermissionByRole_name(String role_name);
}
