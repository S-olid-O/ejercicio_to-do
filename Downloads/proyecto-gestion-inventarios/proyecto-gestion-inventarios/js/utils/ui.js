// js/utils/ui.js

// Muestra un mensaje emergente breve en pantalla.
function mostrarToast(mensaje, tipo = 'exito') {
  const contenedor = _getToastContainer();
  const colores = {
    exito: 'bg-green-600',
    error: 'bg-red-600',
    info: 'bg-blue-600'
  };

  const toast = document.createElement('div');
  toast.className = `${colores[tipo]} text-white px-4 py-3 rounded-lg shadow-lg text-sm flex items-center justify-between gap-3 animate-fade-in`;
  toast.innerHTML = `<span>${mensaje}</span>
    <button class="font-bold leading-none">&times;</button>`;

  toast.querySelector('button').addEventListener('click', () => toast.remove());
  contenedor.appendChild(toast);

  setTimeout(() => toast.remove(), 3200);
}

// Devuelve o crea el contenedor que agrupa las notificaciones toast.
function _getToastContainer() {
  let contenedor = document.getElementById('toastContainer');
  if (!contenedor) {
    contenedor = document.createElement('div');
    contenedor.id = 'toastContainer';
    contenedor.className = 'fixed top-4 right-4 z-50 flex flex-col gap-2 w-80';
    document.body.appendChild(contenedor);
  }
  return contenedor;
}

// Renderiza el encabezado común de la aplicación con navegación y cierre de sesión.
function renderAppHeader(titulo = 'Inventario') {
  const contenedor = document.getElementById('appHeader');
  if (!contenedor) return;

  const sesion = getSesion();
  const nombre = sesion?.nombre || 'Invitado';
  const rol = sesion?.rol || 'Sin rol';

  const enlaces = [
    { href: 'dashboard.html', label: 'Dashboard' },
    { href: 'historial.html', label: 'Historial' }
  ];

  if (rol === ROLES.ADMIN) {
    enlaces.push({ href: 'admin.html', label: 'Administración' });
  }

  contenedor.innerHTML = `
    <header class="mb-6 rounded-3xl border border-slate-700 bg-slate-950/80 p-6 shadow-xl backdrop-blur-xl text-slate-100">
      <div class="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
        <div>
          <p class="text-sm uppercase tracking-[0.32em] text-slate-400">${titulo}</p>
          <h1 class="mt-2 text-3xl font-semibold text-white">Bienvenido${nombre ? ', ' + nombre : ''}</h1>
        </div>
        <div class="flex flex-wrap items-center gap-3">
          ${enlaces.map(link => `<a href="${link.href}" class="rounded-full border border-slate-700 bg-slate-900/90 px-4 py-2 text-sm font-medium text-slate-100 transition hover:bg-slate-800">${link.label}</a>`).join('')}
          <button id="btnLogout" class="rounded-full bg-blue-600 px-4 py-2 text-sm font-semibold text-white transition hover:bg-blue-500">Cerrar sesión</button>
        </div>
      </div>
      <div class="mt-6 rounded-3xl border border-slate-700 bg-slate-900/80 px-5 py-4 text-sm text-slate-300">
        <span class="font-medium text-slate-100">Rol:</span> ${rol}
      </div>
    </header>`;

  const logoutBtn = contenedor.querySelector('#btnLogout');
  if (logoutBtn) logoutBtn.addEventListener('click', logout);
}

// Muestra un modal de confirmación y devuelve la respuesta del usuario.
function confirmarAccion(mensaje) {
  return new Promise(resolve => {
    const overlay = document.createElement('div');
    overlay.className = 'fixed inset-0 bg-black/50 flex items-center justify-center z-50';
    overlay.innerHTML = `
      <div class="rounded-3xl bg-slate-950 p-6 shadow-2xl border border-slate-700 w-[min(90vw,420px)]">
        <p class="text-sm text-slate-100">${mensaje}</p>
        <div class="mt-6 flex justify-end gap-3">
          <button id="btnCancelar" class="rounded-2xl bg-slate-800 px-4 py-2 text-sm font-medium text-slate-200 hover:bg-slate-700">Cancelar</button>
          <button id="btnConfirmar" class="rounded-2xl bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-500">Confirmar</button>
        </div>
      </div>`;

    document.body.appendChild(overlay);

    overlay.querySelector('#btnCancelar').addEventListener('click', () => {
      overlay.remove();
      resolve(false);
    });
    overlay.querySelector('#btnConfirmar').addEventListener('click', () => {
      overlay.remove();
      resolve(true);
    });
  });
}

// Valida los campos requeridos de un formulario antes de enviar.
function validarFormulario(form) {
  const campos = form.querySelectorAll('input[required], select[required], textarea[required]');
  for (const campo of campos) {
    const valor = campo.value.trim();
    if (!valor) {
      campo.classList.add('border-red-500', 'ring-1', 'ring-red-500');
      campo.focus();
      mostrarToast(`El campo "${campo.name || campo.id}" es obligatorio`, 'error');
      return false;
    }
    campo.classList.remove('border-red-500', 'ring-1', 'ring-red-500');
  }
  return true;
}