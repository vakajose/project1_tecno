create or replace function usuario_registrar(puser varchar,ppass varchar,pcorreo varchar,pnombre varchar,ptelefono integer,ptipo varchar,padmin varchar,ppassadmin varchar)
returns integer as
$$
declare 
	idu integer ;
begin
   if (select count(*) from usuario u where u.user = puser or u.correo = pcorreo  ) <= 0 then
		if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin ) > 0 then
			insert into usuario("user",pass,correo,nombre,telefono,tipo) values (puser,ppass,pcorreo,pnombre,ptelefono,ptipo) returning id into idu;
			return idu;
		end if;
	return  -1; 
	end if;
	return 0;
end;
$$
language plpgsql;

create or replace function usuario_listar(puser varchar , ppass varchar)
returns table(id integer,"user" varchar,"pass" text,correo varchar,nombre varchar,telefono integer,tipo varchar) as
$$
begin
   if (select u.tipo from usuario u where u.user = puser and u.pass = ppass ) = 'Administrador' then
      return query (
		 select u.id, u.user,u.pass,u.correo,u.nombre,u.telefono,u.tipo from usuario u order by id desc
	  );
	else
	return query (
		 select u.id,u.user,u.pass,u.correo,u.nombre,u.telefono,u.tipo from usuario u where u.user = puser and u.pass =ppass order by id desc
	  );
  end if;
end;
$$
language plpgsql;


create or replace function usuario_modificar(pid integer,puser varchar,ppass varchar,pcorreo varchar,pnombre varchar,ptelefono integer,ptipo varchar,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
    if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
		update usuario set "user" =puser , pass  = ppass , correo =pcorreo ,nombre = pnombre , telefono = ptelefono , tipo = ptipo
	   where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

create or replace function usuario_eliminar(pid integer,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
   if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
	   delete from usuario where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

---------------------------------------producto ----------------------------------------

create or replace function producto_registrar(pnombre varchar,pcodigo varchar,pprecio numeric,pcosto numeric,pcantidad numeric,padmin varchar,ppassadmin varchar)
returns integer as
$$
declare
	idp integer;
begin
	 if (select count(*) from producto p where p.nombre = pnombre or p.codigo = pcodigo  ) <= 0 then
		if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin ) > 0 then
			insert into producto(nombre,codigo,precio,costo,cantidad) values(pnombre,pcodigo,pprecio,pcosto,pcantidad) returning id into idp;
       return idp;
		end if;
	return  -1; 
	end if;
	return 0;
end;
$$
language plpgsql;

create or replace function producto_listar(padmin varchar,ppassadmin varchar)
returns table(id integer,nombre varchar,codigo varchar,precio numeric,costo numeric,cantidad integer) as
$$
begin
	if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin ) > 0 then
		return query(
		   select p.id,p.nombre,p.codigo ,p.precio,p.costo , p.cantidad from producto p order by id desc
		);
	else 
		return query(
			select p.id,p.nombre,p.codigo ,p.precio,COALESCE((select pr.porcentaje from promocion pr where pr.idproducto = p.id and current_date between pr.fechai and pr.fechaf limit 1 ) , 0)::numeric , p.cantidad from producto p order by p.id desc  
		);
	end if;
end;
$$
language plpgsql;


create or replace function producto_modificar(pid integer,pnombre varchar,pcodigo varchar,pprecio numeric,pcosto numeric,pcantidad numeric,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
    if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
		update producto set nombre = pnombre , codigo = pcodigo ,precio =pprecio,costo = pcosto , cantidad = pcantidad
	   where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

create or replace function producto_eliminar(pid integer,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
   if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
	   delete from producto where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

--------------- promocion ---------------


create or replace function promocion_insertar(pfechai date,pfechaf date,pporcentaje integer,pidproducto integer,padmin varchar,ppassadmin varchar)
returns integer as
$$
	declare 
	idp integer;
begin
	if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin ) > 0 then
		insert into promocion(fechai,fechaf,porcentaje,idproducto) values(pfechai,pfechaf,pporcentaje,pidproducto	) returning id into idp;
        return idp;
	else
	return  -1; 
	end if;
end;
$$
language plpgsql;


create or replace function promocion_listar(padmin varchar,ppassadmin varchar)
returns table(id integer,fechai date,fechaf date,porcentaje integer,idproducto integer,producto varchar) as
$$
begin
	if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin ) > 0 then
		return query(
		   select pr.id,pr.fechai,pr.fechaf,pr.porcentaje,pr.idproducto , p.nombre from promocion pr , producto p where pr.idproducto = p.id order by pr.id desc
		);
	end if;
