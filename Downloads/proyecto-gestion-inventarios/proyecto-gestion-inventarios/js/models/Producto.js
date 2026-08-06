// js/models/Producto.js
function Producto(id, nombre, categoriaId, stock, stockMinimo, precio) {
  return {
    id,
    nombre,
    categoriaId,
    stock,
    stockMinimo,
    precio,
    creadoEn: new Date().toISOString()
  };
}