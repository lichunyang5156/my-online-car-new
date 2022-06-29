package com.yang.cloudzuul.dao;

import com.yang.cloudzuul.entity.CommonGrayRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonGrayRuleDaoCustom extends CommonGrayRuleDao {

    CommonGrayRule selectByUserId(Integer userId);
}
