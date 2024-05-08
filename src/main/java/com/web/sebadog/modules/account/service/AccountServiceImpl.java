package com.web.sebadog.modules.account.service;

import com.web.sebadog.infra.mail.EmailMessage;
import com.web.sebadog.infra.mail.EmailService;
import com.web.sebadog.modules.account.Account;
import com.web.sebadog.modules.account.dto.SignUpFormDto;
import com.web.sebadog.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account processNewAccount(SignUpFormDto signUpFormDto) {
        // TODO 비밀번호 암호화
        signUpFormDto.setPassword(passwordEncoder.encode(signUpFormDto.getPassword()));
        Account account = signUpFormDto.toEntity();

        accountRepository.save(account);
        // TODO 인증번호 생성
        account.generateCertificationNumber();
        // TODO 메일 전송
        Context context = new Context();
        context.setVariable("certificationNumber", account.getCertificationNumber());
        String message = templateEngine.process("mail/certification-number-mail.html", context);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("인증번호 메일")
                .message(message)
                .build();
        emailService.send(emailMessage);
        return account;
    }
}
