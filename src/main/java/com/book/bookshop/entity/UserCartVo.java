package com.book.bookshop.entity;

import lombok.Data;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/16
 * &#064;Description:  用户购物车信息展示对象
 */
@Data
public class UserCartVo {
    private Integer num;
    private double totalPrice;
}
