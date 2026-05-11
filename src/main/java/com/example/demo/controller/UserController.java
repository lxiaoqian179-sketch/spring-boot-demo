package com.example.demo.controller;
//宣告這個檔案放在哪個資料夾（package），就像檔案的「地址」。

import com.example.demo.model.User;
import com.example.demo.service.UserService;//剛才寫的 Service
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;//處理 GET 請求的標籤

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {//告訴 Spring Boot「這個類別負責接收 HTTP 請求」。

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
//    一樣是依賴注入，Controller 需要用到 Service，請 Spring Boot 幫忙傳進來。

    @GetMapping//有人請求 GET /users 時，執行這個函式
    public List<User> getUsers() {
         return userService.getAllUsers(); //呼叫 userService.getAllUsers() 拿資料，把清單回傳給使用者
    }

    @GetMapping("/{id}")//用 id 查詢單筆資料
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "新增成功";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "刪除成功";
    }
}