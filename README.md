# TOCO FOME

Toco Fome é um sistema desenvolvido duranta as aulas de Desenvolvimento de Software Cliente/Servidor. 

O sistema possui uma tela de login e recupera do banco de dados uma lista de pratos e suas informações (nome, descrição, preço). Para incluir novos pratos, é necessário usar a [versão de administrador](https://github.com/pablohsz/TocoFome_Admin) do programa.

Para executar o programa será necessário:

- Intellij IDEA 2022.3.3;
- Oracle OpenJDK version 11.0.12;
- PostgreSQL na versão 15.2;
- As tabelas items e users criada em um database (script de criação das tabelas no final da lista);
- Substituir no arquivo `ConnectionFactory.java` o nome do database onde foram criadas as tabelas items e users;
- Um arquivo `config.properties` localizado na pasta `resources` contendo o username e a senha para acesso ao banco de dados, conforme mostrado abaixo:
```
db.username=**seu username aqui**  
db.password=**sua senha aqui**
```

Scripts de criação das tabelas:
```
CREATE TABLE IF NOT EXISTS public.items
(
    id bigint NOT NULL DEFAULT nextval('items_id_seq'::regclass),
    product character varying(70) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    image_url text COLLATE pg_catalog."default",
    price numeric(10,2) NOT NULL,
    CONSTRAINT products_primarykey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.users
(
    username character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_primarykey PRIMARY KEY (username)
)
```

Desenvolvido por Pablo Souza
\
\
*Escola Politécnica*
\
**Pontifícia Universidade Católica de Goiás**
