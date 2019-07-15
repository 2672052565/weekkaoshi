package com.qf.mapper;

import com.qf.pojo.Leave;

import java.util.List;

public interface LeaveMapper {
    void addLeave(Leave leave);

    List<Leave> queryLeaveListByUid(int uid);

    List<Leave> queryLeaveListByState(int apply_leave);

    void updateLeaveByLid(int lid);
}
