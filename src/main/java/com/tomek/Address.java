package com.tomek;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    int idAdresu;
    String ulica;
    String miasto;
    int numerMieszkania;
}
