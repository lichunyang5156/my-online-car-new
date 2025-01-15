package com.yang.patternDesignServer.chainOfResponsibility;

import org.springframework.stereotype.Service;

@Service
public class BusinessLeaderApprover extends Approver{

    @Override
    public void approvedRequest(PermissionRequest permissionRequest) {
        System.out.println("请假申请被【事业部领导】审批：同意");
    }
}
