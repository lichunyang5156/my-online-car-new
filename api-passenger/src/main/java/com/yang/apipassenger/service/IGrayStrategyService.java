package com.yang.apipassenger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.apipassenger.entity.GrayStrategy;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-29
 */
public interface IGrayStrategyService extends IService<GrayStrategy> {

    GrayStrategy getByUserId(String userId);
}
