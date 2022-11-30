# apisul_teste
Avaliação de construção de api para controle de fluxo de elevadores

## DESCRITIVO TÉCNICO

Suponha que a administração do prédio 99a da Tecnopuc, com 16 andares e cinco elevadores, denominados A, B, C, D e E, nos convidou a aperfeiçoar o sistema de controle dos elevadores. Depois de realizado um levantamento no qual cada usuário respondia:\

a. O elevador que utiliza com mais frequência (A, B, C, D ou E);\
b. O andar ao qual se dirigia (0 a 15);\
c. O período que utilizava o elevador – M: Matutino; V: Vespertino; N: Noturno.

Considerando que este possa evoluir para um sistema dinâmico, escreva o código que nos ajude a extrair as seguintes informações:\

a. Qual é o andar menos utilizado pelos usuários;\
b. Qual é o elevador mais frequentado e o período que se encontra maior fluxo;\
c. Qual é o elevador menos frequentado e o período que se encontra menor fluxo;\
d. Qual o período de maior utilização do conjunto de elevadores;\
e. Qual o percentual de uso de cada elevador com relação a todos os serviços prestados;

Deve ser programado em Java ou C#.\

Para a realização do exercício você deve implementar a interface IElevadorService.

Faça a leitura do arquivo input.json para ter acesso às entradas.

### API desenvolvida em java 11

** projeto encontra-se na branch develop conforme gitflow recommendations **

### Endpoints disponíveis
1) /andar-menos-utilizado
2) /elevador-mais-utilizado
3) /periodo-maior-fluxo-elevador-mais-frequentado
4) /elevador-menos-frequentado
5) /periodo-menor-fluxo-elevador-menos-frequentado
6) /periodo-maior-utilizacao-conjunto-elevadores
7) /percentual-de-uso-elevador-a
8) /percentual-de-uso-elevador-b
9) /percentual-de-uso-elevador-c
10) /percentual-de-uso-elevador-d
11) /percentual-de-uso-elevador-e
 
Data de entrega 29 de novembro de 2022
