function ConfirmModal({ message, onConfirm, onCancel }) {
  return (
    <div className="modal-overlay" onClick={onCancel}>
      <div className="modal-box" onClick={(e) => e.stopPropagation()}>
        <h2 className="modal-title">Confirmar eliminación</h2>
        <p className="modal-message">{message}</p>
        <div className="modal-actions">
          <button className="btn btn-delete" onClick={onConfirm}>
            Eliminar
          </button>
          <button className="btn btn-cancel" onClick={onCancel}>
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
}

export default ConfirmModal;
