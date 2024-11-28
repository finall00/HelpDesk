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
insert into maquina(numeromaquina, sistemaoperacional, idlaboratorio) values (04,'ubuntu',1);


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

create table ticket(
	idticket serial primary key,
	descricao varchar(500) not null,
	imagem text,
	idlaboratorio integer,
	iddocente integer,
	datacriacao date not null,
	dataencerramento date,
	status varchar(1) not null,
	constraint fkLaboratorio foreign key (idlaboratorio) references laboratorio(idlaboratorio),
	constraint fkDocente foreign key (iddocente) references docente(iddocente)
);

select * from ticket; 
insert into ticket (descricao,idlaboratorio,iddocente,datacriacao,dataencerramento,status) values ('o pc morreu aqui',1,1,'2024-02-12','2024-03-23','A');
