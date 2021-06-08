package com.zjt.graduation.common.security.utils;

import com.zjt.graduation.common.config.exception.BeanTransFormException;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.CollectionUtils;

import javax.lang.model.UnknownEntityException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author hyh
 * @Date: 2020/10/2 16:57
 * @Version 1.0
 */
public class BeanUtils {
    private static Logger log = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 批量转换
     * 常用于DTO和Entity之间的互转
     */
    public static <T> List<T> batchTransform(final Class<T> clazz, List<?> srcList) {
        if (CollectionUtils.isEmpty(srcList)) {
            //Collections中直接静态实例化了一个特殊的空集合(AbstractList的实现类)，该集合不支持填入数据，线程安全。
            return Collections.emptyList();
        }

        //ArrayList底层是数组实现，对于已知元素数量的情况下，初始化时制定集合大小，避免了数组扩容拷贝的性能问题。
        List<T> result = new ArrayList<>(srcList.size());
        srcList.forEach(item->{
            result.add(transform(clazz, item));
        });
        return result;
    }

    /**
     * 常用于DTO和Entity之间的互转
     * 注意：DTO和Entity需要有一个无参构造函数。
     */
    public static <T> T transform(Class<T> clazz, Object src) {
        if (src == null) {
            return null;
        }
        T instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(3333,"转换出错");
        }
        //转换时忽略掉值为null的属性
        org.springframework.beans.BeanUtils.copyProperties(src, instance, getNullPropertyNames(src));

