// js/services/storageCache.js
const _cache = {};
const _timers = {};
const _indexId = {};

function parseJSON(value, fallback = []) {
  if (!value) return fallback;
  try {
    return JSON.parse(value) || fallback;
  } catch (error) {
    console.warn('Invalid JSON in localStorage key:', error);
    return fallback;
  }
}

function getCache(key) {
  if (!_cache[key]) _cache[key] = parseJSON(localStorage.getItem(key), []);
  return _cache[key];
}

function getIndex(key) {
  if (!_indexId[key]) {
    _indexId[key] = new Map(getCache(key).map(item => [item.id, item]));
  }
  return _indexId[key];
}

function invalidateIndex(key) {
  _indexId[key] = null;
}

function scheduleWrite(key, delay = 300) {
  clearTimeout(_timers[key]);
  _timers[key] = setTimeout(() => {
    localStorage.setItem(key, JSON.stringify(_cache[key]));
  }, delay);
}

function flushWrite(key) {
  clearTimeout(_timers[key]);
  localStorage.setItem(key, JSON.stringify(_cache[key]));
}

window.addEventListener('beforeunload', () => {
  Object.keys(_timers).forEach(flushWrite);
});