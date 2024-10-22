package com.book.bookshop.entity;

import lombok.Data;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/13
 * &#064;Description:购物车信息明细表
 */
@Data
public class CartVo {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer count;
    private String bookName;
    private String imgUrl;
    private double newPrice;
}
