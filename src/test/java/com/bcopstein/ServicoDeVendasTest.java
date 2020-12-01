package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalTime;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ServicoDeVendasTest{
    
    @Test
    public void calculaSubtotalTresItens(){

        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);
        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));

        RegraImposto ri= mock(RegraImposto.class);
        FactoryValidacao fv=  mock(FactoryValidacao.class);

        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        int atual =sv.calculaSubtotal(itens);

        
        double esperado= (1000+2000+1500);
        Assertions.assertEquals(esperado,atual);

    }

    @Test
    public void calculaImpostosTresItens(){
        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));

        RegraImposto ri= mock(RegraImposto.class);
        when(ri.calcular(itens)).thenReturn(((1000+2000+1500)*0.1));
        FactoryValidacao fv=  mock(FactoryValidacao.class);


        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        int atual =sv.calculaImpostos(itens);

        
        double esperado= (1000+2000+1500)*0.1;
        Assertions.assertEquals(esperado,atual);

    }


    @Test
    public void calculaPrecoFinalTresItens(){


        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);
        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));

        RegraImposto ri= mock(RegraImposto.class);
        when(ri.calcular(itens)).thenReturn(((1000+2000+1500)*0.1));
        FactoryValidacao fv=  mock(FactoryValidacao.class);

        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        int atual =sv.calculaPrecoFinal(itens);

        
        double esperado= (1000+2000+1500)*0.1+1000+2000+1500;
        Assertions.assertEquals(esperado,atual);

    }

    @Test
    public void VerificaTodosValoresTresItens(){

        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);
        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));

        RegraImposto ri= mock(RegraImposto.class);
        when(ri.calcular(itens)).thenReturn(((1000+2000+1500)*0.1));
        FactoryValidacao fv=  mock(FactoryValidacao.class);

        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        Integer[] atual =sv.todosValores(itens);

        
        int[] esperado= new int[3];
        esperado[0]=1000+2000+1500;
        esperado[1]=(int)((1000+2000+1500)*0.1);
        esperado[2]=esperado[0]+esperado[1];
        Assertions.assertEquals(esperado[0],atual[0]);
        Assertions.assertEquals(esperado[1],atual[1]);
        Assertions.assertEquals(esperado[2],atual[2]);


    }

 
    @Test
    public void verificaValida(){

        Produtos produtos = mock(Produtos.class);
        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",1000.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",2000.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",1500.0));

        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,5));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,3));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));


        RegraImposto ri= mock(RegraImposto.class);
        FactoryValidacao fv=  mock(FactoryValidacao.class);
        ValidacaoHorarioComercial hc = mock(ValidacaoHorarioComercial.class);
        when(fv.getRegraValidacao()).thenReturn(hc);
        doNothing().when(hc).valida(produtos, estoque, itens);
        

        
        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        assertDoesNotThrow(()->sv.valida(itens));

    }

    //---------------------------TESTES DE INTEGRACAO----------------------------
    @Test
    public void integraValidacaoHorarioComercial(){
        Produtos produtos = mock(Produtos.class);
        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",1000.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",2000.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",1500.0));

        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,5));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,3));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));


        RegraImposto ri= mock(RegraImposto.class);
        FactoryValidacao fv= new FactoryValidacao(LocalTime.of(10,40));
        

        
        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        assertDoesNotThrow(()->sv.valida(itens));


    }

    @Test
    public void integraValidacaoForaHorarioComercial(){
        Produtos produtos = mock(Produtos.class);
        when(produtos.recupera(10)).thenReturn(new Produto(10,"Prod10",1000.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30,"Prod30",2000.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50,"Prod15",1500.0));

        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10,5));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30,3));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50,15));

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,1,1000));
        itens.add(new ItemVenda(2,30,1,2000));
        itens.add(new ItemVenda(3,50,1,1500));


        RegraImposto ri= mock(RegraImposto.class);
        FactoryValidacao fv= new FactoryValidacao(LocalTime.of(19,40));
        

        
        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        assertDoesNotThrow(()->sv.valida(itens));


    }

    @Test
    public void integraRegraImpostoOriginal(){
        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);

        List<ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1,10,2,1000));
        itens.add(new ItemVenda(2,30,3,2000));
        itens.add(new ItemVenda(3,50,1,1500));

        RegraImposto ri= new RegraImpostoOriginal();
        FactoryValidacao fv=  mock(FactoryValidacao.class);


        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        int atual =sv.calculaImpostos(itens);

        
        double esperado= (1000+2000+1500)*0.1;
        Assertions.assertEquals(esperado,atual);

    }

    public void integraRegraImpostoGrandes(){
        Produtos produtos=mock(Produtos.class);
        Estoque estoque =mock(Estoque.class);

        List<ItemVenda> itens = new ArrayList<>(4);
        itens.add(new ItemVenda(1,10,1,1000));
        itens.add(new ItemVenda(2,30,1,2000));
        itens.add(new ItemVenda(3,50,3,1500));
        itens.add(new ItemVenda(4,60,2,3000));
        

        RegraImposto ri= new RegraImpostoComprasGrandes();
        FactoryValidacao fv=  mock(FactoryValidacao.class);


        ServicoDeVendas sv= new ServicoDeVendas(produtos,estoque,ri,fv);
        int atual =sv.calculaImpostos(itens);

        double esperado= 50+200+150+300;
        Assertions.assertEquals(esperado,atual);

    }

    
}