import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { personService } from '../services/personService';
import './PersonForm.css';

const emptyForm = {
  nombre: '',
  apellido: '',
  fechaNacimiento: '',
  puesto: '',
  sueldo: '',
};

function PersonForm() {
  const [form, setForm] = useState(emptyForm);
  const [error, setError] = useState(null);
  const [saving, setSaving] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();
  const isEditing = Boolean(id);

  useEffect(() => {
    if (!isEditing) return;
    personService.getById(id).then((response) => {
      if (response.status) {
        const p = response.data[0];
        setForm({
          nombre: p.nombre,
          apellido: p.apellido,
          fechaNacimiento: p.fechaNacimiento,
          puesto: p.puesto,
          sueldo: p.sueldo,
        });
      } else {
        setError(response.msg);
      }
    });
  }, [id, isEditing]);

  const handleChange = ({ target: { name, value } }) => {
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSaving(true);
    try {
      const payload = { ...form, sueldo: parseFloat(form.sueldo) };
      const response = isEditing
        ? await personService.update(id, payload)
        : await personService.create(payload);

      if (response.status) navigate('/');
      else setError(response.msg);
    } catch {
      setError('Error al guardar. Verificá que el backend esté corriendo.');
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="form-container">
      <div className="form-card">
        <h1>{isEditing ? 'Editar Persona' : 'Crear Persona'}</h1>

        {error && <div className="form-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Nombre</label>
            <input
              name="nombre"
              value={form.nombre}
              onChange={handleChange}
              placeholder="Ingresá el nombre"
              required
            />
          </div>

          <div className="form-group">
            <label>Apellido</label>
            <input
              name="apellido"
              value={form.apellido}
              onChange={handleChange}
              placeholder="Ingresá el apellido"
              required
            />
          </div>

          <div className="form-group">
            <label>Fecha de Nacimiento</label>
            <input
              type="date"
              name="fechaNacimiento"
              value={form.fechaNacimiento}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Puesto</label>
            <input
              name="puesto"
              value={form.puesto}
              onChange={handleChange}
              placeholder="Ingresá el puesto"
              required
            />
          </div>

          <div className="form-group">
            <label>Sueldo</label>
            <input
              type="number"
              step="0.01"
              min="0"
              name="sueldo"
              value={form.sueldo}
              onChange={handleChange}
              placeholder="0.00"
              required
            />
          </div>

          <div className="form-actions">
            <button type="submit" className="btn btn-primary" disabled={saving}>
              {saving ? 'Guardando...' : 'Guardar'}
            </button>
            <button
              type="button"
              className="btn btn-cancel"
              onClick={() => navigate('/')}
            >
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default PersonForm;
