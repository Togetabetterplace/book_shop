package com.book.bookshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.book.bookshop.entity.enums.Category;
import com.book.bookshop.entity.enums.Suit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/13
 * &#064;Description:  图书实体类
 */
@Data
@TableName(value = "bs_book")
@EqualsAndHashCode(callSuper = false)
public class Book extends Model<Book> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String isbn;
    private String name;
    private String author;
    private String publisher;
    private Date publishDate;
    private double oldPrice;
    private double newPrice;
    private String authorLoc;
    private Suit suit;
    private Category category;
    private String info;
    private String imgUrl;
}
