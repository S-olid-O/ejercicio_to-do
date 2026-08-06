// js/auth/auth.js

let _sesionCache = null;

function login(usuario, password) {
  const index = getIndex(STORAGE_KEYS.USUARIOS);
  let encontrado = null;

  for (const u of index.values()) {
    if (u.usuario === usuario && u.password === password) { encontrado = u; break; }
  }
  if (!encontrado) return { ok: false, msg: 'Credenciales inválidas' };

  _sesionCache = {
    id: encontrado.id,
    nombre: encontrado.nombre,
    usuario: encontrado.usuario,
    rol: encontrado.rol
  };
  localStorage.setItem(STORAGE_KEYS.SESION, JSON.stringify(_sesionCache));

  return { ok: true, rol: encontrado.rol };
}

function logout() {
  _sesionCache = null;
  localStorage.removeItem(STORAGE_KEYS.SESION);
  window.location.href = 'login.html';
}

function getSesion() {
  if (_sesionCache) return _sesionCache;
  _sesionCache = JSON.parse(localStorage.getItem(STORAGE_KEYS.SESION));
  return _sesionCache;
}

function getRol() {
  const sesion = getSesion();
  return sesion ? sesion.rol : null;
}

function isAutenticado() {
  return !!getSesion();
}

function protegerRuta() {
  if (!isAutenticado()) window.location.href = 'login.html';
}