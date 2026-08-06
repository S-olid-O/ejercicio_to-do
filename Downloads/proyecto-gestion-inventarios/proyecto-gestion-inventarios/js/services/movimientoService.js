// js/services/movimientoService.js

function registrarEntrada(idProd, cant) {
  return _procesarMovimiento(idProd, cant, TIPOS_MOVIMIENTO.ENTRADA);
}

function registrarSalida(idProd, cant, tipo = TIPOS_MOVIMIENTO.SALIDA) {
  return _procesarMovimiento(idProd, cant, tipo);
}

function _procesarMovimiento(idProd, cant, tipo) {
  const producto = getIndex(STORAGE_KEYS.PRODUCTOS).get(idProd);

  if (!producto) return { ok: false, msg: 'Producto no encontrado' };
  if (cant <= 0) return { ok: false, msg: 'Cantidad inválida' };
  if (tipo !== TIPOS_MOVIMIENTO.ENTRADA && producto.stock < cant) {
    return { ok: false, msg: 'Stock insuficiente' };
  }

  producto.stock += tipo === TIPOS_MOVIMIENTO.ENTRADA ? cant : -cant;
  scheduleWrite(STORAGE_KEYS.PRODUCTOS);

  const movimientos = getCache(STORAGE_KEYS.MOVIMIENTOS);
  const nuevoId = movimientos.length ? movimientos[movimientos.length - 1].id + 1 : 1;
  const sesion = getSesion();

  const movimiento = {
    id: nuevoId,
    idProd,
    tipo,
    cant,
    fecha: new Date().toISOString(),
    usuario: sesion ? sesion.usuario : 'desconocido'
  };

  movimientos.push(movimiento);
  scheduleWrite(STORAGE_KEYS.MOVIMIENTOS);

  return { ok: true, movimiento, stockActual: producto.stock };
}

function listarMovimientos() {
  return getCache(STORAGE_KEYS.MOVIMIENTOS);
}