package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class FactoryValidacaoTest{
    
    @ParameterizedTest
    @CsvSource({
        "10,40,class com.bcopstein.ValidacaoHorarioComercial",
        "8,01,class com.bcopstein.ValidacaoHorarioComercial",
        "17,59,class com.bcopstein.ValidacaoHorarioComercial",
        "11,40,class com.bcopstein.ValidacaoHorarioComercial",
        "17,00,class com.bcopstein.ValidacaoHorarioComercial",
        "15,40,class com.bcopstein.ValidacaoHorarioComercial",
        "18,40,class com.bcopstein.ValidacaoForaHorarioComercial",
        "18,00,class com.bcopstein.ValidacaoForaHorarioComercial",
        "8,00,class com.bcopstein.ValidacaoForaHorarioComercial",
        "00,00,class com.bcopstein.ValidacaoForaHorarioComercial",
    })
    public void validaHorarios(int h,int m,String s){
       LocalTime t= LocalTime.of(h,m);
       FactoryValidacao fc=new FactoryValidacao(t);
       RegraValidacao regra = fc.getRegraValidacao();
       String s1 = ""+regra.getClass();
       Assertions.assertEquals(s1,s);
    }
}