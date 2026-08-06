// js/app-admin.js

// Renderiza la tabla de productos en el panel de administración.
function renderTablaAdmin() {
  const tabla = document.getElementById('tablaProductosAdmin');
  if (!tabla) return;

  const categorias = JSON.parse(localStorage.getItem(STORAGE_KEYS.CATEGORIAS)) || [];
  const productos = listarProd();
  tabla.innerHTML = productos.map(p => {
    const categoria = categorias.find(c => c.id === p.categoriaId);
    return `
      <tr>
        <td class="px-4 py-3">${p.nombre}</td>
        <td class="px-4 py-3">${categoria ? categoria.nombre : '-'}</td>
        <td class="px-4 py-3">${p.stock}</td>
        <td class="px-4 py-3">${p.stockMinimo}</td>
        <td class="px-4 py-3">$${p.precio.toLocaleString()}</td>
        <td class="px-4 py-3 space-x-2">
          <button class="btn-editar-producto text-blue-600 hover:underline" onclick="onEditarClick(${p.id})">Editar</button>
          <button class="btn-eliminar-producto text-red-600 hover:underline" onclick="onEliminarClick(${p.id}, '${p.nombre}')">Eliminar</button>
        </td>
      </tr>`;
  }).join('');
  aplicarPermisosRol();
}

// Carga la lista de categorías en el formulario de producto.
function cargarCategoriasAdmin() {
  const categorias = listarCategorias();
  const select = document.getElementById('prodCategoria');
  select.innerHTML = `<option value="">Seleccione una categoría</option>` + categorias.map(c => `
      <option value="${c.id}">${c.nombre}</option>`
    ).join('');
}

// Renderiza la lista de categorías y permite su eliminación.
function renderCategoriasAdmin() {
  const categorias = listarCategorias();
  const lista = document.getElementById('listaCategorias');
  lista.innerHTML = categorias.map(c => `
    <li class="flex items-center justify-between rounded-2xl border border-slate-200/10 bg-slate-950/80 px-4 py-3">
      <span>${c.nombre}</span>
      <button class="text-sm font-medium text-rose-400 hover:text-rose-300" onclick="onEliminarCategoria(${c.id})">Eliminar</button>
    </li>`).join('') || '<li class="text-slate-500">No hay categorías registradas</li>';
}

// Limpia el formulario de producto y restaura el estado predeterminado.
function limpiarFormularioProducto() {
  document.getElementById('prodId').value = '';
  document.getElementById('prodNombre').value = '';
  document.getElementById('prodCategoria').value = '';
  document.getElementById('prodStock').value = '';
  document.getElementById('prodStockMin').value = '';
  document.getElementById('prodPrecio').value = '';
  document.getElementById('formTitulo').textContent = 'Agregar producto';
  document.getElementById('submitProducto').textContent = 'Agregar producto';
  document.getElementById('btnCancelarEdicion').classList.add('hidden');
}

// Llena el formulario para editar un producto existente.
function onEditarClick(idProd) {
  const producto = listarProd().find(p => p.id === idProd);
  if (!producto) {
    mostrarToast('Producto no encontrado', 'error');
    return;
  }

  document.getElementById('prodId').value = producto.id;
  document.getElementById('prodNombre').value = producto.nombre;
  document.getElementById('prodCategoria').value = producto.categoriaId;
  document.getElementById('prodStock').value = producto.stock;
  document.getElementById('prodStockMin').value = producto.stockMinimo;
  document.getElementById('prodPrecio').value = producto.precio;
  document.getElementById('formTitulo').textContent = 'Editar producto';
  document.getElementById('submitProducto').textContent = 'Guardar cambios';
  document.getElementById('btnCancelarEdicion').classList.remove('hidden');
}

// Elimina un producto después de confirmar la acción con el usuario.
async function onEliminarClick(idProd, nombreProd) {
  const confirmado = await confirmarAccion(`¿Seguro que deseas eliminar "${nombreProd}"? Esta acción no se puede deshacer.`);
  if (!confirmado) return;

  const resultado = eliminarProd(idProd);
  mostrarToast(resultado.ok ? 'Producto eliminado' : resultado.msg, resultado.ok ? 'exito' : 'error');

  if (resultado.ok) {
    renderTablaAdmin();
    limpiarFormularioProducto();
  }
}

// Inicializa los datos y comportamientos de la página de administración.
function inicializarAdmin() {
  initData();
  protegerRuta();
  if (getRol() !== ROLES.ADMIN) {
    window.location.href = 'dashboard.html';
    return;
  }
  cargarCategoriasAdmin();
  renderCategoriasAdmin();
  renderTablaAdmin();

  // Maneja el envío del formulario para crear o editar productos.
  document.getElementById('formProducto').addEventListener('submit', e => {
    e.preventDefault();
    const id = Number(document.getElementById('prodId').value);
    const nombre = document.getElementById('prodNombre').value.trim();
    const categoriaId = Number(document.getElementById('prodCategoria').value);
    const stock = Number(document.getElementById('prodStock').value);
    const stockMinimo = Number(document.getElementById('prodStockMin').value);
    const precio = Number(document.getElementById('prodPrecio').value);

    if (!nombre || !categoriaId || stock < 0 || stockMinimo < 0 || precio < 0) {
      mostrarToast('Por favor completa todos los campos correctamente', 'error');
      return;
    }

    const productoDatos = { nombre, categoriaId, stock, stockMinimo, precio };

    let resultado;
    if (id) {
      resultado = editarProd(id, productoDatos);
    } else {
      resultado = crearProd(nombre, categoriaId, stock, stockMinimo, precio);
    }

    mostrarToast(resultado.ok ? 'Producto guardado correctamente' : resultado.msg, resultado.ok ? 'exito' : 'error');
    if (resultado.ok) {
      renderTablaAdmin();
      renderCategoriasAdmin();
      limpiarFormularioProducto();
    }
  });

  document.getElementById('btnAgregarCategoria').addEventListener('click', () => {
    const categoriaNombre = document.getElementById('categoriaNombre').value.trim();
    if (!categoriaNombre) {
      mostrarToast('Ingresa un nombre de categoría válido', 'error');
      return;
    }

    const resultado = crearCategoria(categoriaNombre);
    mostrarToast(resultado.ok ? 'Categoría creada' : resultado.msg, resultado.ok ? 'exito' : 'error');
    if (resultado.ok) {
      document.getElementById('categoriaNombre').value = '';
      cargarCategoriasAdmin();
      renderCategoriasAdmin();
    }
  });

  document.getElementById('btnCancelarEdicion').addEventListener('click', () => {
    limpiarFormularioProducto();
  });
}

// Elimina una categoría si no hay productos asociados.
function onEliminarCategoria(idCategoria) {
  const confirmado = confirm('¿Eliminar esta categoría? Solo si no existen productos asociados.');
  if (!confirmado) return;

  const resultado = eliminarCategoria(idCategoria);
  mostrarToast(resultado.ok ? 'Categoría eliminada' : resultado.msg, resultado.ok ? 'exito' : 'error');
  if (resultado.ok) {
    cargarCategoriasAdmin();
    renderCategoriasAdmin();
  }
}

inicializarAdmin();