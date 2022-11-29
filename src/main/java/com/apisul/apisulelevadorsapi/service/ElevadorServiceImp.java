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
                // Carrega os dados do arquivo json
                List<Elevador> jsonData = JsonRead();

                // Separa lista somente com os andares
                List<Integer> andarList = jsonData.stream()
                                .map(c -> c.getAndar())
                                .collect(Collectors.toList());

                // Agrupa para contagem de registros por andar
                Map<Integer, Long> countedAndar = andarList.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                // Organiza do maior para o menor
                Map<Integer, Long> sortedAndar = countedAndar.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));

                // Filtra o primeiro registro para procura outras chaves com o mesmo valor
                Optional<Entry<Integer, Long>> quantity = sortedAndar.entrySet().stream().findFirst();

                // Procura se mais de um andar possui o mesmo valor
                List<Integer> mapAndar = sortedAndar.entrySet().stream()
                                .filter(s -> s.getValue() == quantity.get().getValue())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());

                return mapAndar;
        }
        //############################################################################################
        @Override
        public List<Character> elevadorMaisFrequentado() throws IOException, ParseException {
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
                List<Character> mapAndar = sortedElevador.entrySet().stream()
                                .filter(s -> s.getValue() == quantity.get().getValue())
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
                
                return mapAndar;
        }
        //############################################################################################
        @Override
        public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
                // TODO Auto-generated method stub
                return null;
        }
        //############################################################################################
        @Override
        public List<Character> elevadorMenosFrequentado() {
                // TODO Auto-generated method stub
                return null;
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
        public float percentualDeUsoElevadorA() {
                // TODO Auto-generated method stub
                return 0;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorB() {
                // TODO Auto-generated method stub
                return 0;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorC() {
                // TODO Auto-generated method stub
                return 0;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorD() {
                // TODO Auto-generated method stub
                return 0;
        }
        //############################################################################################
        @Override
        public float percentualDeUsoElevadorE() {
                // TODO Auto-generated method stub
                return 0;
        }
}
