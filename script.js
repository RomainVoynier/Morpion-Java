const board = document.getElementById('board');
const cells = Array.from(document.querySelectorAll('.cell'));
const status = document.getElementById('status');
const resetBtn = document.getElementById('reset');

let turn = 'x';

function updateStatus() {
  status.textContent = `Joueur: ${turn.toUpperCase()}`;
}

function placePiece(cell) {
  if (cell.classList.contains('x') || cell.classList.contains('o')) return;
  cell.classList.add(turn);
  const span = document.createElement('span');
  span.className = 'piece';
  cell.appendChild(span);
  turn = turn === 'x' ? 'o' : 'x';
  updateStatus();
}

cells.forEach(c => c.addEventListener('click', () => placePiece(c)));

resetBtn.addEventListener('click', () => {
  cells.forEach(c => {
    c.classList.remove('x', 'o');
    c.innerHTML = '';
  });
  turn = 'x';
  updateStatus();
});

updateStatus();
