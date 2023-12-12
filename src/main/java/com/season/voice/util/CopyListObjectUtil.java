package com.season.voice.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 拷贝list对象
 * @author: jack_mo
 * @date: 2023/04/12 18:14
 **/
public class CopyListObjectUtil {

    /**
     * 将原 list 对象中的元素复制到目标类型的 list 对象中
     *
     * @param sourceList 原 list 对象
     * @param targetClass 目标类型的 Class 对象
     * @return 目标类型的 list 对象
     * @throws Exception
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();

        for (S source : sourceList) {
            T target = null;
            try {
                target = targetClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        return targetList;
    }

    /**
     * 将原数据对象中的元素复制到目标类型的对象中
     *
     * @param sourceData 原数据对象
     * @param targetClass 目标类型的 Class 对象
     * @return 目标类型的 list 对象
     * @throws Exception
     */
    public static <S, T> T copy(S sourceData, Class<T> targetClass) {
        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (sourceData == null) {
            return target;
        }
        BeanUtils.copyProperties(sourceData, target);

        return target;
    }
}
