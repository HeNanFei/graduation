package com.zjt.graduation.serverfile.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author hyh
 * @Date: 2020/10/25 14:26
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestClazz {
    private String name;

    private String mobile;

    private Integer any;

}
