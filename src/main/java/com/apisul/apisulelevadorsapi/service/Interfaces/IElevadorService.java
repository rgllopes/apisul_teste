package com.apisul.apisulelevadorsapi.service.Interfaces;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

public interface IElevadorService {

	/** Deve retornar uma List contendo o(s) andar(es) menos utilizado(s). 
	 * @throws Exception*/
	List<Integer> andarMenosUtilizado() throws IOException, ParseException, Exception;

	/** Deve retornar uma List contendo o(s) elevador(es) mais frequentado(s). 
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception*/
	List<Character> elevadorMaisFrequentado() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar uma List contendo o período de maior fluxo de cada um dos
	 * elevadores mais frequentados (se houver mais de um).
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	List<Character> periodoMaiorFluxoElevadorMaisFrequentado() throws IOException, ParseException, Exception;

	/** Deve retornar uma List contendo o(s) elevador(es) menos frequentado(s). 
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception*/
	List<Character> elevadorMenosFrequentado() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar uma List contendo o período de menor fluxo de cada um dos
	 * elevadores menos frequentados (se houver mais de um).
	 * @throws Exception
	 */
	List<Character> periodoMenorFluxoElevadorMenosFrequentado() throws Exception;

	/**
	 * Deve retornar uma List contendo o(s) periodo(s) de maior utilização do
	 * conjunto de elevadores.
	 * @throws Exception
	 */
	List<Character> periodoMaiorUtilizacaoConjuntoElevadores() throws Exception;

	/**
	 * Deve retornar um float (duas casas decimais) contendo o percentual de uso do
	 * elevador A em relação a todos os serviços prestados.
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	float percentualDeUsoElevadorA() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar um float (duas casas decimais) contendo o percentual de uso do
	 * elevador B em relação a todos os serviços prestados.
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	float percentualDeUsoElevadorB() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar um float (duas casas decimais) contendo o percentual de uso do
	 * elevador C em relação a todos os serviços prestados.
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	float percentualDeUsoElevadorC() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar um float (duas casas decimais) contendo o percentual de uso do
	 * elevador D em relação a todos os serviços prestados.
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	float percentualDeUsoElevadorD() throws IOException, ParseException, Exception;

	/**
	 * Deve retornar um float (duas casas decimais) contendo o percentual de uso do
	 * elevador E em relação a todos os serviços prestados.
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	float percentualDeUsoElevadorE() throws IOException, ParseException, Exception;
}
