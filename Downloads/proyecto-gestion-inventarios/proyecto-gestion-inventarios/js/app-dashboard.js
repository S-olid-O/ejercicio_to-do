// Carga las categorías disponibles en el formulario del dashboard.
function cargarCategoriasDashboard() {
  const categorias = listarCategorias();
  const select = document.getElementById('dashboardProdCategoria');
  if (!select) return;

  select.innerHTML = categorias.length
    ? '<option value="">Seleccione una categoría</option>' + categorias
      .map(c => `<option value="${c.id}">${c.nombre}</option>`)
      .join('')
    : '<option value="" disabled selected>No hay categorías disponibles</option>';
}

// Limpia los campos del formulario tras agregar un producto.
function limpiarFormularioDashboard() {
  document.getElementById('dashboardProdNombre').value = '';
  document.getElementById('dashboardProdCategoria').value = '';
  document.getElementById('dashboardProdPrecio').value = '';
  document.getElementById('dashboardProdStock').value = '';
  document.getElementById('dashboardProdStockMin').value = '';
  const mensaje = document.getElementById('dashboardAddMsg');
  if (mensaje) mensaje.textContent = '';
}

// Inicializa los componentes y datos de la página principal.
function inicializarDashboard() {
  initData();
  protegerRuta();
  renderAppHeader('Dashboard');
  cargarCategoriasDashboard();
  renderDashboard();

  const form = document.getElementById('formAgregarProducto');
  if (!form) return;

  // Captura el envío del formulario para crear un producto nuevo desde el dashboard.
  form.addEventListener('submit', e => {
    e.preventDefault();
    if (!validarFormulario(form)) return;

    const nombre = document.getElementById('dashboardProdNombre').value.trim();
    const categoriaId = Number(document.getElementById('dashboardProdCategoria').value);
    const precio = Number(document.getElementById('dashboardProdPrecio').value);
    const stock = Number(document.getElementById('dashboardProdStock').value);
    const stockMinimo = Number(document.getElementById('dashboardProdStockMin').value);
    const mensaje = document.getElementById('dashboardAddMsg');

    if (!nombre || !categoriaId || precio < 0 || stock < 0 || stockMinimo < 0) {
      mostrarToast('Completa todos los campos correctamente', 'error');
      if (mensaje) mensaje.textContent = 'Completa todos los campos correctamente';
      return;
    }

    const resultado = crearProdLibre(nombre, categoriaId, stock, stockMinimo, precio);
    if (!resultado.ok) {
      mostrarToast(resultado.msg, 'error');
      if (mensaje) mensaje.textContent = resultado.msg;
      return;
    }

    mostrarToast('Producto agregado con éxito', 'exito');
    if (mensaje) mensaje.textContent = 'Producto agregado correctamente.';

    limpiarFormularioDashboard();
    cargarCategoriasDashboard();
    renderDashboard();
  });
}

inicializarDashboard();