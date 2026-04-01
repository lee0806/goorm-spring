# goorm-spring

Spring Boot 학습을 위한 스터디 레포지토리입니다.
REST API 설계, JPA를 통한 데이터 접근, AOP, 트랜잭션 관리까지 단계적으로 구현하며 학습했습니다.

---

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| ORM | Spring Data JPA / Hibernate |
| Database | PostgreSQL 16 |
| AOP | Spring AOP (AspectJ) |
| 기타 | Lombok, Docker |

---

## 프로젝트 구조

```
src/main/java/com/example/goorm_spring/
├── Application.java
├── aop/
│   └── LoggingAspect.java         
├── controller/
│   ├── hello/
│   │   └── HelloController.java    # 기본 요청/응답 실습
│   ├── user/
│   │   └── UserController.java     # 회원 CRUD REST API
│   └── cafeorder/
│       └── OrderController.java    # 카페 주문 (DI 실습)
├── service/
│   ├── EmailService.java
│   ├── user/
│   │   └── UserService.java
│   └── cafeorder/
│       ├── CoffeeService.java
│       ├── BreadService.java
│       └── OrderService.java
├── repository/
│   ├── user/
│   │   └── UserRepository.java     # JpaRepository 상속
│   └── cafeorder/
│       ├── CoffeeRepository.java 
│       └── BreadRepository.java
├── entity/
│   └── user/
│       └── User.java               # JPA 엔티티
└── dto/
    └── user/
        ├── UserRequestDto.java
        └── UserResponseDto.java
```

---

## 학습 흐름 및 구현 내용

### 1단계 — 웹 페이지와 REST API 엔드포인트 구현

`HelloController`를 통해 Spring MVC의 기본 동작 방식을 학습했습니다.

- `@RestController`, `@GetMapping` 사용법
- `@RequestParam`으로 쿼리 파라미터 받기
- Java record를 활용한 응답 객체 반환

```
GET /hello              → "Hello, World!"
GET /hello/params?name= → "Hello, {name}!"
GET /hello/info         → { name, status } JSON 응답
```

---

### 2단계 — UserController 구현 (계층 구조 이해)

Controller → Service → Repository 계층 구조를 직접 설계하며 의존성 주입(DI)을 학습했습니다.

- `@RequiredArgsConstructor` + `final` 필드를 이용한 생성자 주입
- `OrderService`에서 `CoffeeService`, `BreadService`를 주입받아 다중 빈 의존 관계 실습

```
GET /order?item={item}  → 커피 또는 빵 주문 처리
```

---

### 3단계 — 회원 CRUD 구현 (JPA + REST API)

Spring Data JPA를 활용한 회원 관리 API를 구현했습니다.

#### 엔드포인트

| Method | URL | 설명 |
|--------|-----|------|
| GET | `/users` | 전체 회원 조회 |
| GET | `/users/{id}` | ID로 회원 조회 |
| GET | `/users/email?email=` | 이메일로 회원 조회 |
| POST | `/users` | 신규 회원 등록 (201 Created) |
| PUT | `/users/{id}` | 회원 정보 수정 |
| DELETE | `/users/{id}` | 회원 삭제 (204 No Content) |
| POST | `/users/rollback-test` | 트랜잭션 롤백 동작 확인용 |

#### 요청/응답 예시

```json
// POST /users
{
  "name": "홍길동",
  "email": "hong@example.com"
}

// 응답
{
  "id": 1,
  "name": "홍길동",
  "email": "hong@example.com"
}
```

#### 학습 포인트

- `@Entity`, `@Table`, `@Column`으로 JPA 엔티티 매핑
- `JpaRepository<User, Long>` 상속으로 기본 CRUD 자동 제공
- `findByEmail(String email)` 메서드 네이밍 쿼리
- `UserResponseDto.from(User user)` 정적 팩토리 메서드로 변환 로직 캡슐화

---

### 4단계 — 트랜잭션의 이해

`@Transactional`의 동작 원리와 롤백 조건을 실습했습니다.

- `update()`, `delete()` 메서드에 `@Transactional` 적용
- `saveRollback()` 메서드: 저장 후 의도적으로 `RuntimeException`을 발생시켜 자동 롤백 확인
- `POST /users/rollback-test` 엔드포인트로 롤백 동작을 직접 HTTP 요청으로 검증

---

### 5단계 — AOP의 이해

`LoggingAspect`를 통해 AOP(관점 지향 프로그래밍)의 핵심 개념을 학습했습니다.

- `@Aspect`, `@Component`로 Aspect 클래스 등록
- `@Around` 어드바이스로 메서드 실행 전/후를 모두 제어
- `execution(* com.example.goorm_spring.service..*.*(..))` 포인트컷으로 서비스 레이어 전체에 적용
- `ProceedingJoinPoint`로 실제 메서드 호출 후 실행시간(ms) 측정 및 로깅

```
[시작] UserService.findAll()
[완료] UserService.findAll() 실행시간: 23ms
```

비즈니스 로직 코드에 손대지 않고 횡단 관심사(로깅)를 분리할 수 있음을 확인했습니다.

---

