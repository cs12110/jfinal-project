package com.pkgs.ctrl;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.pkgs.entity.Product;
import com.pkgs.service.ProductService;

import java.util.List;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:07
 * <p>
 * @since 1.0.0
 */
public class MyCtrl extends Controller {

    private ProductService service = Enhancer.enhance(ProductService.class);

    public void list() {
        List<Product> search = service.search();

        renderText(JSON.toJSONString(search));
    }
}
