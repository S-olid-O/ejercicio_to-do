// js/data/initData.js
function initData() {
  const categoriasIniciales = [
    { id: 1, nombre: 'Electrónica' },
    { id: 2, nombre: 'Oficina' }
  ];

  const usuariosIniciales = [
    Usuario(1, 'Administrador', 'admin', 'admin123', ROLES.ADMIN),
    Usuario(2, 'Juan Pérez', 'jperez', 'emp123', ROLES.EMPLEADO)
  ];

  const productosIniciales = [
    Producto(1, 'Laptop HP 15', 1, 10, 3, 2500000),
    Producto(2, 'Resma de Papel Carta', 2, 50, 10, 15000)
  ];

  const movimientosIniciales = [
    Movimiento(1, 1, TIPOS_MOVIMIENTO.ENTRADA, 10, 1),
    Movimiento(2, 2, TIPOS_MOVIMIENTO.ENTRADA, 50, 1)
  ];

  if (!localStorage.getItem(STORAGE_KEYS.USUARIOS)) {
    localStorage.setItem(STORAGE_KEYS.USUARIOS, JSON.stringify(usuariosIniciales));
  }
  if (!localStorage.getItem(STORAGE_KEYS.CATEGORIAS)) {
    localStorage.setItem(STORAGE_KEYS.CATEGORIAS, JSON.stringify(categoriasIniciales));
  }
  if (!localStorage.getItem(STORAGE_KEYS.PRODUCTOS)) {
    localStorage.setItem(STORAGE_KEYS.PRODUCTOS, JSON.stringify(productosIniciales));
  }
  if (!localStorage.getItem(STORAGE_KEYS.MOVIMIENTOS)) {
    localStorage.setItem(STORAGE_KEYS.MOVIMIENTOS, JSON.stringify(movimientosIniciales));
  }
}