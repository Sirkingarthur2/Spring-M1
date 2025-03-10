package com.example.lumberyard.customer;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private Integer memberDurationInMonths;

    public Customer() {
    }

    public Customer(String name, String email, Integer memberDurationInMonths) {
        this.name = name;
        this.email = email;
        this.memberDurationInMonths = memberDurationInMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMemberDurationInMonths() {
        return memberDurationInMonths;
    }

    public void setMemberDurationInMonths(Integer memberDurationInMonths) {
        this.memberDurationInMonths = memberDurationInMonths;
    }
}