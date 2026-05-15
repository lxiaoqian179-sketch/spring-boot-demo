# Spring Boot Demo

一個使用 Spring Boot 3 + MyBatis + MySQL + JWT 實作的後端 RESTful API 專案。

## 技術棧

- Java 21
- Spring Boot 3.5
- Spring Security + JWT（jjwt 0.11.5）
- MyBatis + MySQL
- Nginx 反向代理
- 部署於 DigitalOcean Ubuntu 24.04

## 專案功能

- 使用者 CRUD API（GET / POST / PUT / DELETE）
- 訂單查詢 API
- JWT 登入驗證
- API 保護（未登入無法存取）
- 前端登入頁面（index.html）

## API 列表

| 方法 | 路徑 | 說明 | 需要 token |
|------|------|------|-----------|
| POST | /auth/login | 登入取得 token | 否 |
| GET | /users | 取得所有使用者 | 是 |
| GET | /users/{id} | 取得單一使用者 | 是 |
| POST | /users | 新增使用者 | 是 |
| PUT | /users/{id} | 修改使用者 | 是 |
| DELETE | /users/{id} | 刪除使用者 | 是 |
| GET | /orders | 取得所有訂單 | 是 |
| GET | /users/{id}/orders | 取得使用者訂單 | 是 |
| GET | /users?name={name} | 依名字查詢使用者 | 是 |
| GET | /orders?status={status} | 依狀態篩選訂單 | 是 |

## 部署資訊

- 伺服器：DigitalOcean Droplet（Ubuntu 24.04，Singapore）
- 公開 IP：168.144.110.55
- Nginx 反向代理：port 80 → Spring Boot port 8080
- 背景執行：nohup

## 本機開發

```bash
# 啟動專案
./mvnw spring-boot:run

# 打包
./mvnw clean package -DskipTests
```

## 部署步驟

```bash
# 打包
./mvnw clean package -DskipTests

# 上傳到伺服器
scp target/demo-0.0.1-SNAPSHOT.jar root@your-ip:/home/demo/

# 在伺服器啟動
nohup java -jar /home/demo/demo-0.0.1-SNAPSHOT.jar \
  --spring.config.location=file:/home/demo/application.properties \
  > /home/demo/app.log 2>&1 &
```
## 開發流程

本專案採用 Feature Branch 開發流程：

1. 從 `main` 建立 feature branch：`git checkout -b feature/xxx`
2. 開發完成後 commit（使用 Conventional Commits 格式）
3. Push 到遠端並開 Pull Request
4. Code Review 後 merge 進 `main`
5. 刪除已合併的 branch

### Branch 命名規則
- `feature/` → 新功能
- `fix/` → 修 bug
- `refactor/` → 重構
- `docs/` → 文件更新