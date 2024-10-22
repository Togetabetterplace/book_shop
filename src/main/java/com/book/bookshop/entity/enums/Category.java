package com.book.bookshop.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/13
 * &#064;Description:  图书类型枚举类
 */
@Getter
public enum Category {
    SELECTED(1,"精选图书"),RECOMMEND(2,"推荐图书"),BARGAIN(3,"特价图书");

    Category(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;
    private final String desc;
}
