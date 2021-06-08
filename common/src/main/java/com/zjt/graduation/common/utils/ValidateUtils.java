package com.zjt.graduation.common.utils;

import com.zjt.graduation.common.validate.ValidResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
public class ValidateUtils {
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();


    public static Validator getValidator(){return  validator;}
    /**
     * 校验对象
     *
     * @param t      bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T t, Class<?> groups, Integer rowIndex) {
        ValidResult result = new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t, groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                //result.setColumnName();
                //result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return result;
    }
}
//    /**
//     * 校验bean的某一个属性
//     *
//     * @param obj          bean
//     * @param propertyName 属性名称
//     * @return ValidResult
//     */
//    public static <T> ValidResult validateProperty(T obj, String propertyName) {
//        ValidResult result = new ValidationUtil().new ValidResult();
//        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
//        boolean hasError = violationSet != null && violationSet.size() > 0;
//        result.setHasErrors(hasError);
//        if (hasError) {
//            for (ConstraintViolation<T> violation : violationSet) {
//                result.addError(propertyName, violation.getMessage());
//            }
//        }
//        return result;
//    }
//    /**
//     * 校验类
//     */
//    public static <T> List<ValidResult> getValiteResultList(T obj){
//        List<ValidResult> validResults = new ArrayList<>();
//        Field[] declaredFields = obj.getClass().getDeclaredFields();
//        for (Field field: declaredFields) {
//            ValidResult validResult = new ValidationUtil().new ValidResult();
//            Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(obj, field.getName());
//            boolean hasError = constraintViolations != null && constraintViolations.size() > 0;
//            validResult.setHasErrors(hasError);
//            if (hasError) {
//                for (ConstraintViolation<T> violation : constraintViolations) {
//                    validResult.addError(field.getName(), violation.getMessage());
//                }
//                validResults.add(validResult);
//            }
//        }
//        return validResults;
//    }

