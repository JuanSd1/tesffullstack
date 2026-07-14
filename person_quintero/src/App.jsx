import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PersonList from './components/PersonList';
import PersonForm from './components/PersonForm';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<PersonList />} />
        <Route path="/create" element={<PersonForm />} />
        <Route path="/edit/:id" element={<PersonForm />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
