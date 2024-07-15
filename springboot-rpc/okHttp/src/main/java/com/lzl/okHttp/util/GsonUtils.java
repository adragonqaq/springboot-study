package com.lzl.okHttp.util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Gson对象工具类
 * @author likb
 */
public final class GsonUtils {

    private GsonUtils(){
    }

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private static final Gson GSON_DATE = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

    /**
     * 获取Gson的对象
     * @return Gson
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * 获取Gson的对象
     * @return Gson
     */
    public static Gson getGsonDate() {
        return GSON_DATE;
    }
    /**
     * 转成json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String gsonString = null;
        if (GSON != null) {
            gsonString = GSON.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成json
     * @param object
     * @return
     */
    public static String toJsonDate(Object object) {
        String gsonString = null;
        if (GSON_DATE != null) {
            gsonString = GSON_DATE.toJson(object);
        }
        return gsonString;
    }
    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gsonString != null) {
            t = GSON.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成bean
     * 解决日期转换问题
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBeanDate(String gsonString, Class<T> cls) {
        return GSON_DATE.fromJson(gsonString, cls);
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (GSON != null) {
            list = GSON.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static  <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        if (StringUtils.isEmpty(json)) {
            return list;
        }
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题 日期转换问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static  <T> List<T> jsonToListDate(String json, Class<T> cls) {
        List<T> list = new ArrayList();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(GSON_DATE.fromJson(elem, cls));
        }
        return list;
    }


    /**
     * 转成list中有map的
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (GSON != null) {
            list = GSON.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> toMaps(String gsonString) {
        Map<String, T> map = null;
        if (GSON != null) {
            map = GSON.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 将source对象的属性复制给target对象
     * 作用：防止复制的时候指向同一内存地址
     * @param source 初始对象
     * @param <T> 初始对象范型
     * @return 复制后的对象
     */
    public static <T> T copyObjectProperties(T source)
    {
        //查询横向扩展
        if(null == source)
        {
            return source;
        }
        String str = GsonUtils.getGson().toJson(source);
        return GsonUtils.getGson().fromJson(str, (Type) source.getClass());
    }

    /**
     * 集合对象内容复制(作用：防止复制的时候指向同一内存地址)
     * List集合对象内容复制(作用：防止复制的时候指向同一内存地址)
     * 该方法缺点：T若为map类型，会将对象中的int类型转换为double类型
     * @param list 对象
     * @return 复制后的对象
     */
    public static <T> List<T> copyListProperties(List<T> list)
    {
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        List<T> res = new ArrayList<>();
        for(T t:list){
            T t1 = GsonUtils.copyObjectProperties(t);
            res.add(t1);
        }
        return res;
    }

    /**
     * Set集合对象内容复制(作用：防止复制的时候指向同一内存地址)
     * @param list 对象
     * @return 复制后的对象
     */
    public static <T> Set<T> copySetProperties(Set<T> list)
    {
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        Set<T> res = new HashSet<>();
        for(T t:list){
            T t1 = GsonUtils.copyObjectProperties(t);
            res.add(t1);
        }
        return res;
    }
}
