package com.book.bookshop.controller;

import com.book.bookshop.entity.Address;
import com.book.bookshop.entity.User;
import com.book.bookshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/11/30
 * &#064;Description:  地址控制器
 */
@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping("/save")
    public String save(Address address, HttpSession session){
        User user = (User) session.getAttribute("user");
        address.setUserId(user.getId());//设定用户
        if(address.getIsDefault() != null && address.getIsDefault().equals("on")){//判定类别(是否为默认收获地址)
            address.setIsDefault("1");
        }
        addressService.save(address);//保存地址
        return "success";
    }
}
