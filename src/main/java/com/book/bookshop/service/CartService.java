package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.Cart;
import com.book.bookshop.entity.CartVo;
import com.book.bookshop.entity.UserCartVo;
import com.book.bookshop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 购物车服务类
 *
 * &#064;Author:   zichentian
 * &#064;Date:   2023/12/14
 * &#064;Description:  提供购物车相关的业务逻辑实现
 */
@Service
public class CartService extends ServiceImpl<CartMapper,Cart> {
    @Autowired
    private CartMapper cartMapper;

    /**
     * 根据用户ID查询购物车记录
     * @param userId 用户ID
     * @return 包含购物车信息的列表
     */
    public List<CartVo> findCartByUser(Integer userId){
        return cartMapper.findCartListByUserId(userId);
    }

    /**
     * 根据购物车ID查询对应的记录
     * @param ids 购物车ID，多个ID以逗号分隔
     * @return 包含购物车信息的列表
     */
    public List<CartVo> findCartByIds(String ids){
        return cartMapper.findCartListByIds(Arrays.asList(ids));
    }

    /**
     * 统计当前用户购物车的总计
     * @param list 包含购物车信息的列表
     * @return 购物车的总计金额
     */
    public double getCartItemTotal(List<CartVo> list){
        double sum = 0.0;
        for (CartVo cart: list) {
            sum += cart.getCount() * cart.getNewPrice();
        }
        return sum;
    }

    /**
     * 批量删除购物车记录
     * @param ids 购物车ID，多个ID以逗号分隔
     * @return 操作结果，成功返回 "success"
     */
    public String batchDelete(String ids){
        if(ids != null){
            String[] idArray = ids.split(",");
            cartMapper.deleteBatchIds(Arrays.asList(idArray));
        }
        return "success";
    }

    /**
     * 包装用户购物车信息数据
     * @param list 包含购物车信息的列表
     * @return 包装后的用户购物车信息对象
     */
    public UserCartVo wrapperCart(List<CartVo> list){
        UserCartVo userCartVo = new UserCartVo();
        userCartVo.setNum(list.size());
        userCartVo.setTotalPrice(getCartItemTotal(list));
        return userCartVo;
    }
}
