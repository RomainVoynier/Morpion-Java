const board = document.getElementById('board');
const cells = Array.from(document.querySelectorAll('.cell'));
const status = document.getElementById('status');
const resetBtn = document.getElementById('reset');
const scoreXSpan = document.getElementById('score-x');
const scoreOSpan = document.getElementById('score-o');
const scoreDrawSpan = document.getElementById('score-draw');

let turn = 'x';
let gameOver = false;
let scoreX = 0;
let scoreO = 0;
let draws = 0;

const winningCombinations = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6]
];

function getPlayerNumber(player) {
  return player === 'x' ? '1' : '2';
}

function updateStatus() {
  const playerNum = getPlayerNumber(turn);
  status.textContent = `Joueur: ${playerNum}`;
}

function checkWinner() {
  for (let combination of winningCombinations) {
    const [a, b, c] = combination;
    const cellA = cells[a];
    const cellB = cells[b];
    const cellC = cells[c];
    
    if (
      cellA.classList.contains(turn) &&
      cellB.classList.contains(turn) &&
      cellC.classList.contains(turn)
    ) {
      return true;
    }
  }
  return false;
}

function isBoardFull() {
  return cells.every(cell => cell.classList.contains('x') || cell.classList.contains('o'));
}

function updateScoreboard() {
  scoreXSpan.textContent = scoreX;
  scoreOSpan.textContent = scoreO;
  scoreDrawSpan.textContent = draws;
}

function displayResult(result) {
  const resultDiv = document.createElement('div');
  resultDiv.style.cssText = `
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.3);
    text-align: center;
    z-index: 1000;
    border: 3px solid #4CAF50;
  `;
  
  resultDiv.innerHTML = `
    <h2 style="color: #4CAF50; margin: 0 0 15px 0;">${result}</h2>
    <button onclick="location.reload()" style="
      padding: 10px 20px;
      background: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    ">Nouvelle partie</button>
  `;
  
  document.body.appendChild(resultDiv);
  console.log(result);
}

function placePiece(cell) {
  if (gameOver || cell.classList.contains('x') || cell.classList.contains('o')) return;
  
  cell.classList.add(turn);
  const span = document.createElement('span');
  span.className = 'piece';
  cell.appendChild(span);
  
  if (checkWinner()) {
    gameOver = true;
    if (turn === 'x') {
      scoreX += 1;
    } else {
      scoreO += 1;
    }
    updateScoreboard();
    const playerNum = getPlayerNumber(turn);
    const resultMessage = `🎉 Victoire du joueur ${playerNum}!`;
    status.textContent = resultMessage;
    displayResult(resultMessage);
    return;
  }
  
  turn = turn === 'x' ? 'o' : 'x';
  if (isBoardFull()) {
    gameOver = true;
    draws += 1;
    updateScoreboard();
    const resultMessage = '🤝 Match nul! Aucun joueur n\'a gagné.';
    status.textContent = resultMessage;
    displayResult(resultMessage);
    return;
  }
  
  updateStatus();
}

cells.forEach(c => c.addEventListener('click', () => placePiece(c)));

resetBtn.addEventListener('click', () => {
  cells.forEach(c => {
    c.classList.remove('x', 'o');
    c.innerHTML = '';
  });
  turn = 'x';
  gameOver = false;
  updateStatus();
  
  const resultDiv = document.querySelector('div[style*="position: fixed"]');
  if (resultDiv) resultDiv.remove();
});

updateScoreboard();
updateStatus();
