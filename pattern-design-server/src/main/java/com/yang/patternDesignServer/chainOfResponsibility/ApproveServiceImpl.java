package com.yang.patternDesignServer.chainOfResponsibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveServiceImpl {

    @Autowired
    private DirectLeaderApprover directLeaderApprover;
    @Autowired
    private DepartmentLeaderApprover departmentLeaderApprover;
    @Autowired
    private BusinessLeaderApprover businessLeaderApprover;

    public void setChain(){
        directLeaderApprover.setApprover(departmentLeaderApprover);
        departmentLeaderApprover.setApprover(businessLeaderApprover);
    }

    public void doApproveRequest(PermissionRequest request){
        directLeaderApprover.approvedRequest(request);
    }
}
