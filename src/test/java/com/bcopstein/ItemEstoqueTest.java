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
    
    @Test
    public void entradaQuantidade(){
        ItemEstoque it= new ItemEstoque(10,5);
        it.entrada(10);
        int esperado= 15;
        int atual = it.getQuantidade();
        Assertions.assertEquals(esperado, atual);
    }

    @Test
    public void entradaQuantidadeInvalida(){
        ItemEstoque it= new ItemEstoque(10,5);
        
        Assertions.assertThrows(SistVendasException.class, ()->{
            it.entrada(-10);
        });
    }

    @Test
    public void saidaQuantidade(){
        ItemEstoque it= new ItemEstoque(10,5);
        it.saida(3);
        int esperado= 2;
        int atual = it.getQuantidade();
        Assertions.assertEquals(esperado, atual);

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