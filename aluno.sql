CREATE TABLE aluno
(
  id serial NOT NULL,
  nome character varying(100) NOT NULL,
  endereco character varying(128) NOT NULL,
  telefone character varying(20) NOT NULL,
  escolaridade integer NOT NULL,
  CONSTRAINT aluno_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);