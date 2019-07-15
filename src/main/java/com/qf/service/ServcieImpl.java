package com.qf.service;

import com.qf.mapper.LeaveMapper;
import com.qf.mapper.UserMapper;
import com.qf.pojo.Leave;
import com.qf.pojo.User;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServcieImpl implements Service {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public List<String> queryPermissionByRole_name(String role_name) {
        List<String> permissions = userMapper.queryPermissionByRole_name(role_name);
        return permissions;
    }

    public List<String> queryRoleByUsername(String username) {
        List<String> roles = userMapper.queryRoleByUsername(username);
        return roles;
    }

    public void updateLeaveByLid(int lid) {
        leaveMapper.updateLeaveByLid(lid);
        String id = taskService.createTaskQuery().processInstanceBusinessKey("" + lid).singleResult().getId();
        taskService.complete(id);
    }

    public List<Leave> queryLeaveListByState(int apply_leave) {
        List<Leave> list = leaveMapper.queryLeaveListByState(apply_leave);
        return list;
    }

    public List<Leave> queryLeaveListByUid(int uid) {
        List<Leave> list = leaveMapper.queryLeaveListByUid(uid);
        return list;
    }

    public int addLeave(Leave leave) {
        leaveMapper.addLeave(leave);
        int lid = leave.getLid();
        //启动流程实例
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("student",leave.getUser().getUsername());
        map.put("teacher","manager1");
        runtimeService.startProcessInstanceByKey("leave",lid+"",map);
        //学生完成任务
        String id = taskService.createTaskQuery()
                .taskAssignee(leave.getUser().getUsername()).singleResult().getId();

        taskService.complete(id);
        return 0;
    }

    public User queryUserByUsername(String username) {
        User user = userMapper.queryUserByUsername(username);
        return null;
    }
}
