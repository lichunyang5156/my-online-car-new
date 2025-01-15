package com.yang.patternDesignServer.chainOfResponsibility;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *  不使用设计模式时的审批流程
 */
@Service
public class NormalApprovedProcess {

    public void doHandleRequest(String currentUserRole){
        if (Objects.equals(currentUserRole,"直属领导")){
            System.out.println("审批同意");
        }
        if (Objects.equals(currentUserRole,"部门领导")){
            System.out.println("审批同意");
        }
        if (Objects.equals(currentUserRole,"业务部门领导")){
            System.out.println("审批同意");
        }
        if (Objects.equals(currentUserRole,"行政领导")){
            System.out.println("审批同意");
        }
    }

}
