package com.web.sebadog.modules.account.service;

import com.web.sebadog.modules.account.dto.CertificationNumberDto;
import com.web.sebadog.modules.account.dto.SignUpFormDto;

public interface AccountService {
    void processNewAccount(SignUpFormDto signUpFormDto);

    boolean checkCertificationNumber(CertificationNumberDto certificationNumberDto);
}
