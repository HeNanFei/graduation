package com.zjt.graduation.serverfile.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zjt.graduation.common.annota.AutoValidate;
import com.zjt.graduation.common.security.utils.BeanUtils;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import com.zjt.graduation.common.utils.ValidateUtils;
import com.zjt.graduation.common.entity.SellGood;
import com.zjt.graduation.serverfile.entity.ErrorInfo;
import com.zjt.graduation.serverfile.entity.SellGoodExcel;
import com.zjt.graduation.common.service.SellGoodService;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class SellGoodsListenner extends AnalysisEventListener<SellGoodExcel> {

    private List<SellGood> sellGoods;

    private List<ErrorInfo> errorInfos;

    private List<SellGoodExcel> sellGoodExcels;

    private Field[] fields;

    private Class aClass;

    private Boolean result;

    private HashSet<SellGoodExcel> sellGoodExcelHashSet;

    private Validator validator;

    public SellGoodsListenner() {
    }

    public SellGoodsListenner(List<SellGood> sellGoods, List<ErrorInfo> errorInfos, List<SellGoodExcel> sellGoodExcels, Class aClass) {
        result = true;
        this.sellGoods = sellGoods;
        this.errorInfos = errorInfos;
        this.sellGoodExcels = sellGoodExcels;
        this.aClass = aClass;
        this.fields = aClass.getDeclaredFields();
        this.sellGoodExcelHashSet = new HashSet<>();
        this.validator = ValidateUtils.getValidator();

    }

    @Override
    public void invoke(SellGoodExcel sellGoodExcel, AnalysisContext analysisContext) {
        Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
        Set<ConstraintViolation<SellGoodExcel>> validateResult = validator.validate(sellGoodExcel);
        if (CollectionUtils.isEmpty(validateResult)) {
            sellGoodExcel.setCurrentRowIndex(rowIndex);
            sellGoodExcels.add(sellGoodExcel);
        } else {
            result =false;
            for (ConstraintViolation<SellGoodExcel> validateError : validateResult) {
                ErrorInfo errorInfo = new ErrorInfo(rowIndex, "第" + rowIndex + "行" + validateError.getMessage());
                errorInfos.add(errorInfo);
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            HashSet hashSet = new HashSet();
            Map<Integer, String> collect = sellGoodExcels.parallelStream().collect(Collectors.toMap(n -> n.getCurrentRowIndex(), n -> n.getGoodName()));
            Set<Integer> integers = collect.keySet();
            Iterator<Integer> iterator = integers.iterator();
            while (iterator.hasNext()){
                Integer rowIndex = iterator.next();
                String s = collect.get(rowIndex);
                if(!hashSet.add(s)){
                    result =false;
                    ErrorInfo errorInfo = new ErrorInfo(rowIndex,"第"+rowIndex+"行商品名重复，请检查");
                    errorInfos.add(errorInfo);
                }
            }
            if(result){
                SellGoodService sellGoodService = ApplicationContextUtils.getBean(SellGoodService.class);
                List<SellGood> saveData = new ArrayList<>();
                for (int i = 0; i < sellGoodExcels.size(); i++) {
                    SellGood sellGood = new SellGood();
                    BeanUtils.copyProperties(sellGoodExcels.get(i),sellGood);
                    saveData.add(sellGood);
                }
                sellGoodService.saveBatch(saveData);
            }
        }



    public static List<String> cantBeRepeat(Field[] fields) {
        List<String> cantBeRepeat = new ArrayList<>();
        for (Field field : fields) {
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            for (int i = 0; i < declaredAnnotations.length; i++) {
                if (declaredAnnotations[i].getClass().equals(AutoValidate.class)) {
                    if (!field.getAnnotation(AutoValidate.class).isRepeat()) {
                        cantBeRepeat.add(field.getName());
                    }
                }
            }
        }
        return cantBeRepeat;
    }
}


