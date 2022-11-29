package com.apisul.apisulelevadorsapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elevador implements Serializable {

    private Integer andar;
    private Character elevador;
    private Character turno;
    
}
