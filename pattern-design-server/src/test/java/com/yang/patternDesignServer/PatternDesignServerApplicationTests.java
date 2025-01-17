package com.yang.patternDesignServer;

import com.yang.patternDesignServer.chainOfResponsibility.ApproveServiceImpl;
import com.yang.patternDesignServer.chainOfResponsibility.PermissionRequest;
import com.yang.patternDesignServer.strategy.DiscountStrategy;
import com.yang.patternDesignServer.strategy.DiscountStrategyFactory;
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


    @Autowired
    private DiscountStrategyFactory discountStrategyFactory;

    @Test
    public void testStrategy(){
        //todo 此处可根据业务需要决定使用哪种策略，策略名称和Bean名称保持一致就可以了
        DiscountStrategy fullDiscountStrategy = discountStrategyFactory.
                getDiscountStrategy("fullDiscountStrategy");
        fullDiscountStrategy.doDiscount();
        //-----------------------------------------------------------------
        DiscountStrategy directDiscountStrategy = discountStrategyFactory.
                getDiscountStrategy("directDiscountStrategy");
        directDiscountStrategy.doDiscount();

    }

}
