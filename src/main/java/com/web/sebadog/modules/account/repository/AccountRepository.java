package com.web.sebadog.modules.account.repository;

import com.web.sebadog.modules.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    Optional<Account> findByEmail(String email);
    Optional<Account> findByNickname(String nickname);
}
