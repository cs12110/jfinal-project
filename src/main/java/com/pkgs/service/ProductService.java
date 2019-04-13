package com.pkgs.service;

import com.jfinal.aop.Enhancer;
import com.pkgs.dao.OrderDao;
import com.pkgs.dao.ProductDao;
import com.pkgs.entity.Product;

import java.util.List;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:37
 * <p>
 * @since 1.0.0
 */
public class ProductService {

    /**
     * Just like the ioc in spring
     */
    private ProductDao productDao = Enhancer.enhance(ProductDao.class);
    private OrderDao orderDao = Enhancer.enhance(OrderDao.class);

    public List<Product> search() {
        orderDao.say();
        return productDao.getList();
    }
}
