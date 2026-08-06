// js/services/productoService.js

// Crea un producto nuevo y lo guarda en caché y almacenamiento.
function crearProd(nombre, categoriaId, stock, stockMinimo, precio) {
  const productos = getCache(STORAGE_KEYS.PRODUCTOS);
  const nuevoId = productos.length ? productos[productos.length - 1].id + 1 : 1;
  const nuevo = Producto(nuevoId, nombre, categoriaId, stock, stockMinimo, precio);

  productos.push(nuevo);
  getIndex(STORAGE_KEYS.PRODUCTOS).set(nuevoId, nuevo);
  scheduleWrite(STORAGE_KEYS.PRODUCTOS);

  return { ok: true, producto: nuevo };
}

// Función de comodidad para crear un producto desde el dashboard.
function crearProdLibre(nombre, categoriaId, stock, stockMinimo, precio) {
  return crearProd(nombre, categoriaId, stock, stockMinimo, precio);
}

// Edita los datos de un producto existente y actualiza el almacenamiento.
function editarProd(id, datos) {
  const producto = getIndex(STORAGE_KEYS.PRODUCTOS).get(id);
  if (!producto) return { ok: false, msg: 'Producto no encontrado' };

  Object.assign(producto, datos);
  scheduleWrite(STORAGE_KEYS.PRODUCTOS);

  return { ok: true, producto };
}

// Elimina un producto si no tiene stock disponible.
function eliminarProd(id) {
  const index = getIndex(STORAGE_KEYS.PRODUCTOS);
  const producto = index.get(id);

  if (!producto) return { ok: false, msg: 'Producto no encontrado' };
  if (producto.stock > 0) return { ok: false, msg: 'No se puede eliminar: el producto tiene stock disponible' };

  const productos = getCache(STORAGE_KEYS.PRODUCTOS);
  const pos = productos.indexOf(producto);
  productos.splice(pos, 1);
  index.delete(id);
  scheduleWrite(STORAGE_KEYS.PRODUCTOS);

  return { ok: true };
}

// Devuelve la lista de productos desde caché.
function listarProd() {
  return getCache(STORAGE_KEYS.PRODUCTOS);
}

// Crea una nueva categoría y la guarda en almacenamiento.
function crearCategoria(nombre) {
  const categorias = getCache(STORAGE_KEYS.CATEGORIAS);
  const nuevoId = categorias.length ? categorias[categorias.length - 1].id + 1 : 1;
  const nueva = { id: nuevoId, nombre };

  categorias.push(nueva);
  scheduleWrite(STORAGE_KEYS.CATEGORIAS);

  return { ok: true, categoria: nueva };
}

// Devuelve la lista de categorías desde caché.
function listarCategorias() {
  return getCache(STORAGE_KEYS.CATEGORIAS);
}

// Elimina una categoría si no hay productos asociados.
function eliminarCategoria(id) {
  const categorias = getCache(STORAGE_KEYS.CATEGORIAS);
  const productos = getCache(STORAGE_KEYS.PRODUCTOS);
  if (productos.some(p => p.categoriaId === id)) {
    return { ok: false, msg: 'No se puede eliminar: existen productos asociados a esta categoría' };
  }

  const categoria = categorias.find(c => c.id === id);
  if (!categoria) return { ok: false, msg: 'Categoría no encontrada' };

  const pos = categorias.indexOf(categoria);
  categorias.splice(pos, 1);
  scheduleWrite(STORAGE_KEYS.CATEGORIAS);

  return { ok: true };
}