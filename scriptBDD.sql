CREATE DATABASE IF NOT EXISTS mscliente;
CREATE DATABASE IF NOT EXISTS mscuenta;
GRANT ALL PRIVILEGES ON mscliente.* TO 'admin'@'%';
GRANT ALL PRIVILEGES ON mscuenta.* TO 'admin'@'%';
FLUSH PRIVILEGES;

use mscliente;

create table if not exists persona
(
    edad           int          null,
    id             int auto_increment
        primary key,
    direccion      varchar(150) null,
    genero         varchar(50) null,
    identificacion varchar(20) null,
    nombre         varchar(100) null,
    telefono       varchar(15) null,
    constraint UKr5vsms84ih2viwd6tatk9o5pq
        unique (identificacion)
);

create table if not exists cliente
(
    id          int          not null
        primary key,
    contrasenia varchar(255) null,
    estado      varchar(255) null,
    constraint FKkpvkbjg32bso6riqge70hwcel
        foreign key (id) references persona (id)
);

use mscuenta;
create table if not exists cuenta
(
    cliente_id         int          null,
    id_cuenta          int auto_increment
        primary key,
    numero_cuenta      int          not null,
    saldo_inicial      double       not null,
    fecha_modificacion datetime(6)  null,
    estado             varchar(1) not null,
    ip_actualizacion   varchar(50) null,
    tipo_cuenta        varchar(50) not null,
    fecha_creacion     datetime(6)  null
);

create table if not exists movimientos
(
    id_cuenta          int          null,
    id_movimiento      int auto_increment
        primary key,
    saldo_inicial      double       null,
    valor              double       null,
    fecha_creacion     datetime(6)  null,
    fecha_modificacion datetime(6)  null,
    ip_actualizacion   varchar(50) null,
    tipo_movimiento    varchar(20) null,
    saldo_disponible   double       null,
    constraint FKc6lma9bsbqj5l8bhnie8l60o7
        foreign key (id_cuenta) references cuenta (id_cuenta)
);

