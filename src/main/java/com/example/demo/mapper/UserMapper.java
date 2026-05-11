package com.example.demo.mapper;
//UserMapper.java（介面層）
//這是 MyBatis 的介面，只定義「要做什麼」，不寫實作內容。

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

//@Mapper → 告訴 Spring Boot 這是 MyBatis 的 Mapper，自動處理實作
//findAll() → 宣告一個查詢全部資料的方法
@Mapper
public interface UserMapper {
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void delete(Integer id);
    User findById(Integer id);//「查單筆」的 API
}

//用一句話說：「定義要對資料庫做什麼操作」