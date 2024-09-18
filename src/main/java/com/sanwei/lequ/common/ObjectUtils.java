package com.sanwei.lequ.common;

import java.lang.reflect.Field;

/**
 * @Author：SatanCY
 * @Date：2024/9/17 14:47
 */
public class ObjectUtils {

    public static boolean isObjectExceptIdNull(Object obj) {
        if (obj == null) {
            return false;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean idFound = false;
        boolean allOtherNull = true;
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if ("id".equals(field.getName())) {
                    idFound = true;
                    continue;
                }
                if (field.get(obj) != null) {
                    allOtherNull = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                // Handle exception appropriately
                e.printStackTrace();
            }
        }
        return idFound && allOtherNull;
    }
}

