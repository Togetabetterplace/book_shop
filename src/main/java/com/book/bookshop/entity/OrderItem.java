package com.book.bookshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/31
 * &#064;Description:订单表项
 */
@Data
@TableName(value = "bs_order_item")
@EqualsAndHashCode(callSuper = false)
public class OrderItem extends Model<OrderItem> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer bookId;
    private Integer count;

    //图书对象
    @TableField(exist = false)
    private Book book;
}
