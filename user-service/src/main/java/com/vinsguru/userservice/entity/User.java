package com.vinsguru.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author debal
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Table("users")
public class User {
    @Id
    private Integer id;
    private String name;
    private int balance;
}
