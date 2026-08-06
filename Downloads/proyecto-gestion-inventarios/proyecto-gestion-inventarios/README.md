Usuario: jperez, admin
Contraseña: emp123, admin123

1.	Prompt: 

Actúa como arquitecto frontend senior. 

Proyecto: Sistema de Gestión de Inventarios Empresarial. Solo Frontend.
Tecnologías: HTML5, Tailwind CSS, JavaScript Vanilla, localStorage.
Roles: admin y empleado.

Tareas:
1. Estructura de carpetas para proyecto estático.
2. 3 objetos JS para guardar en localStorage: Producto, Movimiento, Usuario. Campos mínimos y necesarios.
3. Funciones initData() para crear datos de prueba si localStorage está vacío. Incluir 1 admin, 1 empleado, 2 categorías, 2 productos.
4. Constantes para keys de localStorage.

Requisitos: Código limpio, corto y funcional. Sin explicaciones, solo código en bloques separados.

2.	Prompt:
Dado localStorage, crea módulo auth.js con: login, logout, getRol. Roles: admin, empleado. Protege vistas: si rol!=admin oculta botones eliminar/editar producto. Código JS Vanilla corto.

3.	Prompt
Crea módulo productos.js con funciones: crearProd, editarProd, eliminarProd, listarProd, crearCategoria. Valida que no se pueda eliminar si hay stock>0. Usa localStorage. Devuelve solo código.
4.	Prompt
Crea módulo movimientos.js con funciones: registrarEntrada(idProd, cant), registrarSalida(idProd, cant, tipo). Actualiza stock automático en Producto. Guarda Movimiento con {id, idProd, tipo, cant, fecha, usuario}. Valida stock insuficiente.
5.	Prompt
Usando Tailwind, genera HTML para dashboard admin con 3 cards: 1. Productos bajo stock <10. 2. Top 5 productos con más movimientos. 3. Total productos. Funciones JS para calcular. Código compacto.

6.	Prompt 
Genera HTML+JS para vista empleado: Tabla inventario solo lectura + formulario para Entrada y Salida. Filtro por categoría. Usa Tailwind y funciones de prompt 3 y 4. Código mínimo.

7.	Prompt
Crea función renderHistorial(filtros) que lea Movimientos de localStorage y muestre tabla con fecha, producto, tipo, cant, usuario. Filtros: fecha y tipo. HTML+JS con Tailwind. Corto.

8.	Prompt
Revisa estos 3 módulos: productos.js, movimientos.js, auth.js. Optimízalos para menos bucles y menos escrituras a localStorage. Aplica debounce al guardar. Devuelve solo código optimizado.

9.	Prompt

Mejora UX: 1. Toasts para éxito/error. 2. Confirmación antes de eliminar. 3. Validar formularios vacíos. Usa solo JS Vanilla y Tailwind. Código de funciones reutilizables.

10.	Prompt

Detecta 5 bugs comunes en app inventario con localStorage y 2 roles. Para cada uno da: causa + fix de 1 línea de código. Casos: stock negativo, borrar categoría en uso, login sin usuario.
