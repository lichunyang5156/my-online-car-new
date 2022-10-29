package com.yang.apipassenger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.apipassenger.dao.GrayStrategyMapper;
import com.yang.apipassenger.entity.GrayStrategy;
import com.yang.apipassenger.service.IGrayStrategyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-29
 */
@Service
public class GrayStrategyServiceImpl extends ServiceImpl<GrayStrategyMapper, GrayStrategy> implements IGrayStrategyService {

    @Override
    public GrayStrategy getByUserId(String userId) {
        return lambdaQuery().eq(GrayStrategy::getUserId,userId).one();
    }
}
