package com.apisul.apisulelevadorsapi.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apisul.apisulelevadorsapi.model.Elevador;
import com.apisul.apisulelevadorsapi.service.Interfaces.IElevadorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElevadorServiceImp implements IElevadorService {

        public List<Elevador> JsonRead() throws Exception {
                try {
                        String jsonString = String.join(" ",
                                        Files.readAllLines(Paths.get("./input.json"), StandardCharsets.UTF_8));

                        ObjectMapper objectMapper = new ObjectMapper();

                        List<Elevador> result = objectMapper.readValue(jsonString, new TypeReference<List<Elevador>>() {
                        });

                        return result;

                } catch (IOException e) {
                        throw new Exception("Não foi possível ler o arquivo input.json: " + e);
                }
        }
        //############################################################################################
        @Override
        public List<Integer> andarMenosUtilizado() throws Exception {
                //Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Separa lista somente com os andares
                List<Integer> andarList = jsonData.stream()
                                .map(c -> c.getAndar())
                                .collect(Collectors.toList());

                //Agrupa para contagem de registros por andar
                Map<Integer, Long> countedAndar = andarList.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                //Organiza do maior para o menor
                Map<Integer, Long> sortedAndar = countedAndar.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                //Filtra o primeiro registro para procura outras chaves com o mesmo valor
                Optional<Entry<Integer, Long>> quantity = sortedAndar.entrySet().stream().findFirst();

                //Procura se mais de um andar possui o mesmo valor
                List<Integer> listAndar = sortedAndar.entrySet().stream()
                                .filter(s -> s.getValue() == quantity.get().getValue())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());

                return listAndar;
        }
        //############################################################################################
        @Override
        public List<Character> elevadorMaisFrequentado() throws Exception {
                //Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Separa lista somente com os elevadores
                List<Character> elevadorList = jsonData.stream()
                                .map(c -> c.getElevador())
                                .collect(Collectors.toList());

                //Agrupa para contagem de registros por elevador
                Map<Character, Long> countedAndar = elevadorList.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                //Organiza do maior para o menor
                Map<Character, Long> sortedElevador = countedAndar.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, 
                        LinkedHashMap::new));

                //Filtra o ultimo registro para procura outras chaves com o mesmo valor
                Entry<Character, Long> quantity = sortedElevador.entrySet().stream().reduce((one, two) -> two).get();

                //Procura se mais de um elevador possui o mesmo valor
                List<Character> listElevador = sortedElevador.entrySet().stream()
                                .filter(s -> s.getValue() == quantity.getValue())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
                
                return listElevador;
        }
        //############################################################################################
        @Override
        public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() throws Exception {
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Recupera elevador menos frequentado
                List<Character> elevadorMaisUsado = elevadorMaisFrequentado();

                //Converte a lista recuperada em 1 elemento de character para consulta
                Character quantity = elevadorMaisUsado.get(0);

                //Vezes em que elevador foi utilizado
                List<Character> list = jsonData.stream()
                        .filter(p -> p.getElevador() == quantity)
                        .map(c -> c.getTurno())
                        .collect(Collectors.toList());

                //Agrupa para contagem de registros por periodo
                Map<Character, Long> countedPeriodo = list.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                //Organiza do menor para o maior
                Map<Character, Long> sortedPeriodo = countedPeriodo.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                //Recupera primeiro elemento do map organizado em ordem crescente
                Character periodo = sortedPeriodo.keySet().stream().findFirst().get();

                //Converte elemento para formato de resposta
                List<Character> result = new ArrayList<>();
                result.add(periodo); 
                
                return result; 
        }
        //############################################################################################
        @Override
        public List<Character> elevadorMenosFrequentado() throws Exception {
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                // Separa lista somente com os elevadores
                List<Character> elevadorList = jsonData.stream()
                                .map(c -> c.getElevador())
                                .collect(Collectors.toList());

                // Agrupa para contagem de registros por elevador
                Map<Character, Long> countedAndar = elevadorList.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                // Organiza do maior para o menor
                Map<Character, Long> sortedElevador = countedAndar.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                //Filtra o primeiro registro para procura outras chaves com o mesmo valor
                Optional<Entry<Character, Long>> quantity = sortedElevador.entrySet().stream().findFirst();

                // Procura se mais de um elevador possui o mesmo valor
                List<Character> listElevador = sortedElevador.entrySet().stream()
                                .filter(s -> s.getValue() == quantity.get().getValue())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
                
                return listElevador;
        }
        //############################################################################################
        @Override
        public List<Character> periodoMenorFluxoElevadorMenosFrequentado() throws Exception {
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Recupera elevador menos frequentado
                List<Character> elevadorMenosUsado = elevadorMenosFrequentado();

                //Converte a lista recuperada em 1 elemento de character para consulta
                Character quantity = elevadorMenosUsado.get(0);

                //Vezes em que elevador foi utilizado
                List<Character> list = jsonData.stream()
                        .filter(p -> p.getElevador() == quantity)
                        .map(c -> c.getTurno())
                        .collect(Collectors.toList());

                //Agrupa para contagem de registros por periodo
                Map<Character, Long> countedPeriodo = list.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                //Organiza do menor para o maior
                Map<Character, Long> sortedPeriodo = countedPeriodo.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                //Recupera primeiro elemento do map organizado em ordem crescente
                Character periodo = sortedPeriodo.keySet().stream().findFirst().get();

                //Converte elemento para formato de resposta
                List<Character> result = new ArrayList<>();
                result.add(periodo); 
                
                return result;
        }
        //############################################################################################
        @Override
        public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() throws Exception {
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Recupera elevador mais frequentado
                List<Character> elevadorMaisUsado = elevadorMaisFrequentado();

                //Converte a lista recuperada em 1 elemento de character para consulta
                Character quantity = elevadorMaisUsado.get(0);

                //Vezes em que elevador foi utilizado
                List<Character> list = jsonData.stream()
                        .filter(p -> p.getElevador() == quantity)
                        .map(c -> c.getTurno())
                        .collect(Collectors.toList());

                //Agrupa para contagem de registros por periodo
                Map<Character, Long> countedPeriodo = list.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                //Organiza do maior para o menor
                Map<Character, Long> sortedPeriodo = countedPeriodo.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                //Recupera primeiro elemento do map organizado em ordem decrescente
                Character periodo = sortedPeriodo.keySet().stream().findFirst().get();

                //Converte elemento para formato de resposta
                List<Character> result = new ArrayList<>();
                result.add(periodo); 
                
                return result;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorA() throws Exception {

                float result = percentualDeUso('A');

                return result;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorB() throws Exception {
                float result = percentualDeUso('B');

                return result;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorC() throws Exception {
                float result = percentualDeUso('C');

                return result;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorD() throws Exception {
                float result = percentualDeUso('D');

                return result;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorE() throws Exception {
                float result = percentualDeUso('E');

                return result;
        }
        //############################################################################################
        public float percentualDeUso(Character elevador) throws Exception{
                try {
                        // Carrega os dados do arquivo json
                        List<Elevador> jsonData = JsonRead();

                        //Número total de registros
                        float totalSize = jsonData.size();

                        //Vezes em que elevador A foi utilizado
                        List<Elevador> list = jsonData.stream().filter(p -> p.getElevador() == elevador).collect(Collectors.toList());
                        float elevadorSize = list.size();

                        //Calcula % de uso
                        float result = (elevadorSize / totalSize) * 100;

                        return result;        
                } catch (Exception e) {
                        throw new Exception("Não foi possível realizar o cálculo solicitado. Dados inconsistentes: " + e);
                }                
        }
}