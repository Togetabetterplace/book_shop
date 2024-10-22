package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.Book;
import com.book.bookshop.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/9
 * &#064;Description:  图书业务层
 */
@Service
public class BookService extends ServiceImpl<BookMapper,Book> {
}
