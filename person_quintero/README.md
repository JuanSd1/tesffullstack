# frontend_quintero

Aplicación frontend desarrollada con React + Vite para la gestión de personas (CRUD).

## Requisitos

| Herramienta | Versión mínima |
|-------------|----------------|
| Node.js     | 16.x           |
| npm         | 8.x            |

> El backend debe estar corriendo en `http://localhost:8080` antes de iniciar el frontend.

## Instalación

```bash
npm install
```

## Ejecución

```bash
npm run dev
```

La app inicia en `http://localhost:5173`.

## Funcionalidades

- **Listado de personas** — muestra todas las personas registradas en la base de datos
- **Crear persona** — formulario con validación para registrar una nueva persona
- **Editar persona** — formulario pre-cargado con los datos actuales
- **Eliminar persona** — modal de confirmación personalizado + notificación de éxito

## Rutas

| Ruta        | Descripción            |
|-------------|------------------------|
| `/`         | Listado de personas    |
| `/create`   | Formulario de creación |
| `/edit/:id` | Formulario de edición  |

## Estructura del proyecto

```
src/
├── components/
│   ├── PersonList.jsx      → Lista de personas
│   ├── PersonList.css
│   ├── PersonForm.jsx      → Formulario crear/editar
│   ├── PersonForm.css
│   ├── ConfirmModal.jsx    → Modal de confirmación al eliminar
│   ├── ConfirmModal.css
│   ├── Toast.jsx           → Notificación de éxito
│   └── Toast.css
├── services/
│   └── personService.js    → Llamadas HTTP al backend
├── App.jsx                 → Configuración de rutas
├── main.jsx
└── index.css
```

## Conexión con el backend

La URL base se configura en `src/services/personService.js`:

```js
const BASE_URL = 'http://localhost:8080/api/persons';
```

Si el backend corre en otro puerto, actualizá esa constante.
