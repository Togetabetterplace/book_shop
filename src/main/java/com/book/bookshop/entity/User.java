package com.book.bookshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/18
 * &#064;Description:  用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "bs_user")
public class User extends Model<User> {
//    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String company;
}
