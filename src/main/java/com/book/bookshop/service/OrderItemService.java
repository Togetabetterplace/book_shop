package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.OrderItem;
import com.book.bookshop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/14
 * &#064;Description:
 */
@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper,OrderItem> {
}
