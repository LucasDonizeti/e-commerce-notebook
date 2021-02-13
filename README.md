# e-commerce-notebook
Prejeto de Laboratório de Engenharia de Software e trabalho de graduação

Configuração:
<!--ts-->
**Variáveis de ambiente*
  ```
  LES_DB -> url do banco
  LES_DB_USER -> username de acesso ao banco
  LES_DB_PASSWORD -> senha de acesso ao banco
  ```
  ex:
  ```
  LES_DB=jdbc:mysql://localhost:3306/les_db;
  LES_DB_USER=les_db_user;
  LES_DB_PASSWORD=les_db_password;
  ```
**configuração banco*
```
  create user 'les_db_user'@'localhost' identified by 'les_db_password';
  create database les_db;
  GRANT ALL PRIVILEGES ON les_db.* TO 'les_db_user'@'localhost';
 ```
obs: tabelas são geradas automaticamente pelo Hibernate.
<!--te-->
Lucas Donizeti

[![](https://img.shields.io/badge/github-%23100000.svg?&style=for-the-badge&logo=github&logoColor=white)](https://github.com/LucasDonizeti)[![](https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ldon/)
