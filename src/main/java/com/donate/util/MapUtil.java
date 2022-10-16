package com.donate.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {
  public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
    Map<String, Object> map = new HashMap<String,Object>();
    Class<?> clazz = obj.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      String fieldName = field.getName();
      Object value = field.get(obj)==null?"":field.get(obj).toString();
      map.put(fieldName, value);
    }
    return map;
  }
}
