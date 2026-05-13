//package com.example.demo.repository;
////宣告這個檔案放在哪個資料夾（package），就像檔案的「地址」。
//
//import org.springframework.stereotype.Repository;//Spring Boot 的標籤
//import java.util.List;//Java 內建的清單資料結構
//
//@Repository// 告訴 Spring Boot「這個類別負責跟資料來源溝通」
//public class UserRepository {//→ 定義一個叫 UserRepository 的類別
//
//    public List<String> findAll() {
//        //先用假資料，之後會換成真的資料庫
//        //定義一個函式：List<String> → 回傳一個「字串的清單」
//        //findAll → 函式名稱，意思是「找全部」
//        return List.of("Alice", "Bob", "Charlie");
//        //回傳一個包含三個名字的假資料清單。
//    }
//}
////用一句話總結
////「UserRepository 是一個負責取得資料的類別，現在先回傳假資料，之後會換成真實的資料庫查詢。」


package com.example.demo.repository;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }
//    用id查詢單筆 findById


    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Integer id) {
        userMapper.delete(id);
    }


    public Optional<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
//這層是橋樑，Service 不直接呼叫 Mapper，而是透過 Repository：

//注入 UserMapper
//呼叫 userMapper.findAll() 去執行 SQL
//把結果回傳給 Service

//用一句話說：「把 Mapper 包起來，讓 Service 透過它取得資料」
