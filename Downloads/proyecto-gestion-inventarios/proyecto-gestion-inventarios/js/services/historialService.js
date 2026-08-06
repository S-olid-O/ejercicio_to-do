// js/services/historialService.js

function renderHistorial(filtros = {}) {
  const productos = listarProd();
  let movimientos = listarMovimientos();

  if (filtros.fecha) {
    movimientos = movimientos.filter(m => m.fecha.startsWith(filtros.fecha));
  }
  if (filtros.tipo) {
    movimientos = movimientos.filter(m => m.tipo === filtros.tipo);
  }

  movimientos.sort((a, b) => new Date(b.fecha) - new Date(a.fecha));

  document.getElementById('tablaHistorial').innerHTML = movimientos.map(m => {
    const producto = productos.find(p => p.id === m.idProd);
    const colorTipo = m.tipo === 'entrada' ? 'text-green-600' : 'text-red-600';
    return `
      <tr>
        <td class="px-4 py-3">${new Date(m.fecha).toLocaleString()}</td>
        <td class="px-4 py-3">${producto ? producto.nombre : 'Eliminado'}</td>
        <td class="px-4 py-3 font-medium ${colorTipo}">${m.tipo}</td>
        <td class="px-4 py-3">${m.cant}</td>
        <td class="px-4 py-3">${m.usuario}</td>
      </tr>`;
  }).join('') || '<tr><td colspan="5" class="px-4 py-3 text-gray-400">Sin movimientos</td></tr>';
}