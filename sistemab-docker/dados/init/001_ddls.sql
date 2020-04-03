/*Use o schema Criado */
create schema "sistemab"


/*
Resumo: Criação da Tabela Usuário
DevBy:Carlos Roberto
Version:1.0
*/
CREATE SEQUENCE sistemab.usuario_seq;

CREATE TABLE sistemab.tb_usuario(
  id int not null default nextval('sistemab.usuario_seq'),
  nome varchar(100) not null,
  usuario varchar(100) not null,
  senha varchar(20) not null,
  ativo boolean not null default true,
  PRIMARY KEY (id)
);