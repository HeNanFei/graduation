package com.zjt.graduation.common.query;

/**
 * @Author hyh
 * @Date: 2020/12/7 22:23
 * @Version 1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.utils.SpringContext;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ComplexQueryRequest<T> {
    @Autowired
    private SpringContext springContext;

    @ApiModelProperty(
            name = "page",
            value = "分页"
    )
    private Page page;
    @ApiModelProperty(
            name = "request",
            value = "条件存储值对象"
    )
    private T request;
    @ApiModelProperty(
            name = "opMap",
            value = "查询比较操作符"
    )
    private HashMap<String, String> opMap;

    public ComplexQueryRequest() {
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public T getRequest() {
        return this.request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public void setOpMap(HashMap<String, String> opMap) {
        this.opMap = opMap;
    }

    public HashMap getOpMap() {
        return this.opMap;
    }

    public <T> QueryWrapper<T> queryWrapper() {
        return this.queryWrapper((String)null);
    }

    public <M> QueryWrapper<M> queryWrapper(String alias) {
        QueryWrapper<M> queryWrapper = new QueryWrapper();
        Iterator var3 = this.opMap.keySet().iterator();

        while(true) {
            while(var3.hasNext()) {
                String fieldName = (String)var3.next();
                String sqlkey;
/*
                if (Boolean.valueOf(springContext.getApplicationContext().getEnvironment().getProperty("mybatis-plus.configuration.map-underscore-to-camel-case"))) {
*/
                Boolean result = false;
                if(result){
                    sqlkey = StringUtils.isEmpty(alias) ? fieldName : alias + "." + fieldName.replaceAll("[A-Z]", "_$0").toLowerCase();
                } else {
                    sqlkey = StringUtils.isEmpty(alias) ? fieldName : alias + "." + fieldName;
                }

                Object value = FieldUtils.getProtectedFieldValue(fieldName, this.request);
                if ("like".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                    queryWrapper.like(sqlkey, Array.get(value, 0));
                } else if ("llike".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                    queryWrapper.likeLeft(sqlkey, Array.get(value, 0));
                } else if ("rlike".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                    queryWrapper.likeRight(sqlkey, Array.get(value, 0));
                } else if ("eq".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                    queryWrapper.eq(sqlkey, Array.get(value, 0));
                } else {
                    int i;
                    ArrayList list;
                    //int i;
                    if ("in".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        i = Array.getLength(value);
                        if (i > 0) {
                            list = new ArrayList(i);

                            for(i = 0; i < i; ++i) {
                                list.add(Array.get(value, i));
                            }

                            queryWrapper.in(sqlkey, list);
                            continue;
                        }
                    }

                    if ("notin".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        i = Array.getLength(value);
                        if (i > 0) {
                            list = new ArrayList(i);

                            for(i = 0; i < i; ++i) {
                                list.add(Array.get(value, i));
                            }

                            queryWrapper.notIn(sqlkey, list);
                            continue;
                        }
                    }

                    if ("notlike".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.notLike(sqlkey, Array.get(value, 0));
                    } else if ("ne".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.ne(sqlkey, Array.get(value, 0));
                    } else if ("gt".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.gt(sqlkey, Array.get(value, 0));
                    } else if ("ge".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.ge(sqlkey, Array.get(value, 0));
                    } else if ("lt".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.lt(sqlkey, Array.get(value, 0));
                    } else if ("le".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.le(sqlkey, Array.get(value, 0));
                    } else if ("gtlt".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        ((QueryWrapper)queryWrapper.gt(sqlkey, Array.get(value, 0))).lt(sqlkey, Array.get(value, 1));
                    } else if ("gtle".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        ((QueryWrapper)queryWrapper.gt(sqlkey, Array.get(value, 0))).le(sqlkey, Array.get(value, 1));
                    } else if ("gelt".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        ((QueryWrapper)queryWrapper.ge(sqlkey, Array.get(value, 0))).lt(sqlkey, Array.get(value, 1));
                    } else if ("gele".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        ((QueryWrapper)queryWrapper.ge(sqlkey, Array.get(value, 0))).le(sqlkey, Array.get(value, 1));
                    } else if ("inset".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        if (Array.getLength(value) == 1) {
                            queryWrapper.apply("FIND_IN_SET('" + Array.get(value, 0) + "', " + sqlkey + ")", new Object[0]);
                        } else if (Array.getLength(value) == 2) {
                            queryWrapper.apply("(FIND_IN_SET('" + Array.get(value, 0) + "', " + sqlkey + ")", new Object[0]);
                            ((QueryWrapper)queryWrapper.or()).apply("FIND_IN_SET('" + Array.get(value, 1) + "', " + sqlkey + "))", new Object[0]);
                        } else if (Array.getLength(value) > 2) {
                            for(i = 0; i < Array.getLength(value); ++i) {
                                if (i == 0) {
                                    queryWrapper.apply("(FIND_IN_SET('" + Array.get(value, i) + "', " + sqlkey + ")", new Object[0]);
                                } else if (i < Array.getLength(value) - 1) {
                                    ((QueryWrapper)queryWrapper.or()).apply("FIND_IN_SET('" + Array.get(value, i) + "', " + sqlkey + ")", new Object[0]);
                                } else {
                                    ((QueryWrapper)queryWrapper.or()).apply("FIND_IN_SET('" + Array.get(value, i) + "', " + sqlkey + "))", new Object[0]);
                                }
                            }
                        }
                    } else if ("isnull".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.isNull(sqlkey);
                    } else if ("isnotnull".equalsIgnoreCase((String)this.opMap.get(fieldName))) {
                        queryWrapper.isNotNull(sqlkey);
                    }
                }
            }

            if (StringUtils.isEmpty(queryWrapper.getTargetSql())) {
                queryWrapper.eq("1", "1");
            }

            return queryWrapper;
        }
    }
}

