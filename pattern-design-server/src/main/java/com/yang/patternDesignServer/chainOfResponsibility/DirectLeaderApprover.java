package com.yang.patternDesignServer.chainOfResponsibility;

import org.springframework.stereotype.Service;

@Service
public class DirectLeaderApprover extends Approver{

    @Override
    public void approvedRequest(PermissionRequest permissionRequest) {
        if (permissionRequest.getDays()<1){
            System.out.println("请假申请被【直属领导】审批：同意");
        }else {
            System.out.println("请假申请被【直属领导】审批：同意,上报上级审批");
            approver.approvedRequest(permissionRequest);
        }
    }
}
