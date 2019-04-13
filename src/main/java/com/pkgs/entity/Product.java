package com.pkgs.entity;

/**
 * <p>
 *
 * @author cs12110 create at 2019-04-13 21:35
 * <p>
 * @since 1.0.0
 */
public class Product {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
