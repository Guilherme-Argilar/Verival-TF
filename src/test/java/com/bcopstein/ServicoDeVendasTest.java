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


public class ServicoDeVendasTest{
    
    @Test
    public void calculaSubtotal(){

        List<ItemVenda> itens = new ArrayList<>(4);
        itens.add(new ItemVenda(1,10,1,1000));
        itens.add(new ItemVenda(2,30,1,2000));
        itens.add(new ItemVenda(3,50,3,1500));
        itens.add(new ItemVenda(4,60,2,3000));
        

        RegraImposto rg= new RegraImpostoOriginal();
        double atual =rg.calcular(itens);
        double esperado= (1000+2000+1500+3000)*0.1;
        Assertions.assertEquals(esperado,atual);

    }
}