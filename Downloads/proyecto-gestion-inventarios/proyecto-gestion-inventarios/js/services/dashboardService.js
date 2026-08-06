// js/services/dashboardService.js

// Devuelve los productos cuyo stock está por debajo del umbral configurado.
function getProductosBajoStock(limite = 10) {
  return listarProd().filter(p => p.stock < limite);
}

// Devuelve los productos más frecuentes según movimientos registrados.
function getTopProductosPorMovimientos(top = 5) {
  const productos = listarProd();
  const movimientos = listarMovimientos();

  const conteo = {};
  movimientos.forEach(m => {
    conteo[m.idProd] = (conteo[m.idProd] || 0) + 1;
  });

  return Object.entries(conteo)
    .sort((a, b) => b[1] - a[1])
    .slice(0, top)
    .map(([idProd, cantidad]) => {
      const producto = productos.find(p => p.id === Number(idProd));
      return { nombre: producto ? producto.nombre : 'Desconocido', cantidad };
    });
}

// Devuelve los movimientos más recientes, ordenados de más nuevo a más viejo.
function getUltimosMovimientos(limite = 5) {
  return listarMovimientos()
    .slice()
    .sort((a, b) => new Date(b.fecha) - new Date(a.fecha))
    .slice(0, limite)
    .map(m => ({
      ...m,
      fechaTexto: new Date(m.fecha).toLocaleString('es-ES', { hour: '2-digit', minute: '2-digit', day: '2-digit', month: 'short' })
    }));
}

// Devuelve el total de categorías registradas.
function getTotalCategorias() {
  return (JSON.parse(localStorage.getItem(STORAGE_KEYS.CATEGORIAS)) || []).length;
}

// Devuelve el total de productos registrados.
function getTotalProductos() {
  return listarProd().length;
}

// Renderiza los paneles y listas principales del dashboard.
function renderDashboard() {
  const bajoStock = getProductosBajoStock(10);
  document.getElementById('totalBajoStock').textContent = bajoStock.length;
  document.getElementById('listaBajoStock').innerHTML = bajoStock
    .map(p => `<li>${p.nombre} — Stock: ${p.stock}</li>`)
    .join('') || '<li class="text-slate-400">Sin alertas</li>';

  const topMovimientos = getTopProductosPorMovimientos(5);
  document.getElementById('listaTopMovimientos').innerHTML = topMovimientos
    .map(t => `<li>${t.nombre} (${t.cantidad} mov.)</li>`)
    .join('') || '<li class="text-slate-400">Sin movimientos</li>';

  document.getElementById('totalProductos').textContent = getTotalProductos();
  document.getElementById('totalCategorias').textContent = getTotalCategorias();

  const recientes = getUltimosMovimientos(6);
  document.getElementById('listaMovimientosRecientes').innerHTML = recientes
    .map(m => `<li class="rounded-3xl border border-slate-200/5 bg-slate-950/80 p-4 text-slate-200">
      <div class="flex items-center justify-between gap-3">
        <span class="font-medium">${m.usuario}</span>
        <span class="text-xs text-slate-400">${m.fechaTexto}</span>
      </div>
      <p class="mt-2 text-sm text-slate-300">${m.tipo.toUpperCase()} • ${m.cant} unidad(es) • Producto ID ${m.idProd}</p>
    </li>`)
    .join('') || '<li class="text-slate-400">No hay movimientos recientes</li>';
}