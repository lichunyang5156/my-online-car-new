package com.yang.patternDesignServer.chainOfResponsibility;

import lombok.Data;

@Data
public class PermissionRequest {

    private String name;

    private int days;

    private String reason;
}
