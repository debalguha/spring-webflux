package com.vinsguru.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author debal
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
    private int userId;
    private int amount;
}
