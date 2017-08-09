Questão 4) O que é Deadlock?

Deadlock na programação concorrente, é quando vc tem uma thread que precisa ser executada que acaba bloqueando uma outra thread e que essa thread bloqueia a primeira thread, acarretando um deadlock, ou seja nenhuma dessas duas threads serão executadas, pois elas estão se bloqueando numm loop infinito, a resolução desse problema em java seria criar uma sincronização entre as threads para que uma só thread possa ser executada por vez e quando uma estiver sendo executada todas as outras estarão bloqueadas, até uma nova thread passar para o status de execução e bloqueando as outras threads.

Questão 5) Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos
fazer diversas operações de loop, filtros, maps, etc. Porém, existe uma
variação bem interessante do Stream que é ParallelStreams. Descreva com
suas palavras quando qual é a diferença entre os dois e quando devemos
utilizar cada um deles.
A API Stream veio para facilitar o trabalho do programador com a API de coleções, diminuindo linhas de códigos e facilitando a busca, os filtros o mapeamento dos dados, a principal diferença entre Stream e ParallelStreams é que no Stream vc trabalha somente executando um núcleo de processamento, já no segundo objeto dito, você pode executar coleções ou arrays em vários núcleos do processador de forma paralela, fazendo com que o computador utilize seu recurso de hardware de forma mais eficiente.
