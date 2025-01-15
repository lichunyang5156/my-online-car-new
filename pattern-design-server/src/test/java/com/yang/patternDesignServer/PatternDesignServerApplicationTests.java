package com.yang.patternDesignServer;

import com.yang.patternDesignServer.chainOfResponsibility.ApproveServiceImpl;
import com.yang.patternDesignServer.chainOfResponsibility.PermissionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatternDesignServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ApproveServiceImpl approveService;

    @Test
    public void testApproveService(){
        PermissionRequest request=new PermissionRequest();
        request.setDays(1);
        approveService.setChain();
        approveService.doApproveRequest(request);
        System.out.println("=======");
    }

}
