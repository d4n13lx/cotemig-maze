
# 🐭 Cotemig Maze

> Implementação de um labirinto e sua solução utilizando threads. Uma ferramenta que viabiliza a criação de labirintos com 'N' ratos (threads) em posições aleatórias e um queijo, com um caminho garantido.
## ⚙️ Funcionalidades

O projeto implementa uma simulação de labirinto com os seguintes recursos:

- [x] **Geração Procedural de Labirinto:** O labirinto é criado aleatoriamente usando um algoritmo de Busca em Profundidade (DFS), garantindo que todas as células sejam acessíveis e que exista um caminho até o queijo.

- [x] **Simulação Multi-Thread:** Cada rato (`Rat`) é uma `Thread` independente (`implements Runnable`) que busca o queijo simultaneamente.
- [x] **Algoritmo de Backtracking:** Os ratos usam um algoritmo de *backtracking* para navegar. A lógica impede loops infinitos (o rato não pode voltar imediatamente para a casa anterior) e permite o retrocesso sobre o próprio rastro.
- [x] **Thread-Safety:** Todo o acesso de leitura e escrita ao grid (`blocks[][]`) é sincronizado (`synchronized`) para prevenir *race conditions* e *deadlocks*.
- [x] **Detecção de Vitória:** A detecção de vitória ("olhar antes de pular") e o registro do vencedor (`Winner.java`) são thread-safe, garantindo que apenas o primeiro rato vença.


## 💻 Pré-Requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- [Java Amazon Corretto 24](https://docs.aws.amazon.com/corretto/latest/corretto-24-ug/downloads-list.html) instalado e configurado corretamente no PATH do sistema.

- Variável de ambiente JAVA_HOME apontando para a instalação do Java Corretto 24.

## 🚀 Compilando e Executando

Este é um projeto Java padrão, sem dependências externas.

1. Clone o repositório:
    ```bash
    git clone https://github.com/PedroDressler/cotemig-maze
    ```
    
2. Abra o projeto em sua IDE Java (Eclipse, IntelliJ, VS Code).
3. Execute a classe principal: `Main/Program.java`.


## 👥 Contribuidores

> Colaboradores do projeto: [Pedro Ávila Dressler](https://github.com/PedroDressler), [Daniel Damazo Kazimoto](https://github.com/d4n13lx) & [Matheus Felipe Rodrigues Leitão](https://github.com/Matheusss25)

## 📫 Contribuindo

Para colaborar com o projeto, siga o fluxo abaixo:

1. Faça um fork deste repositório.

2. Clone o fork localmente:
    ```bash
    git clone https://github.com/PedroDressler/cotemig-maze
    ```
3. Crie um novo branch para sua feature ou correção:  
    ```bash
    git checkout -b <nome_do_branch>
    ```


## 📝 Licença

[Amazon Corretto JDK 24](https://aws.amazon.com/pt/corretto/?filtered-posts.sort-by=item.additionalFields.createdDate&filtered-posts.sort-order=desc)

Esse projeto está sob a licença [MIT](https://choosealicense.com/licenses/mit/). Veja o arquivo para mais detalhes.