end;
$$
language plpgsql;

create or replace function promocion_modificar(pid integer,pfechai date,pfechaf date,pporcentaje integer,pidproducto integer,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
    if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
		update promocion set fechai = pfechai , fechaf = pfechaf ,porcentaje =pporcentaje,idproducto = pidproducto 
	   where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;


create or replace function promocion_eliminar(pid integer,padmin varchar,ppassadmin varchar)
returns integer as
$$
begin
   if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
	   delete from promocion where id = pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

------- inventario ----------


create or replace function inventario_ingresoegreso(pfecha date ,padmin varchar,ppassadmin varchar)
returns integer as
$$
declare 
	pid integer;
begin
    if (select count(*) from usuario u where u.user = padmin and u.pass = ppassadmin  ) > 0 then
		insert into inventario(numero,fecha,idusuario) values ( (select count(*) from inventario)+1 ,pfecha , (select u.id from usuario u where u.user= padmin and u.pass = ppassadmin)) returning id into pid;
       return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;


CREATE OR REPLACE FUNCTION ingresoegreso_actualizarCantidad()
RETURNS trigger AS
$$
declare 
	cant integer;
BEGIN
   if new.tipo = 'Ingreso' then
        cant:= (select p.cantidad from producto p where p.id = new.idproducto ) + new.cantidad;
	else 
 		cant:= (select p.cantidad from producto p where p.id = new.idproducto ) - new.cantidad;
	end if;
   if cant >=0 then
		update producto set cantidad = cant where id = new.idproducto;
	end if;
   return null;
END;
$$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER trigger_ingresoegreso_actualizarCantidad
AFTER INSERT ON ingresoegreso
FOR EACH ROW EXECUTE PROCEDURE ingresoegreso_actualizarCantidad();


------------ reserva -------


create or replace function reserva_insertar(puser varchar,ppass varchar)
returns integer as
$$
	declare 
	pid integer;
begin
  if (select count(*) from usuario u where u.user = puser and u.pass = ppass  ) > 0 then
	     insert into reserva(numero,fecha,total,idusuario) values((select count(*) from reserva)+1,current_date,0,(select u.id from usuario u where u.user= puser and u.pass = ppass)) returning id into pid;
		 return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION detallereserva_actualizarTotal()
RETURNS trigger AS
$$
declare 
	cant integer;
BEGIN
   update reserva set total = total + (new.cantidad*new.precio) where id = new.idreserva;
   return null;
END;
$$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER trigger_detallereserva_actualizarTotal
AFTER INSERT ON detallereserva
FOR EACH ROW EXECUTE PROCEDURE detallereserva_actualizarTotal();

create or replace function reserva_mostrar(puser varchar,ppass varchar)
returns table(numero varchar,fecha date,total numeric,producto varchar,cantidad integer,precio numeric) as
$$
begin
	if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
		return query (
			select r.numero,r.fecha,r.total,p.nombre,d.cantidad,d.precio from reserva r , detallereserva d , producto p 
			where r.id = d.idreserva and d.idproducto = p.id  
		);
	else
	return query (  
			select r.numero,r.fecha,r.total,p.nombre,d.cantidad,d.precio from reserva r , detallereserva d , producto p 
			where r.id = d.idreserva and d.idproducto = p.id  and r.idusuario =  (select u.id from usuario u where u.user = puser and u.pass =ppass )  
		);
	end if;
end;
$$
language plpgsql;

-------------venta ----------
select * from venta;

create or replace function venta_insertar(pidcliente integer ,puser varchar,ppass varchar)
returns integer as
$$
	declare 
	pid integer;
begin
  if (select count(*) from usuario u where u.user = puser and u.pass = ppass  ) > 0 then
	     insert into venta(numero,fecha,total,idusuario) values((select count(*)  from venta)+1,current_date,0,pidcliente) returning id into pid;
		 return pid;
	else 
	return -1;  
   end if;
end;
$$
language plpgsql;

CREATE OR REPLACE FUNCTION detalleventa_actualizarTotal()
RETURNS trigger AS
$$
declare 
	cant integer;
BEGIN
   update venta set total = total + (new.cantidad*new.precio) where id = new.idventa;
   return null;
END;
$$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER trigger_detalleventa_actualizarTotal
AFTER INSERT ON detallereserva
FOR EACH ROW EXECUTE PROCEDURE detalleventa_actualizarTotal();


create or replace function venta_mostrar(puser varchar,ppass varchar)
returns table(numero varchar,fecha date,total numeric,producto varchar,cantidad integer,precio numeric,usuario varchar) as
$$
begin
	if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
		return query (
			select v.numero,v.fecha,v.total,p.nombre,d.cantidad,d.precio,u.nombre from venta v , detalleventa d , producto p  ,usuario u
			where v.id = d.idventa and d.idproducto = p.id  and u.id = v.idusuario
		);
	else
	return query (  
			select v.numero,v.fecha,v.total,p.nombre,d.cantidad,d.precio,u.nombre from venta v , detalleventa d , producto p  ,usuario u
			where v.id = d.idventa and d.idproducto = p.id  and u.id = v.idusuario and u.user = puser and u.pass = ppass
		);
	end if;
end;
$$
language plpgsql;

---------- pago ---------------------------

select * from pago;

create or replace function pago_insertar(ptotal numeric,pidventa integer,puser varchar,ppass varchar)
returns integer as
$$
declare 
	pid integer;
begin
	if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			insert into pago(fecha,total,idventa) values(current_date,ptotal,pidventa) returning id into pid;
		    return pid;
	else
		return -1;
   end if;
end;
$$
language plpgsql;

create or replace function pago_mostrar(puser varchar,ppass varchar)
returns table(id integer,fecha date,total numeric,venta varchar,usuario varchar) as
$$
begin
	if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
		return query (
			 select p.id , p.fecha ,p.total , v.numero , u.nombre from pago p, venta v, usuario u 
	         where u.id = v.idusuario and p.idventa = v.id 
		);
	else
	return query (  
			 select p.id , p.fecha ,p.total , v.numero , u.nombre from pago p, venta v, usuario u 
	          where u.id = v.idusuario and p.idventa = v.id and u.user = puser and u.pass = ppass
		);
	end if;
end;
$$
language plpgsql;

create or replace function pago_modificar(pid integer,ptotal numeric,pidventa integer,puser varchar,ppass varchar)
returns integer as
$$
begin
  if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			update pago set total = ptotal , idventa = pidventa , fecha = current_date 
			where id = pid ;
		    return pid;
	else
		return -1;
   end if;
end;
$$
language plpgsql;

create or replace function pago_eliminar(pid integer,puser varchar,ppass varchar)
returns integer as
$$
begin
    if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			delete from pago where id = pid ;
		    return pid;
	else
		return -1;
   end if;
end;
$$
language plpgsql;

-------- reportes ------
create or replace function venta_reporte(puser varchar,ppass varchar)
returns table(numero varchar,fecha date , total numeric,cliente varchar,estado text) as
$$
begin
	   if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			return query (
	 		select v.numero,v.fecha ,v.total , u.nombre , case when  (select count(*) from pago p where p.idventa =v.id ) > 0 then 'Pagado' else 'No Pagado' end from venta v, usuario u  
			where  v.idusuario = u.id 
			);
	  end if;
end;
$$
language plpgsql;


create or replace function inventario_reporte(puser varchar,ppass varchar)
returns table(nombre varchar,codigo varchar , stock integer,cantidad integer,tipo varchar,fecha date) as
$$
begin
	   if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			return query (
	 		   select p.nombre,p.codigo,p.cantidad ,i.cantidad,i.tipo , (select iv.fecha from inventario iv where iv.id = i.idinventario limit 1)  from producto p , ingresoegreso i 
			 where  i.idproducto = p.id
			);
	  end if;
end;
$$
language plpgsql;



------------- estadisticas -----------
create or replace function producto_estadistica(puser varchar,ppass varchar)
returns table(nombre varchar,codigo varchar , vendido bigint) as
$$
begin
	   if (select u.tipo from usuario u where u.user = puser and u.pass =ppass ) = 'Administrador' then 
			return query (
	 		   select p.nombre , p.codigo , (select sum(d.cantidad) from detalleventa d where d.idproducto = p.id) as vendidos  from producto p
				order by vendidos desc
			);
	  end if;
end;
$$
language plpgsql;



 




