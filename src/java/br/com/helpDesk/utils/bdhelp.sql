create table laboratorio(
    idlaboratorio serial primary key,
    numeroSala int not null,
    qtnMaquinas int not null,
    status varchar(1) not null
    
);

create table maquina(
    idmaquina serial primary key,
    numeroMaquina  int,
    sistemaOperacional varchar(30),
    idlaboratorio int not null, 
    constraint  fk_laboratorio foreign key (idlaboratorio) references laboratorio(idlaboratorio)
    
);


insert into laboratorio(numeroSala, qtnMaquinas, status) values (10, 5, 'A');
insert into maquina(numeromaquina, sistemaoperacional, idlaboratorio) values (?,?,?);


create table pessoa(
	idPessoa serial primary key,
	nomePessoa varchar(30) not null,
	contato varchar(100) not null
);

insert into pessoa(nomePessoa,contato) values ('Reginaldo','teste@gmail.com');
select * from pessoa;

create table docente(
	idDocente serial primary key,
	numeroMatricula int not null,
	situacao varchar(1) not null,
	fkPessoa integer unique,
	constraint fk_pessoa foreign key (fkPessoa) references pessoa(idPessoa)
);

insert into docente(numeroMatricula,situacao,fkPessoa) values (10293939,'A',1);
select * from docente;