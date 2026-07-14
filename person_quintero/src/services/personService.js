const BASE_URL = 'http://localhost:8080/api/persons';

const headers = { 'Content-Type': 'application/json' };

export const personService = {
  getAll: () =>
    fetch(BASE_URL).then((res) => res.json()),

  getById: (id) =>
    fetch(`${BASE_URL}/${id}`).then((res) => res.json()),

  create: (person) =>
    fetch(BASE_URL, {
      method: 'POST',
      headers,
      body: JSON.stringify(person),
    }).then((res) => res.json()),

  update: (id, person) =>
    fetch(`${BASE_URL}/${id}`, {
      method: 'PUT',
      headers,
      body: JSON.stringify(person),
    }).then((res) => res.json()),

  remove: (id) =>
    fetch(`${BASE_URL}/${id}`, { method: 'DELETE' }).then((res) => res.json()),
};
