# Projeto de Gestão de Tarefas

## 1. Introdução

Um cliente requisitou o desenvolvimento de uma aplicação Java focada na gestão eficiente de tarefas. O objetivo principal é criar uma solução robusta que permita a criação, listagem, deleção e busca de tarefas. Essa especificação visa atender à demanda do cliente por um sistema que garanta a fácil identificação de tarafas a se fazer.

## 2. Objetivos da Aplicação

- **Criação de Tarefas**: Permitir que o usuário adicione novas tarefas ao sistema, com cada tarefa tendo um identificador único, titulo, status e descrição.
- **Listagem de Tarefas**: Facilitar a visualização de todos as tarefas disponíveis no sistema. Além disso, deve permitir que o usuário filtre tarefas pelo status dela.
- **Busca de Tarefa por Id:**: Oferecer uma funcionalidade de busca para localizar rapidamente tarefas pelo ID.
- **Deleção de Tarefa por Id**: Oferecer uma funcionalidade de deleção de tarefa por id.

## 3. Estrutura Proposta

A aplicação será estruturada em várias partes, cada uma com sua responsabilidade específica:

1. **Entidade de Tarefa (`TasktEntity`)**: Definirá a estrutura de dados para as tarefas.
2. **Controlador de Tarefas (`TasktController`)**: Gerenciará a fluxo de requisição do usuário, entrada e saída de dados.
3. **Serviço de Tarefas (`TaskService`)** Gerenciará a lógica da aplicação com as operações de criação, leitura, deleção e busca de tarefa.
4. **Repositório de Tarefas (`TaskRepository`)** Gerenciará os métodos do ORM do JPA.
5. **DTO para criação de Tarefas (`TaskCreateDTO`)** Gerenciará como os dados devem ser recebidos pela aplicação.
6. **Exceções Personalizadas**: Incluirá exceções customizadas para tratar situações específicas como preço negativo e produtos não encontrados.

## 4. Especificações das Exceções Personalizadas:

Nessa aplicação teremos duas exceções personalizadas, sendo ela **`NotFoundException`** e deve herdar de **`ApiException`**. Deve conter dois construtores (overloading), sendo um que não recebe parâmetro e define a mensagem de erro como **`"Not found."`** e um que recebe a mensagem de erro por parâmetro.

## 5. Sugestão de Arquitetura

- 📂 src
  - // subpastas
  - 📂 gestao_de_tarefas
    - 📄 GestaoDeTarefasApplication.java
    - 📂 task
      - 📂 dtos
        - 📄 TaskCreateDTO.java
      - 📄 TaskController.java
      - 📄 TaskEntity.java
      - 📄 TaskRepository.java
      - 📄 TaskService.java
  - 📂 exceptions
    - 📂 customExceptions
      - 📄 NotFoundException.java
    - 📂 dtos
      - 📄 ErrorMessageDTO.java
    - 📄 GlobalExceptionHandler.java
  - 📂 configurations
    - 📄 GlobalConfig.java

## 6. Especificações de `TaskEntity`:

### 6.1. Atributos:

Todos os atributos de `TaskEntity` devem ser **privados**. Utilize o Lombok para definir todos os `getters` e `setters` automaticamente. Todos os campos são obrigatórios.

- **id**: Chave primária da entidade.
  - **Tipo de dado**: `UUID`.
- **title**: Título da tarefa. Este campo é obrigatório.
  - **Tipo de dado**: `String`.
- **status**: Representa o status atual da tarefa. Este campo é obrigatório.
  - **Tipo de dado**: `String`.
- **description**: Representa a descrição da tarefa. Este campo é obrigatório.
  - **Tipo de dado**: `String`.

## 7. Especificações de `Rotas`:

- **`Rota de criação (POST):`**:

  - **Endpoint**: /api/tasks.
  - **Regra**: Deve criar uma nova tarefa no banco de dados. O corpo da requisição deve ser validado retornando mensagem caso alguma das regras de **`TaskEntity`** seja violada.

  **`Rota de leitura (GET):`**

  - **Endpoint**: /api/tasks.
  - **Regra**: Deve listar todas as terefas existentes no banco de dados. Essa rota recebe um parâmetro de busca (query parameter) **`status`**. Caso o parâmetro de busca **`status`** seja enviado, deve retornar todas as tarefas que contém o mesmo status recebido.
  - **Mensagem de erro**: Deve ser um objeto contendo a chave **`"error"`** e o valor **`"Task not found."`**.

  **`Rota de deleção (DELETE):`**

  - **Endpoint**: /api/tasks/{taskId}.
  - **Regra**: Deve deletar uma tarefa por id recebido pelo parâmetros de rota (path parameters). Caso o id buscado não exista no banco de dados, deve retornar uma mensagem de erro e o status code **`404 NOT FOUND`**.
  - **Mensagem de erro**: Deve ser um objeto contendo a chave **`"error"`** e o valor **`"Task not found."`**.
