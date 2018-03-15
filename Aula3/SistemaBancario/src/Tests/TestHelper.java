package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestHelper {

	public static Conta criaContaDoRicardo(){
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		Agencia agenciaUFSC = bb.criarAgencia("Agencia UFSC");
		Conta contaDoRicardo = agenciaUFSC.criarConta("Ricardo");
		return contaDoRicardo;
	}
	
	public static Conta criaContaDoMaria(){
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		Agencia agenciaUFSC = bb.criarAgencia("Agencia UFSC");
		Conta contaDaMaria = agenciaUFSC.criarConta("Maria");
		return contaDaMaria;
	}
	
	public static Dinheiro criaDezReais(){
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		return dezReais;
	}
	
	public static Dinheiro criaDezDolares(){
		Dinheiro dezDolares = new Dinheiro(Moeda.USD, 10, 0);
		return dezDolares;
	}

	public static ValorMonetario criaVinteReais(){
		ValorMonetario vinteReaisEmValorMonetario = new ValorMonetario(Moeda.BRL).somar(new Dinheiro(Moeda.BRL, 20, 0));
		return vinteReaisEmValorMonetario;
	}
}
