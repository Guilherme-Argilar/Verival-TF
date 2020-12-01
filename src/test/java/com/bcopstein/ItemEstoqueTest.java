package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ItemEstoqueTest{
    
    @ParameterizedTest  
    @CsvSource({
        "10,5,3,8",
        "10,80,50,130",
        "10,9,9,18"
          
    })
    public void entradaQuantidade(int a,int b,int c, int res){
        ItemEstoque it= new ItemEstoque(a,b);
        it.entrada(c);
        int atual = it.getQuantidade();
        Assertions.assertEquals(res, atual);
    }

    @Test
    public void entradaQuantidadeInvalida(){
        ItemEstoque it= new ItemEstoque(10,5);
        
        Assertions.assertThrows(SistVendasException.class, ()->{
            it.entrada(-10);
        });
    }

    @ParameterizedTest  
    @CsvSource({
        "10,5,3,2",
        "10,80,50,30",
        "10,9,9,0"
          
    })
    public void saidaQuantidade(int a,int b,int c, int res){
        ItemEstoque it= new ItemEstoque(a,b);
        it.saida(c);
        int atual = it.getQuantidade();
        Assertions.assertEquals(res, atual);

    }

    @ParameterizedTest  
    @CsvSource({
        "10,5,15",
        "10,5,-10"
          
    })
    public void saidaQuantidadeInsuficiente(int a,int b, int resp){ 
        ItemEstoque it= new ItemEstoque(a,b);
        Assertions.assertThrows(SistVendasException.class, ()->{
            it.saida(resp);
        });
    }

}