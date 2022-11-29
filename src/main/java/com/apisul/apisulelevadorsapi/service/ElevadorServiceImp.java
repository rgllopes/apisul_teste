package com.apisul.apisulelevadorsapi.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.apisul.apisulelevadorsapi.model.Elevador;
import com.apisul.apisulelevadorsapi.service.Interfaces.IElevadorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElevadorServiceImp implements IElevadorService {

        public List<Elevador> JsonRead() throws IOException, ParseException {
                try {
                        String jsonString = String.join(" ",
                                        Files.readAllLines(Paths.get("./input.json"), StandardCharsets.UTF_8));

                        ObjectMapper objectMapper = new ObjectMapper();

                        List<Elevador> result = objectMapper.readValue(jsonString, new TypeReference<List<Elevador>>() {
                        });

                        return result;

                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
        //############################################################################################
        @Override
        public List<Integer> andarMenosUtilizado() throws IOException, ParseException {
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
        public List<Character> elevadorMaisFrequentado() throws IOException, ParseException {
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
        public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() throws IOException, ParseException {
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                //Recupera o elevador mais usado
                List<Character> elevadorMaisUsado = elevadorMaisFrequentado();
                
                String string = elevadorMaisUsado.toString()
                  .substring(1, 3 * elevadorMaisUsado.size() - 1)
                  .replaceAll(", ", "");


                //Separa lista somente com os periodos
                List<Character> periodoList = jsonData.stream()
                                .filter(s-> s.getElevador().equals(string))
                                .map(c -> c.getTurno())
                                .collect(Collectors.toList());
                


                return periodoList;
        }
        //############################################################################################
        @Override
        public List<Character> elevadorMenosFrequentado() throws IOException, ParseException {
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

                // Filtra o primeiro registro para procura outras chaves com o mesmo valor
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
        public List<Character> periodoMenorFluxoElevadorMenosFrequentado() {
                // TODO Auto-generated method stub
                return null;
        }
        //############################################################################################
        @Override
        public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() {
                // TODO Auto-generated method stub
                return null;
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
