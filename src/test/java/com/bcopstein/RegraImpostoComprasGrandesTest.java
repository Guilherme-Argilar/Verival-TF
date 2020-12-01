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


public class RegraImpostoComprasGrandesTest{
    
    @Test
    public void verificacalcularQuatroItens(){

        List<ItemVenda> itens = new ArrayList<>(4);
        itens.add(new ItemVenda(1,10,1,1000));
        itens.add(new ItemVenda(2,30,1,2000));
        itens.add(new ItemVenda(3,50,3,1500));
        itens.add(new ItemVenda(4,60,2,3000));
        

        RegraImposto rg= new RegraImpostoComprasGrandes();
        double atual =rg.calcular(itens);
        double esperado= 50+200+150+300;
        Assertions.assertEquals(esperado,atual);

    }

    @Test
    public void verificacalcularDoisItens(){

        List<ItemVenda> itens = new ArrayList<>(2);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,4,2000));

        

        RegraImposto rg= new RegraImpostoComprasGrandes();
        double atual =rg.calcular(itens);
        double esperado= 100+200;
        Assertions.assertEquals(esperado,atual);

    }
}