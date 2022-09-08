# Golden-Raspberry-Awards
API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards

### Pré-requisitos

* Java: 18.0.2.1 [Download](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
* Maven: 3.8.1 (ou superior) [Download](https://maven.apache.org/download.cgi) 

### Automação
Ao dar o start na aplicação, serão realizados os seguintes procedimentos de forma automática:
1. Criação da base H2 goldenraspberryawardsdb (desenvolvimento) ou testdb (testes)
2. Execução do script _src/main/resources/data.sql_ para criação dos usuário padrão usado na autenticação da API.
3. Leitura dos registros contidos no arquivo _src/main/resources/movielist (5).csv
4. Gravação dos registros na base H2 (Entidades: movie, producer, studio) 
5. Gravação dos registros na base H2 (Entidades: award) 

### Como rodar aplicação
* Importar o projeto dentro da IDE como "Projeto Maven"
* Rodar o comando ```mvn install -Dmaven.test.skip=true```
* A classe main do projeto é a src/main/java/com/luucasor/goldenraspberryawards/GoldenRaspberryAwardsApplication.java

### Como usar a aplicação
A aplicação disponibiliza os seguintes endpoints:

http://localhost:8080/api/auth/register -> Permite o registro de um novo usuário para a realizar a autenticação na aplicação.<br>
http://localhost:8080/api/auth -> Realiza o login do usuário dentro da API<br>
http://localhost:8080/api/producer/minAndMaxAwardTimeGap -> Obtém o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido<br>
Mais detalhes, com a aplicação iniciada, acesse: http://localhost:8080/swagger-ui/index.html

### Autenticação
A aplicação utiliza a autenticação por Bearer token.
Este token deverá ser passado no header de toda requisição (exceto /api/auth e /api/auth/register);

#### Passo 1
Após iniciar a aplicação, será necessário realizar o login, a partir do endpoint `/api/auth`, basta passar no corpo da mensagem
o json com `email` e `password`. Ex:

```
{
  "email": "luucasor@gmail.com",
  "password": "Qwe##123"
}
```
O retorno dessa requisição deve ser um código token. Ex:
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjI2MTg0NDIsImV4cCI6MTY2MjYyNTY0MiwidXNlcklkIjoxLCJlbWFpbCI6Imx1dWNhc29yQGdtYWlsLmNvbSIsImZpcnN0TmFtZSI6Ikx1Y2FzIiwibGFzdE5hbWUiOiJPcnRpZ2FyYSJ9.MujhCgW17L2n5yE86dfnyO37RbHDTYrDyvecsOZYfJ8"
}
```

#### Passo 2
Copie o conteúdo retornado no campo "token" do json de autenticação.

#### Passo 3
Para fazer a busca dos produtores com maior/menor intervalo entre dois prêmios consecutivos será necessário chamar o endpoint `/api/producer/minAndMaxAwardTimeGap`
passando o token no header da requisição. Ex:
```
Authentication: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjI2MTg0NDIsImV4cCI6MTY2MjYyNTY0MiwidXNlcklkIjoxLCJlbWFpbCI6Imx1dWNhc29yQGdtYWlsLmNvbSIsImZpcnN0TmFtZSI6Ikx1Y2FzIiwibGFzdE5hbWUiOiJPcnRpZ2FyYSJ9.MujhCgW17L2n5yE86dfnyO37RbHDTYrDyvecsOZYfJ8
```

### Testes de Integração
A aplicação o diretório `src/test/java/com/luucasor/goldenraspberryawards/integration` onde ficam salvos todos os testes de integração.
Para rodar os testes de integração, basta abrir a classe desejada, clicar com o botão direito sobre o método e rodar o teste.

`AuthControllerTest.java` -> Testes de autenticação<br>
`ProducerControllerTest.java` -> Testes do endpoint para busca dos produtores com maior/menor intervalo entre prêmios.<br>

### Links
* API: http://localhost:8080/api
* Swagger: http://localhost:8080/swagger-ui/index.html
