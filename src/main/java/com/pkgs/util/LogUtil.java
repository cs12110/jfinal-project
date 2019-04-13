package com.pkgs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:27
 * <p>
 * @since 1.0.0
 */
public class LogUtil {


    private Class<?> clazz;


    public LogUtil(Class<?> clazz) {
        this.clazz = clazz;
    }


    private static Supplier<String> dateSupplier = () -> {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(new Date());
    };

    /**
     * display log
     *
     * @param log log
     */
    public void mark(String log) {
        String name = clazz.getName();

        System.out.println(dateSupplier.get() + " " + name + " - " + log);
    }
}
