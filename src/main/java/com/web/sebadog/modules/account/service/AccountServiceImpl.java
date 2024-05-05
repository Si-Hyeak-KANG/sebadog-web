package com.web.sebadog.modules.account.service;

import com.web.sebadog.modules.account.Account;
import com.web.sebadog.modules.account.dto.SignUpFormDto;
import com.web.sebadog.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Account processNewAccount(SignUpFormDto signUpFormDto) {
        Account account = signUpFormDto.toEntity();
        return accountRepository.save(account);
    }
}
