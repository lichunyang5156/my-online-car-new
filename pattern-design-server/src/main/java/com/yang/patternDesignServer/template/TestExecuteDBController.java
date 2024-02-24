package com.yang.patternDesignServer.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class TestExecuteDBController {


    @Autowired
    private MySqlDBHandler mySqlDBHandler;

    @GetMapping("/exe")
    public void executeSQL(){
        mySqlDBHandler.executeDBOperate();
    }
}
