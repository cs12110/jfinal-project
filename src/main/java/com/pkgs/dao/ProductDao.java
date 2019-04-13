package com.pkgs.dao;

import com.pkgs.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:34
 * <p>
 * @since 1.0.0
 */
public class ProductDao {


    public List<Product> getList(){
        List<Product> list = new ArrayList<>(5);

        for(int index=0; index<5;index++){
            Product p = new Product();
            p.setId(index);
            p.setName("p"+index);

            list.add(p);
        }
        return list;
    }
}
