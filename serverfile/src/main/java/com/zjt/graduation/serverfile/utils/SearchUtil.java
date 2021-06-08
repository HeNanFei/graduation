package com.zjt.graduation.serverfile.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 10:22
 */
public class SearchUtil {
    //适用于单表查询
    public static <T> QueryWrapper<T> wrapperWithoutAllias(HashMap<String, Object> map) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if(CollectionUtils.isEmpty(map)){
            return queryWrapper;
        }
        Set<String> keyStrings = map.keySet();
        for (String keyString: keyStrings) {
            if(!StringUtils.isEmpty(keyString) && !StringUtils.isEmpty(map.get(keyString))) {
                if (keyString.contains("eq") || keyString.contains("Eq")) {
                    queryWrapper.eq(StringUtils.replace(keyString, "eq", ""), map.get(keyString));
                    continue;
                }
                if (keyString.contains("gt") || keyString.contains("Gt")) {
                    queryWrapper.gt(StringUtils.replace(keyString, "gt", ""), map.get(keyString));
                    continue;
                }
                if(keyString.contains("lt") || keyString.contains("Lt")){
                    queryWrapper.lt(StringUtils.replace(keyString,"lt",""),map.get(keyString));
                    continue;
                }
                if(keyString.contains("desc") || keyString.contains("Desc")){
                    String columnString = (String) map.get(keyString);
                    queryWrapper.orderByDesc(columnString);
                    continue;
                }
                if(keyString.contains("asc") || keyString.contains("Asc")){
                    String columnString = (String) map.get(keyString);
                    queryWrapper.orderByAsc(columnString);
                    continue;
                }
                if(keyString.contains("orderby") || keyString.contains("OrderBy")){
                    String columnString = (String) map.get(keyString);
                    queryWrapper.orderBy(false,false,columnString);
                    continue;
                }
                if(keyString.contains("like") || keyString.contains("Like")){
                    queryWrapper.like(StringUtils.replace(keyString,"like",""),map.get(keyString));
                    continue;
                }
                if(keyString.contains("in") || keyString.contains("In")){
                    queryWrapper.like(StringUtils.replace(keyString,"in",""),map.get(keyString));
                    continue;
                }
                if(keyString.contains("groupby") || keyString.contains("GroupBy")){
                    queryWrapper.groupBy((String) map.get(keyString));
                    continue;
                }
                if(keyString.equals("page") || keyString.equals("size")){
                    continue;
                }
                queryWrapper.eq(keyString,map.get(keyString));
            }
        }
        return queryWrapper;
    }
}
