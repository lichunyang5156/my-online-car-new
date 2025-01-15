package com.yang.patternDesignServer.chainOfResponsibility;

public abstract class Approver {

    protected Approver approver;

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void approvedRequest(PermissionRequest permissionRequest);
}
