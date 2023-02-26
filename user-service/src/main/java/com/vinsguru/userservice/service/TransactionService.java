package com.vinsguru.userservice.service;

import com.vinsguru.userservice.dto.TransactionRequestDto;
import com.vinsguru.userservice.dto.TransactionResponseDto;
import com.vinsguru.userservice.dto.TransactionStatus;
import com.vinsguru.userservice.entity.UserTransaction;
import com.vinsguru.userservice.repository.UserRepository;
import com.vinsguru.userservice.repository.UserTransactionRepository;
import com.vinsguru.userservice.util.EntityDtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author debal
 */
@Service
@Slf4j
public class TransactionService {
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto transactionRequestDto) {
        log.info("Proceeding for transaction!! {}", transactionRequestDto);
        return userRepository.updateUserBalance(transactionRequestDto.getUserId(), transactionRequestDto.getAmount())
                .doOnNext(bool -> log.info("Transaction status:: {}", bool))
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(transactionRequestDto))
                .flatMap(userTransactionRepository::save)
                .map(txn -> EntityDtoUtil.toDto(transactionRequestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(transactionRequestDto, TransactionStatus.DECLINED));

    }

    public Flux<TransactionResponseDto> getByUserId(int userId){
        return userTransactionRepository
                .findByUserId(userId)
                .map(EntityDtoUtil::toDto);
    }
}
