package com.yang.apipassenger.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GrayStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userId;

    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
