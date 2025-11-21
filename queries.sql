-- numero de usuarios por rol, de mayor a menor
SELECT r.nombre AS rol, COUNT(u.id) AS total_usuarios -- COUNT cuenta los usuarios por rol
FROM ROLES r
LEFT JOIN USUARIOS u ON u.role_id = r.id -- unir cada rol con usuarios que tengan role_id = id del rol
-- por left join tambien aparecen roles que no tienen usuarios
GROUP BY r.nombre -- agrupa resultados por cada rol
ORDER BY total_usuarios DESC; -- de mayor a menor segun el numero de usuarios

-- usuarios con rol no asignado, aunque hay restriccion de not null
SELECT *
FROM USUARIOS
WHERE role_id IS NULL;

-- actualizar email de usuario por id
UPDATE USUARIOS
SET email = 'nuevo@email.com'
WHERE id = 66;  

-- eliminar usuarios duplicados por email, dejando el mas reciente, la tabla tiene restriccion unique
DELETE FROM USUARIOS -- los elimina
WHERE id IN (
    SELECT id FROM (
      /*
      1, row number crea un numero secuencial por cada fila dentro del grupo
      2. luego partition by email agrupa a los usuarios por email, donde
      todos los usuarios con el mismo email forman un grupo
      3. se ordenan por fecha de creacion, de mas nuevo a antiguo
      */
        SELECT id,
               ROW_NUMBER() OVER (PARTITION BY email ORDER BY fecha_creacion DESC) AS rn -- rn va a ir de 1 en 1
        FROM USUARIOS
    )
    WHERE rn > 1 -- aca selecciona todos id de los usuarios menos el mas reciente de cada grupo
);
