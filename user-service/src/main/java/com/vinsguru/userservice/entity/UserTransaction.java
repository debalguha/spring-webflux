package com.vinsguru.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * @author debal
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
