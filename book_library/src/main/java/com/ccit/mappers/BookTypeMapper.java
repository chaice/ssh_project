package com.ccit.mappers;


import com.ccit.pojo.BookType;

import java.util.List;

public interface BookTypeMapper {
    List<BookType> findByBookTypeAll();
    BookType findByBookType(String booktype);
}
