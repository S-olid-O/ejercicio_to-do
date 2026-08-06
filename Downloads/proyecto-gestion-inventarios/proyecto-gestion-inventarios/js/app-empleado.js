// js/app-empleado.js

// Carga las opciones del filtro de categoría para la vista de empleado.
function cargarCategorias() {
  const categorias = JSON.parse(localStorage.getItem(STORAGE_KEYS.CATEGORIAS)) || [];
  const select = document.getElementById('filtroCategoria');
  categorias.forEach(c => {
    select.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;
  });
}

// Carga el select de productos para registrar movimientos.
function cargarSelectProductos() {
  const productos = listarProd();
  const select = document.getElementById('movProducto');
  if (!productos.length) {
    select.innerHTML = '<option value="" disabled selected>No hay productos disponibles</option>';
    return;
  }

  select.innerHTML = '<option value="" disabled selected>Seleccione un producto</option>' + productos
    .map(p => `<option value="${p.id}">${p.nombre} (Stock: ${p.stock})</option>`)
    .join('');
}

// Actualiza los indicadores resumidos que se muestran en la vista de empleado.
function actualizarResumenEmpleado() {
  const productos = listarProd();
  document.getElementById('resumenTotalProductos').textContent = productos.length;
  document.getElementById('resumenBajoStock').textContent = productos.filter(p => p.stock < 10).length;
}

// Renderiza la tabla de productos para la vista de empleado, con filtro opcional por categoría.
function renderTabla(filtroCategoriaId = '') {
  const categorias = JSON.parse(localStorage.getItem(STORAGE_KEYS.CATEGORIAS)) || [];
  let productos = listarProd();

  if (filtroCategoriaId) {
    productos = productos.filter(p => p.categoriaId === Number(filtroCategoriaId));
  }

  document.getElementById('tablaProductos').innerHTML = productos.map(p => {
    const cat = categorias.find(c => c.id === p.categoriaId);
    return `
      <tr>
        <td class="px-4 py-3">${p.nombre}</td>
        <td class="px-4 py-3">${cat ? cat.nombre : '-'}</td>
        <td class="px-4 py-3">${p.stock}</td>
        <td class="px-4 py-3">$${p.precio.toLocaleString()}</td>
      </tr>`;
  }).join('') || '<tr><td colspan="4" class="px-4 py-3 text-gray-400">Sin productos</td></tr>';
}

// Maneja el registro de movimientos de inventario (entrada/salida) desde el formulario.
// Maneja el registro de movimientos de inventario (entrada/salida) desde el formulario.
document.getElementById('formMovimiento').addEventListener('submit', async e => {
  e.preventDefault();
  const form = e.target;
  if (!validarFormulario(form)) return;

  const idProd = Number(document.getElementById('movProducto').value);
  const tipo = document.getElementById('movTipo').value;
  const cant = Number(document.getElementById('movCantidad').value);

  const resultado = tipo === 'entrada'
    ? registrarEntrada(idProd, cant)
    : registrarSalida(idProd, cant, tipo);

  mostrarToast(resultado.ok ? 'Movimiento registrado correctamente' : resultado.msg, resultado.ok ? 'exito' : 'error');

  if (resultado.ok) {
    form.reset();
    renderTabla(document.getElementById('filtroCategoria').value);
    cargarSelectProductos();
    actualizarResumenEmpleado();
  }
});

initData();
cargarCategorias();
cargarSelectProductos();
renderTabla();
actualizarResumenEmpleado();