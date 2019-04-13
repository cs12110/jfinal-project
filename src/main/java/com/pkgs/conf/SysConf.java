package com.pkgs.conf;

import com.jfinal.config.*;
import com.pkgs.ctrl.MyCtrl;
import com.pkgs.interceptor.ControllerInterceptor;
import com.pkgs.interceptor.ServiceInterceptor;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:04
 * <p>
 * @since 1.0.0
 */
public class SysConf extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {

    }

    @Override
    public void configRoute(Routes routes) {

        routes.add("/my", MyCtrl.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new ControllerInterceptor());
        interceptors.addGlobalServiceInterceptor(new ServiceInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
