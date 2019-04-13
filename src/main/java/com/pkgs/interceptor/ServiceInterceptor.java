package com.pkgs.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.pkgs.util.LogUtil;

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

        logger.mark(formatClassName(target.getClass()) + "#" + methodName);

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
}
