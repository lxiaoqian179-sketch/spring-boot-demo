package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;//剛才寫的那個類別，這裡要用到它
import org.springframework.stereotype.Service;//Spring Boot 的標籤
import java.util.List;//清單資料結構
import java.util.Optional;

@Service//告訴 Spring Boot「這個類別負責處理商業邏輯」
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    這三行是依賴注入，是 Spring Boot 很重要的概念：
//    「UserService 需要用到 UserRepository，但不自己建立它，而是請 Spring Boot 幫忙傳進來」
//    就像廚師不自己去倉庫拿食材，而是讓助理送過來。

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }
    //用id查詢單筆資料


    public void createUser(User user) {
        userRepository.insert(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    // UserService.java 加這個方法
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public User findByName(String name) {
//        return userMapper.findByName(name);
//    }

    public User findByName(String name) {
        return userRepository.findByName(name);
        }
}
//定義一個函式：
//呼叫 userRepository.findAll() 去拿資料
//把結果直接回傳出去

