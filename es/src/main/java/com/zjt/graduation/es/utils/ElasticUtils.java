package com.zjt.graduation.es.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/17 17:05
 */
public class ElasticUtils {
    public static PageRequest pageable(Integer current, Integer size){
        return PageRequest.of(current,size);
    }


    public static<T> Page<T>  pageResult(Integer current, Integer size, List<T> searchHits){
        List<T> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(searchHits)){

            for (int i = 0; i < searchHits.size(); i++) {
                resultList.add(searchHits.get(i));
            }
        }
        Page page = new Page();
        page.setRecords(resultList);
        page.setSize(size);
        page.setCurrent(current);

        return page;
    }
}
