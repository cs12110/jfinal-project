package com.pkgs.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
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

        logger.mark(controller.getClass().getName() + "#" + methodName);

        invocation.invoke();

    }
}
