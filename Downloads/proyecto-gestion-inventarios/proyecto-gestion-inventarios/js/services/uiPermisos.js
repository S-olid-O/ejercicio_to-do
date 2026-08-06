// js/services/uiPermisos.js

function aplicarPermisosRol() {
  const rol = getRol();

  if (rol !== ROLES.ADMIN) {
    document.querySelectorAll('.btn-eliminar-producto, .btn-editar-producto')
      .forEach(btn => btn.style.display = 'none');
  }
}

document.addEventListener('DOMContentLoaded', () => {
  protegerRuta();
  aplicarPermisosRol();
});