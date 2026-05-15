package com.example.demo.controller;
//宣告這個檔案放在哪個資料夾（package），就像檔案的「地址」。


import com.example.demo.common.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;//剛才寫的 Service
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    // branch A 的修改git
    @GetMapping
//    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
//        return ResponseEntity.ok(ApiResponse.success(userService.getAllUsers()));
//    }

        public ResponseEntity<ApiResponse<Object>> getUsers(
                @RequestParam(required = false) String name) {
            if (name != null) {
                User user = userService.findByName(name);
                return ResponseEntity.ok(ApiResponse.success(user));
            }
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(ApiResponse.success(users));
        }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(404).body(ApiResponse.notFound("使用者不存在"));
        }
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(201).body(ApiResponse.created("新增成功"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok(ApiResponse.success("修改成功"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("刪除成功"));
    }
}