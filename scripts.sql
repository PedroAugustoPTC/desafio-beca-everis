create table tb_funcionario (salario double precision not null, id bigint not null, primary key (id)) engine=InnoDB;
create table tb_pessoa (id bigint not null auto_increment, cpf varchar(255), email varchar(255), nome varchar(255), telefone varchar(255), primary key (id)) engine=InnoDB;
create table tb_ticket (id bigint not null auto_increment, horario_entrada datetime, horario_saida datetime, valor_total double precision, vaga_id bigint not null, veiculo_id bigint not null, primary key (id)) engine=InnoDB;
create table tb_vaga (id bigint not null auto_increment, numero_vaga integer not null, ocupada bit not null, primary key (id)) engine=InnoDB;
create table tb_veiculo (id bigint not null auto_increment, placa varchar(255), proprietario_id bigint, primary key (id)) engine=InnoDB;
alter table tb_funcionario add constraint FKiy4h70ee4j8ne08g6cynv446k foreign key (id) references tb_pessoa (id);
alter table tb_ticket add constraint FKmt32eqh3worh9cy7llnuseblp foreign key (vaga_id) references tb_vaga (id);
alter table tb_ticket add constraint FKsc5t8kcgvweof0te18n3y8sbq foreign key (veiculo_id) references tb_veiculo (id);
alter table tb_veiculo add constraint FK4s71l1nqg6k3aafl9s3taobw4 foreign key (proprietario_id) references tb_pessoa (id);