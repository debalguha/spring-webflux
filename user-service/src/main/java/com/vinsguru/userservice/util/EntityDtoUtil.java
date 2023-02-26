package com.vinsguru.userservice.util;

import com.vinsguru.userservice.dto.TransactionRequestDto;
import com.vinsguru.userservice.dto.TransactionResponseDto;
import com.vinsguru.userservice.dto.TransactionStatus;
import com.vinsguru.userservice.dto.UserDto;
import com.vinsguru.userservice.entity.User;
import com.vinsguru.userservice.entity.UserTransaction;

import java.time.LocalDateTime;

/**
 * @author debal
 */
public class EntityDtoUtil {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getBalance());
    }

    public static User toEntity(UserDto dto) {
        return new User(null, dto.getName(), dto.getBalance());
    }

    public static UserTransaction toEntity(TransactionRequestDto dto) {
        return new UserTransaction(null, dto.getUserId(), dto.getAmount(), LocalDateTime.now());
    }

    public static TransactionResponseDto toDto(TransactionRequestDto transactionRequestDto, TransactionStatus transactionStatus) {
        return new TransactionResponseDto(transactionRequestDto.getUserId(), transactionRequestDto.getAmount(), transactionStatus);
    }

    public static TransactionResponseDto toDto(UserTransaction txn) {
        return new TransactionResponseDto(txn.getUserId(), txn.getAmount(), TransactionStatus.APPROVED);
    }

//    public TransactionResponseDto toDto(UserTransaction userTransaction) {
//        return new TransactionResponseDto(us)
//    }
//
//    public static User fromDto(UserDto dto) {
//        return new User(dto.getId(), dto.getName(), dto.getBalance());
//    }
}
