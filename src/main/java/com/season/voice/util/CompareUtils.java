package com.season.voice.util;


import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class CompareUtils<T>{

    public static  <K> Predicate<K> distinctPredicate(Function<K,Object> function) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>();
        return (t) -> null == map.putIfAbsent(function.apply(t), true);
    }
}
