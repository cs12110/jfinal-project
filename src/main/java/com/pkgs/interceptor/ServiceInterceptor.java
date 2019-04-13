package com.pkgs.interceptor;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.pkgs.conf.SysSetting;
import com.pkgs.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:26
 * <p>
 * @since 1.0.0
 */
public class ServiceInterceptor implements Interceptor {

    private static LogUtil logger = new LogUtil(ServiceInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {

        Object target = invocation.getTarget();
        String methodName = invocation.getMethodName();

        String key = formatClassName(target.getClass()) + "#" + methodName;

        logger.mark(key);

        dealWithRedis(key);
        getRedisValue();

        invocation.invoke();
    }

    /**
     * format the class name
     *
     * @param clazz class
     * @return string
     */
    private static String formatClassName(Class<?> clazz) {
        if (null == clazz) {
            return "N/A";
        }

        String clazzName = clazz.getName();
        int bound = clazzName.indexOf("$$EnhancerByCGLIB$$");

        // if create by cglib
        if (bound != -1) {
            return clazzName.substring(0, bound);
        }

        return clazzName;
    }

    private void dealWithRedis(String value) {
        Cache cache = Redis.use(SysSetting.JFINAL_REDIS_KEY);

        cache.sadd(SysSetting.SERVICE_SET_KEY, value);

        String numStr = "1";
        Object num = cache.hget(SysSetting.SYS_NAME, value);
        if (null != num) {
            numStr = String.valueOf(num);
        }

        cache.hset(SysSetting.SYS_NAME, value, Integer.parseInt(numStr) + 1);

    }

    private void getRedisValue() {
        Cache cache = Redis.use(SysSetting.JFINAL_REDIS_KEY);
        Set<?> members = cache.smembers(SysSetting.SERVICE_SET_KEY);

        Map<String, String> map = new HashMap<>();
        for (Object each : members) {
            Object value = cache.hget(SysSetting.SYS_NAME, String.valueOf(each));
            map.put(String.valueOf(each), String.valueOf(value));
        }

        logger.mark(JSON.toJSONString(map, true));
    }
}
