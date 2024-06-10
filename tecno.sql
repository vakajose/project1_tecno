create table usuario(
    id serial primary key ,
	"user" varchar(500) not null,
	pass text not null ,
	correo varchar(500) not null,
	nombre varchar(500) not null,
	telefono integer not null ,
	tipo varchar(50) not null
);


create table producto(
	id serial primary key ,
	nombre varchar(500) not null,
	codigo varchar(250) not null,
	precio numeric not null,
	costo numeric not null,
	cantidad integer not null
);

create table inventario(
	id serial primary key ,
	numero varchar(250) not null,
	fecha date not null ,
	idusuario integer not null ,
	foreign key (idusuario) references usuario(id)
);

create table ingresoegreso(
	idinventario integer not null,
	idproducto integer not null,
	cantidad integer not null,
	costo numeric not null,
	tipo varchar(250) not null ,
	primary key(idinventario,idproducto) ,
	foreign key(idinventario) references inventario(id),
	foreign key(idproducto) references producto(id)
);

create table promocion(
	id serial primary key,
	fechai date not null,
	fechaf date not null,
	porcentaje integer not null,
	idproducto integer ,
	foreign key(idproducto) references producto(id)
);

create table venta(
	id serial primary key,
	numero varchar(250) not null,
	fecha date not null,
	total numeric not null,
	idusuario integer not null
);

create table detalleventa(
	idventa integer not null,
	idproducto integer not null,
	cantidad integer not null,
	precio numeric not null,
	foreign key(idventa) references venta(id),
	foreign key(idproducto) references producto(id)
);

create table pago(
	id serial primary key,
	fecha date not null ,
	total numeric not null, 
	idventa integer not null,
	foreign key(idventa) references venta(id)
);

create table reserva(
	id serial primary key,
	numero varchar(250) not null,
	fecha date not null,
	total numeric not null,
	idusuario integer not null
);

create table detallereserva(
    idreserva integer not null,
	idproducto integer not null,
	cantidad integer not null,
	precio numeric not null,
	foreign key(idreserva) references reserva(id),
	foreign key(idproducto) references producto(id)
);




