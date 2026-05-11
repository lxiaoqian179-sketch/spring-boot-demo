package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//「這個類別是用來處理 HTTP 請求的，而且回傳的內容直接當作回應內容（通常是文字或 JSON）」
//@Controller → 這個類別負責接收請求
//@ResponseBody → 回傳值直接送給使用者，不經過畫面渲染
public class   HelloController {

    @GetMapping("/hello")
    public String hello(){
        return  "你好，世界";
    }
}
//「有人打開 localhost:8080/hello，我就回給他一段文字 你好，世界」


