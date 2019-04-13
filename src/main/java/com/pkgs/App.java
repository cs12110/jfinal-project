package com.pkgs;

import com.jfinal.core.JFinal;

/**
 * launch the app
 * <p>
 *
 * @author cs12110 create at 2019-04-13 20:56
 * <p>
 * @since 1.0.0
 */
public class App {

    public static void main(String[] args) {
        JFinal.start("src/webapp/", 8080, "/", 2);
    }
}
