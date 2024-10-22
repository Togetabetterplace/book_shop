package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.*;
import com.book.bookshop.mapper.OrderMapper;
import com.book.bookshop.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单服务类
 *
 * @Author:  zichentian
 * @Date:  2023/12/5
 * @Description: 提供订单相关的业务逻辑实现
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CartService cartService;

    /**
     * 购买商品生成订单
     * @param cartVos 购物车中的商品列表
     * @param addrId 地址ID
     * @param session 当前会话的 HttpSession 对象
     * @return 操作结果，成功返回 "success"
     */
    public String buy(List<CartVo> cartVos, Integer addrId, HttpSession session) {
        // 1.生成订单表记录
        Order order = new Order();
        order.setAddressId(addrId);
        User user = (User) session.getAttribute("user");//获取user
        order.setUserId(user.getId());
        order.setCreateDate(new Date());
        order.setOrderNum(OrderUtils.createOrderNum()); // 使用自定义方法生成订单编号
        order.setOrderStatus("1");
        orderMapper.insert(order);//插入order记录

        // 2.生成订单明细表记录
        List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> cartIds = new ArrayList<>();
        for (CartVo cart : cartVos) {//遍历cart类，保存明细表
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cart.getBookId());
            orderItem.setCount(cart.getCount());
            orderItem.setOrderId(order.getId());
            orderItems.add(orderItem);
            cartIds.add(cart.getId());
        }
        orderItemService.saveBatch(orderItems);

        // 3.删除购物车表中记录
        cartService.removeByIds(cartIds);
        return "success";
    }

    /**
     * 查询用户的订单列表
     * @param userId 用户ID
     * @param orderQueryVo 订单查询条件
     * @return 包含订单信息的列表
     */
    public List<Order> findUserOrder(Integer userId, OrderQueryVo orderQueryVo) {
        //设定查询条件
        Integer begin = (orderQueryVo.getPage() - 1) * orderQueryVo.getPageSize();
        Integer end = orderQueryVo.getPage() * orderQueryVo.getPageSize();
        //确定页数
        orderQueryVo.setBegin(begin);
        orderQueryVo.setEnd(end);
        orderQueryVo.setUserId(userId);
        List<Order> list = orderMapper.findOrderAndOrderDetailListByUser(orderQueryVo);//找到订单信息列表
        for (Order order : list) {
            List<OrderItem> items = order.getOrderItems();//明细
            double price = 0.0;
            for (OrderItem item : items) {
                price += item.getCount() * item.getBook().getNewPrice();
            }
            order.setTotalPrice(price); // 计算订单总金额
        }
        return list;
    }

    /**
     * 查询用户订单的总页数
     * @param userId 用户ID
     * @param orderQueryVo 订单查询条件
     * @return 总页数
     */
    public Integer findUserOrderPages(Integer userId, OrderQueryVo orderQueryVo) {
        orderQueryVo.setUserId(userId);
        int count = orderMapper.findOrderCountByUser(orderQueryVo);
        return (count - 1) / orderQueryVo.getPageSize() + 1;
    }
}
