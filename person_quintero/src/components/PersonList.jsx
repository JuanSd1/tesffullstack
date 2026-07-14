import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { personService } from '../services/personService';
import ConfirmModal from './ConfirmModal';
import Toast from './Toast';
import './PersonList.css';
import './ConfirmModal.css';
import './Toast.css';

function PersonList() {
  const [persons, setPersons] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedId, setSelectedId] = useState(null);
  const [showToast, setShowToast] = useState(false);
  const navigate = useNavigate();

  const loadPersons = async () => {
    try {
      setLoading(true);
      const response = await personService.getAll();
      if (response.status) setPersons(response.data);
      else setError(response.msg);
    } catch {
      setError('Error al conectar con el servidor.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadPersons();
  }, []);

  const handleDeleteConfirm = async () => {
    try {
      const response = await personService.remove(selectedId);
      if (response.status) {
        loadPersons();
        setShowToast(true);
      } else {
        setError(response.msg);
      }
    } catch {
      setError('Error al eliminar la persona.');
    } finally {
      setSelectedId(null);
    }
  };

  if (loading) return <div className="feedback">Cargando...</div>;
  if (error) return <div className="feedback error">{error}</div>;

  return (
    <div className="list-container">
      <div className="list-header">
        <h1>Listado de Personas</h1>
        <button className="btn btn-primary" onClick={() => navigate('/create')}>
          + Crear
        </button>
      </div>

      {persons.length === 0 ? (
        <p className="empty">No hay personas registradas.</p>
      ) : (
        <div className="table-wrapper">
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Fecha Nacimiento</th>
                <th>Puesto</th>
                <th>Sueldo</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {persons.map((person) => (
                <tr key={person.id}>
                  <td>{person.id}</td>
                  <td>{person.nombre}</td>
                  <td>{person.apellido}</td>
                  <td>{person.fechaNacimiento}</td>
                  <td>{person.puesto}</td>
                  <td>${Number(person.sueldo).toLocaleString('es-MX')}</td>
                  <td className="actions">
                    <button
                      className="btn btn-edit"
                      onClick={() => navigate(`/edit/${person.id}`)}
                    >
                      Editar
                    </button>
                    <button
                      className="btn btn-delete"
                      onClick={() => setSelectedId(person.id)}
                    >
                      Borrar
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {showToast && (
        <Toast
          message="Persona eliminada correctamente"
          onClose={() => setShowToast(false)}
        />
      )}

      {selectedId && (
        <ConfirmModal
          message="¿Seguro que quieres eliminar esta persona? Esta acción no se puede deshacer."
          onConfirm={handleDeleteConfirm}
          onCancel={() => setSelectedId(null)}
        />
      )}
    </div>
  );
}

export default PersonList;
