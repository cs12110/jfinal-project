package com.pkgs.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.pkgs.conf.SysSetting;
import com.pkgs.util.LogUtil;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:26
 * <p>
 * @since 1.0.0
 */
public class ControllerInterceptor implements Interceptor {

    private static LogUtil logger = new LogUtil(ControllerInterceptor.class);

    @Override
    public void intercept(Invocation invocation) {

        Controller controller = invocation.getController();
        String methodName = invocation.getMethodName();

        String value = controller.getClass().getName() + "#" + methodName;
        logger.mark(value);

        dealWithRedis(value);

        invocation.invoke();

    }

    private void dealWithRedis(String value) {
        Cache cache = Redis.use(SysSetting.JFINAL_REDIS_KEY);

        cache.sadd(SysSetting.CONTROLLER_SET_KEY, value);

        String numStr = "1";
        Object num = cache.hget(SysSetting.SYS_NAME, value);
        if (null != num) {
            numStr = String.valueOf(num);
        }

        cache.hset(SysSetting.SYS_NAME, value, Integer.parseInt(numStr) + 1);

    }
}
