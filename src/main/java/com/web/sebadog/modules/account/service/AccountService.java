package com.web.sebadog.modules.account.service;

import com.web.sebadog.modules.account.Account;
import com.web.sebadog.modules.account.dto.SignUpFormDto;

public interface AccountService {
    Account processNewAccount(SignUpFormDto signUpFormDto);
}
