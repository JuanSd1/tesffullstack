import { useEffect } from 'react';
import './Toast.css';

function Toast({ message, onClose }) {
  useEffect(() => {
    const timer = setTimeout(onClose, 3000);
    return () => clearTimeout(timer);
  }, [onClose]);

  return (
    <div className="toast">
      <span className="toast-icon">✓</span>
      <span className="toast-message">{message}</span>
    </div>
  );
}

export default Toast;
