# Bem vindo a API BookAcar!

Está é uma API em Java (versão 17 ) com Spring Boot e MySQL.
Com ela é possível fazer cadastro de clientes, veículos e reservas.
No momento está em modo de desenvolvimento, e o uso é feito localmente, portando aqui será descrito o funcionamento.
> Fazer o setup do banco de dados book_a_car antes de inicialiar API

## Setup do banco de dados
O banco foi estruturado pelo seguinte [Diagrama de Entidade-Relacionamento (DER).](https://lucid.app/documents/embedded/9c518b34-27a9-442e-bda1-5dee810f5a7e?invitationId=inv_5df06cd9-dab9-4c2f-bdfb-0409ce491f49#)

A API está conectada a um banco de dados, e para que funcione é preciso levantar o servidor do MySQL e prepará-lo. Para não ter que instalar o MySQL na sua máquina, se você tiver o [Docker](https://www.docker.com/) já instalado, pode subir um contêiner.

Digite no terminal:
```bash
docker container run --name container-mysql -e MYSQL_ROOT_PASSWORD=123 -d -p 3306:3306 mysql:8.0.31
```

Após executar essa linha você terá um servidor MySQL rodando na rua máquina. 
Entre no [MySQL Workbench](https://www.mysql.com/products/workbench/) ou a ferramenta que você costuma utilizar para lidar com banco de dados e crie um banco com o nome book_a_car
<details>

<summary>Saiba como conectar com Workbench</summary>

### Conectando o server e banco de dados

1. Na tela inicial em MySQL Connections, adicione uma nova conexão clicando no ícone **+**:![enter image description here](https://www.alura.com.br/artigos/assets/conectando-workbench-mysql-linux/imagem7.jpg)
2. Nas páginas seguintes coloque as informações do server que está rodando no contêiner
   * (Connection name = container-mysql, Hostname = localhost, Port: 3306:3306, Password = 123):
![enter image description here](https://ajuda.hostnet.com.br/wp-content/uploads/2017/11/workbench2.png)

3. Após essa configuração você pode rodar os scritps do banco de dados no [Workbench](https://dev.mysql.com/doc/workbench/en/wb-sql-editor.html), que estão no diretório resourses do projeto, na pasta dataBase: BookACar/bootcamp/src/main/resources/dataBase

4. Com o server rodando e o banco pronto, podemos subir a aplicação. Caso não consiga rodar os scripts, crie um banco com o nome book_a_car, com isso ao subir a API ela se conectará a esse banco e criará as tabelas vazias.
</details>

## Rodando localmente

Instale as dependencias do projeto com o [maven](https://www.marcobehler.com/guides/mvn-clean-install-a-short-guide-to-maven):
```bash

mvn clean install 

```
Obs: é necessário fazer a [instalação do mvn](https://www.hostinger.com.br/tutoriais/install-maven-ubuntu), mas se você estiver usando a IDE [IntelliJ](https://www.jetbrains.com/help/idea/getting-started.html) é só digitar o comando no terminal que ele vai sugerir apertar as teclas ctrl + Enter.

Inicie o servidor:
> Antes de iniciar o servidor realize o setup do banco de dados.
```bash
mvn clean spring-boot:run
```
Obs: se estiver usando a IDE [IntelliJ](https://www.jetbrains.com/help/idea/getting-started.html) é só digitar o comando no terminal que ele vai sugerir apertar as teclas ctrl + Enter.

# Documentação da API
## Documentação em Swagger UI
Após rodar a API você pode acessá-la no Swagger IU através dessa URL: http://localhost:8080/api/swagger-ui/index.html
assim será possivel visualizar e testar o funcionamento de cada endpoint. 
Abaixo uma breve descrição:

Em http://localhost:8080/api/status a mensagem "Server in running", indicando que a API está online.
## Gestão de clientes
* Retorna todos os clientes que estão cadastrados:
http://localhost:8080/api/clients
```http
GET /api/clients
```
* Retorna apenas um cliente através do ID:
```http
GET /api/clients/${clientId}
```
* E para cadastro de clientes:
```http
POST /api/clients/insert
```
A requisição para inserir um cliente deve ser feita com a seguinte estrutura:
> Onde ***name*** e ***email*** são Strings
```json
{"name": "Renata Silveira","email": "renata@outlook.com"}
```
Obs: o clientID é gerado automaticamente

## Gestão de Reservas
* Retorna todas as reservas cadastradas:
http://localhost:8080/api/reservations
```http
GET /api/reservations
```
 * Para cadastro de reservas:
```http
POST /api/reservations
```
A requisição para inserir uma reserva deve ser feita com a seguinte estrutura:
> Onde **start** se refere a data de inicio da reserva e **end** a data final. Ambos devem esta no formato **YYYY-MM-DD**.
```json
{
"start": "2023-11-16",
 "end": "2023-11-20",
 "carId": 1, 
 "clientId": 2
 }
```
Obs: o reservationID é gerado automaticamente


## Gestão de Carros

* Retorna todas os carros cadastrados:
http://localhost:8080/api/cars
```http
GET /api/cars
```
* Para cadastro de carros:
```http
POST /api/cars
```
A requisição para inserir um carro deve ser feita com a seguinte estrutura:
> Onde **model**, **brand**, **color** e **plate** são Strings, **year** do tipo Integer e **available** do tipo boolean
```json
{
"model": "celta",
"brand": "chevrolet",
"color": "preto",
"year": 1998,
"plate": "DFT5874",
"available": false
}
```
> available refere-se a disponibilidade do carro
Obs: o carID é gerado automaticamente
* Retonar apenas um carro:
```http
GET /api/cars/{cardId}
```
* Retornar apenas os carros que estão disponiveis:
```http
GET /api/cars/available
```
* Para remover um carro
```http
DELETE /api/cars/{cardId}
```
Só será possível remover carros que estão disponíveis.

## Relacionados
Se você quiser ver tudo isso funcionando na tela, a API foi integada a um front-end em Angular, você encontra o repositório para clone [AQUI](https://github.com/milena-cordeiro/BookACar-front-end).
Nesse repositório terá um README explicando como clonar o projeto e fazer a instalação. Além de dar uma preview do que esperar dessa plataforma online de gestão de carros para reserva.

## Melhorias a serem feitas
Há melhorias e implementações que foram observadas e que podem ser feitas, que devido ao tempo para entrega deste projeto não foram possíveis. Mas que pretendo aplicá-las futuramente:
* Documentar a API com  [Swagger ui](https://swagger.io/tools/swagger-ui/);
* Disponibilizar no [Railway](https://railway.app/);
* Implementar a verificação dos dados, como por exemplo se a placa do carro está no formato correto ou se o email passado é válido;
* Implementar mais testes e validação de dados;
* Implementar mais endpoints para rotas de clientes, como a de deletar editar, bem como na rota de reservas e carros;
* Implementar rotas de login e autenticação;
* Dockerizar a aplicação com um docker compose que faz todo o setup do ambiente de desenvolvimento com apenas um comando no terminal. 

E Essas são apenas algumas das melhorias que pretendo fazer. Java é uma linguagem que venho aprendendo a pouco tempo, então ainda estou entendendo como algumas coisas funcionam, mas conforme eu for adquirindo mais conhecimento, irei aplica-los para tornar esse e outros projetos melhores.
