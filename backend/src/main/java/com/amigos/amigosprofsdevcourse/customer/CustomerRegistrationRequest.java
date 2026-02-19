package com.amigos.amigosprofsdevcourse.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {
    private String name;
    private String email;
    private Integer age;
    private String country;
}