        return instance;
    }

  /*  public static <T, M> T excelTransform(Class<T> clazz, M src){
        boolean isExcelDTO = ExcelDTO.class.isAssignableFrom(src.getClass());
        boolean isEntity = BaseEntity.class.isAssignableFrom(src.getClass());
        if(!isExcelDTO && !isEntity){
            throw new BeanTransFormException(ConstantEnum.EXCEL_TRANSFER_NOT_SUPPORT_TYPE);
        }
        Field[] fields = src.getClass().getDeclaredFields();
        T result = org.springframework.beans.BeanUtils.instantiateClass(clazz);
        for (Field field : fields) {
            try{
                FieldUtils.getField(clazz, field.getName());
            }catch (Exception e){
                continue;
            }
            String fieldName = field.getName();
            Object value = FieldUtils.getProtectedFieldValue(fieldName, src);
            if(field.getType() == FieldUtils.getField(clazz, fieldName).getType()){
                FieldUtils.setProtectedFieldValue(fieldName, result, value);
            }else{
                CellMap cellMap = field.getAnnotation(CellMap.class);
                Converter converter = org.springframework.beans.BeanUtils.instantiateClass(cellMap.converter());
                try {
                    CellData cellData = new CellData();
                    cellData.setStringValue(value.toString());
                    FieldUtils.setProtectedFieldValue(fieldName, result,
                            isExcelDTO ? converter.convertToJavaData(cellData, null, null) : converter.convertToExcelData(value, null, null));
                } catch (Exception e) {
                    log.error("字段{}拷贝失败，类型：{}->{}, 值：{}", fieldName, field.getType(), FieldUtils.getField(clazz, fieldName).getType(), value);
                    continue;
                }
            }
        }
        return result;
    }*/

   /* public static <T, M> List<T> batchExcelTransform(Class<T> clazz, List<M> src){
        List<T> list = new ArrayList(src.size());
        src.stream().forEach(item->list.add(excelTransform(clazz, item)));
        return list;*/
    //}

   /* public static <T> void autoFillId(T enity){
        Field[] fields = enity.getClass().getDeclaredFields();
        for (Field field : fields) {
            IdConvert convert = field.getAnnotation(IdConvert.class);
            if(convert != null){
                org.springframework.beans.BeanUtils.instantiateClass(convert.converter()).convert(enity, field);
            }
        }
    }*/

    /**
     * 获取bean中值为null的属性集合。
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] descriptors = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor descriptor : descriptors) {
            Object srcValue = src.getPropertyValue(descriptor.getName());
            if (srcValue == null) {
                emptyNames.add(descriptor.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 用于将一个列表转换为列表中的对象的某个属性映射到列表中的对象
     *
     * <pre>
     *      List<UserDTO> userList = userService.queryUsers();
     *      Map<Integer, userDTO> userIdToUser = BeanUtil.mapByKey("userId", userList);
     * </pre>
     *
     * @param key 属性名
     */
  /*  @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> mapByKey(String key, List<?> list) {
        Map<K, V> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        try {
            Field field = getAccessibleField(list.get(0).getClass(), key);
            for (Object o : list) {
                map.put((K) field.get(o), (V) o);
            }
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(GLOBAL_BEANUTIL_ERROR);
        }
        return map;
    }
*/
   /* *//**
     * 根据列表里面的属性聚合
     *
     * <pre>
     *       List<ShopDTO> shopList = shopService.queryShops();
     *       Map<Integer, List<ShopDTO>> city2Shops = BeanUtil.aggByKeyToList("cityId", shopList);
     * </pre>
     *//*
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> aggByKeyToList(String key, List<?> list) {
        Map<K, List<V>> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {// 防止外面传入空list
            return map;
        }
        try {
            Field field = getAccessibleField(list.get(0).getClass(), key);
            for (Object o : list) {
                K k = (K) field.get(o);
                map.computeIfAbsent(k, k1 -> new ArrayList<>());
                map.get(k).add((V) o);
            }
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(GLOBAL_BEANUTIL_ERROR);
        }
        return map;
    }
*/
  /*  *//**
     * 用于将一个对象的列表转换为列表中对象的属性集合
     *
     * <pre>
     *     List<UserDTO> userList = userService.queryUsers();
     *     Set<Integer> userIds = BeanUtil.toPropertySet("userId", userList);
     * </pre>
     *//*
    @SuppressWarnings("unchecked")
    public static <K> Set<K> toPropertySet(String key, List<?> list) {
        Set<K> set = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {// 防止外面传入空list
            return set;
        }
        try {
            Field field = getAccessibleField(list.get(0).getClass(), key);
            for (Object o : list) {
                set.add((K)field.get(o));
            }
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(GLOBAL_BEANUTIL_ERROR);
        }
        return set;
    }*/


    private static Field deepFindField(Class<?> clazz, String key) {
        Field field = null;
        while (!clazz.getName().equals(Object.class.getName())) {
            try {
                field = clazz.getDeclaredField(key);
                if (field != null) {
                    break;
                }
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    /**
     * 提取给定类的属性字段
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getAccessibleField(Class clazz, String fieldName){
        Field field = deepFindField(clazz, fieldName);
        if (field == null){
            throw new IllegalArgumentException(String.format("class[%s] not contains property[%s]!", clazz.getName(), fieldName));
        }
        field.setAccessible(true);
        return field;
    }

    /**
     * 获取某个对象的某个属性
     */
    /*public static Object getProperty(Object obj, String fieldName) {
        try {
            Field field = deepFindField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(obj);
            }
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(GLOBAL_BEANUTIL_ERROR);
        }
        return null;
    }
*/
    /**
     * 设置某个对象的某个属性
     */
    /*public static void setProperty(Object obj, String fieldName, Object value) {
        try {
            Field field = deepFindField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, value);
            }
        } catch (Exception e) {
            log.error("bean transform failed:", e);
            throw new BeanTransFormException(GLOBAL_BEANUTIL_ERROR);
        }
    }
*/
    /**
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * The copy will ignore <em>BaseEntity</em> field
     *
     * @param source
     * @param target
     */
    public static void copyEntityProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, COPY_IGNORED_PROPERTIES);
    }

    /**
     * 属性拷贝时要忽略的字段
     */
    private static final String[] COPY_IGNORED_PROPERTIES = {"id", "dataChangeCreatedBy", "dataChangeCreatedTime", "dataChangeLastModifiedTime"};
}

