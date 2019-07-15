package com.qf.service;

import com.qf.pojo.Leave;
import com.qf.pojo.User;

import java.util.List;

public interface Service {
    User queryUserByUsername(String username);

    int addLeave(Leave leave);

    List<Leave> queryLeaveListByUid(int uid);

    List<Leave> queryLeaveListByState(int apply_leave);

    void updateLeaveByLid(int lid);

    List<String> queryRoleByUsername(String username);

    List<String> queryPermissionByRole_name(String role_name);
}
