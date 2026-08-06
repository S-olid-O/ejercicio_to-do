// js/models/Movimiento.js
function Movimiento(id, productoId, tipo, cantidad, usuarioId) {
  return {
    id,
    productoId,
    tipo,
    cantidad,
    usuarioId,
    fecha: new Date().toISOString()
  };
}