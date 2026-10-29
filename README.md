# Gás Fácil Web

Sistema acadêmico de gestão de beneficiários, estoque de botijões e retiradas, agora em versão **web**. A interface abre no navegador, em `http://localhost:8080`.

## Tecnologias
- Java 17+
- Spring Boot, Spring MVC e Thymeleaf
- MySQL 8+
- HTML e CSS responsivos

## Como executar
1. Instale o JDK 17 ou superior, Maven e MySQL Server.
2. No MySQL Workbench, execute todo o arquivo `sql/gasfacil.sql` para criar o banco `gasfacil` e suas tabelas.
3. Abra `src/main/resources/application.properties` e confira usuário e senha do MySQL. Por padrão, o usuário é `root` e a senha está vazia. Você pode configurar variáveis de ambiente `DB_USER`, `DB_PASSWORD` e `DB_URL`.
4. Abra um terminal na pasta do projeto e execute:
   ```bash
   mvn spring-boot:run
   ```
5. Acesse `http://localhost:8080` no navegador.

## Funcionalidades
- Painel com resumo dos registros.
- CRUD de beneficiários: cadastrar, listar, editar e excluir.
- CRUD de botijões: cadastrar, listar, editar e excluir.
- Retiradas: registrar, consultar histórico e excluir.
- Interface adaptável a celular e computador.

## Arquitetura e padrões
- **MVC:** `controller` recebe requisições, `model`/dados representam o domínio e templates Thymeleaf formam a visualização.
- **DAO:** classes de acesso a dados isolam SQL e persistência.
- **Factory:** `DAOFactory` centraliza a criação dos DAOs.
- **Strategy:** `RegraBeneficio`/`RegraPadrao` encapsulam a regra de elegibilidade por renda.
- O `JdbcTemplate` é gerenciado pelo Spring, que mantém o pool de conexões da aplicação.

## Observações
- Esta versão é uma aplicação web, não Java Swing. O navegador acessa a aplicação que roda localmente.
- A retirada diminui o estoque do botijão selecionado. O ambiente deve estar com o MySQL ativo e o banco inicializado antes de usar os cadastros.
