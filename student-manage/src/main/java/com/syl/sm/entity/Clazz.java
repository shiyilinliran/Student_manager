package com.syl.sm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Clazz
 * @Description TODO
 * @Author admin
 * @Date 2020/12/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clazz {
    private Integer id;
    private Integer departmentId;
    private String className;
    @Override
    public String toString() {
        return className;
    }
}