package com.vinsguru.orderservice.dto;

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
public class UserDto {
    private int id;
    private String name;
    private int balance;


}
