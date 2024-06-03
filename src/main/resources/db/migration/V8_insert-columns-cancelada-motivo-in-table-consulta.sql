alter table consulta add column cancelada tinyint,
add column motivo varchar(100);
update consulta set cancelada = 0;