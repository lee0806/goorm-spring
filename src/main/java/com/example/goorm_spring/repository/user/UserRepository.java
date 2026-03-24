package com.example.goorm_spring.repository.user;

import org.springframework.stereotype.Repository;
import com.example.goorm_spring.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository

// JpaRepository를 상속받으면 구현체를 Spring이 자동으로 만들어준다.
// JpaRepository는 2개의 타입을 지정한다. 어떤 엔티티를 사용하는지, PK의 타입이 뭔지
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 찾기
    // 유저가 없을 수도 있기 때문에 Optional을 이용
    Optional<User> findByEmail(String email);
}
