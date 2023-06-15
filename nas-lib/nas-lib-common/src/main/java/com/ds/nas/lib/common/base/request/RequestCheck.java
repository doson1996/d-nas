package com.ds.nas.lib.common.base.request;

import cn.hutool.core.util.NumberUtil;
import com.ds.nas.lib.common.base.annotation.Check;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.ResultMsg;
import com.ds.nas.lib.common.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author ds
 * @date 2023/1/22
 * @description 入参校验
 */
public class RequestCheck {

    /**
     * 参数校验
     *
     * @param request
     */
    public static void check(BaseRequest request) {
        String msg = ResultMsg.PARAMETER_ERROR_MSG;
        if (request == null)
            throw new BusinessException(msg);
        try {
            Class<? extends BaseRequest> clazz = request.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(request);
                Check check = declaredField.getDeclaredAnnotation(Check.class);
                if (check != null) {
                    // 判断是否必输
                    if (check.require()) {
                        msg = StringUtils.isNotBlank(check.msg()) ? check.msg() : declaredField.getName() + "字段为必输!";
                        // 如果为null，直接抛异常
                        if (value == null)
                            throw new BusinessException(msg);
                        // 如果为字符串的情况
                        if (value instanceof String && StringUtils.isBlank((String) value))
                            throw new BusinessException(msg);
                    }

                    // 判断最大长度，maxLen大于0才进行校验
                    if (check.maxLen() > 0) {
                        msg = StringUtils.isNotBlank(check.msg()) ? check.msg() : declaredField.getName() + "字段最大长度为" + check.maxLen() + "!";
                        // 如果为字符串的情况
                        if (value instanceof String && StringUtils.length((String) value) > check.maxLen())
                            throw new BusinessException(msg);
                    }

                    // 判断是否只能为数字(只针对字符串)
                    if (check.onlyNumber()) {
                        msg = StringUtils.isNotBlank(check.msg()) ? check.msg() : declaredField.getName() + "字段请传入数字!";
                        if (value instanceof String && !NumberUtil.isNumber((String) value))
                            throw new BusinessException(msg);
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException(msg);
        }
    }

}
