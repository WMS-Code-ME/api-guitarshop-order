# api-guitarshop-order

![Java](https://img.shields.io/badge/java-version%2021-blue)
![Qurkus](https://img.shields.io/badge/quarkus-version%203.17.8-blueviolet)
![Maven Central](https://img.shields.io/badge/maven-version%203.9.9-green)
![lombok](https://img.shields.io/badge/lombok-version%201.18.30-orange)

### API criada para o trabalho final do Bootcamp de Arquitetura de Software da XPe - XP Educação.

A solução aborda a criação de uma API Rest de uma loja on-line (e-commerce).

Para melhor ilustrar a o desenvolvimento, a API foi criada como se fosse em um contexto de uma loja de guitarras custom shop, uma linha muito específica.
O foco aqui é mostrar o contexto de pedidos desta loja de forma bem simples e objetiva. Alguns fatores foram abstraídos deste contexto como, autenticação, relacionamentos com formas de pagamentos, contexto de cliente e produtos, entre outros detalhes que são importantes.

### Tecnologias e versões utilizadas:
- Apache Maven 3.9.9;
- Java 21.0.4 2024-07-16;
- Docker version 27.5.1;
- Quarkus Framework 3.18.1;

### Instruções para executar a aplicação:

- Build e instação das dependências:

    ```shell script
    mvn clean install -U
    ```
- Subir o banco de dados no docker:

    ```shell script
    docker run -ti --rm -p 27017:27017 mongo:4.4
    ```
    - **Atenção**: Após subir o banco de dados no docker, certifique de criar o banco de dados com o nome ***"guitar-shop-order-db"*** e a coleção a ser usada deverá se chamar ***"orders"***

- Executar a aplicação em ambiente local:

    ```shell script
    ./mvnw quarkus:dev
    ```

### Acessar aos endpoints após execução local:
- Ao subir a aplicação acesse no seu navagador o endereço:

    ```shell script
    http://localhost:4001/order/swagger
    ```
- Acesse a documentação no [Swagger Editor](https://editor-next.swagger.io/) pelo arquivo:

    ```shell script
    src/main/resources/openapi.yaml
    ```
### Documentação técnica:
- Acesse os [diagramas](docs/diagrams.md) do C4 Model.
