package com.amigos.amigosprofsdevcourse.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerUpdateRequest {
    private String name;
    private String email;
    private Integer age;
}
