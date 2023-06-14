import React, { useState, useEffect } from 'react';
import './index.css';
import axios from 'axios';
import './App.css';
import Dropdown from './Dropdown';

function Box({ value, onClick }) {
  return (
    <button className='box' onClick={onClick}>
      {value}
    </button>
  );
}

function Refresh({ onClick }) {
  return (
    <button className='refresh ref' onClick={onClick}>
      Refresh
    </button>
  );
}

function StartGame({ title, onClick, disabled }) {
  return (
    <button className='refresh' onClick={onClick} disabled={disabled}>
      {title}
    </button>
  );
}
function App() {
  const [boxes, setSquares] = useState(Array(9).fill(null));
  const [isXNext, setIsXNext] = useState(true);
  const [isActiveGame, setActiveGame] = useState(false);
  const [currMove, setCurrMove] = useState([]);
  const [selectedGame, setSelectedGame] = useState(0);
  const [label, setLabel] = useState('---------');
  const nextSymbol = isXNext ? 'X' : 'O';

  const winner = calculateWinner(boxes);

  function renderBox(i) {
    return (
      <Box
        value={boxes[i]}
        onClick={() => {
          if (selectedGame == 1 && nextSymbol == 'O') {
            if (boxes[i] != null || winner != null) {
              return;
            }
            const nextBoxes = boxes.slice();
            nextBoxes[i] = isXNext ? 'X' : 'O';

            let lebelGen = [];
            nextBoxes.map((i) => {
              if (i == null) {
                lebelGen.push('-');
              } else {
                lebelGen.push(i);
              }
            });

            setLabel(lebelGen.join(''));

            setSquares(nextBoxes);

            setIsXNext(!isXNext); // toggle turns
          } else {
            alert(selectedGame == 0 ? 'Please press Start R Vs M Game!':  `Menace's Move! Please press Start H Vs M Game or ${nextSymbol}'s Move!`);
          }
        }}
      />
    );
  }
  function calculateWinner(boxes) {
    const possibleLines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];
    // go over all possibly winning lines and check if they consist of only X's/only O's
    for (let i = 0; i < possibleLines.length; i++) {
      const [a, b, c] = possibleLines[i];
      if (boxes[a] && boxes[a] === boxes[b] && boxes[a] === boxes[c]) {
        return boxes[a];
      }
    }
    return null;
  }

  function isBoardFull(boxes) {
    for (let i = 0; i < boxes.length; i++) {
      if (boxes[i] == null) {
        return false;
      }
    }
    return true;
  }

  function getStatus() {
    if (winner) {
      let winString = `Winner: ${
        winner == 'X' ? 'MENACE' : selectedGame == 0 ? 'RANDOM' : 'HUMAN'
      }`;
      return winString;
    } else if (isBoardFull(boxes)) {
      return 'Draw!';
    } else {
      return 'Next player: ' + (isXNext ? 'X' : 'O');
    }
  }

  function renderRestartButton() {
    return (
      <Refresh
        onClick={() => {
          setSquares(Array(9).fill(null));
          setIsXNext(true);
          setActiveGame(false);
          setLabel('---------');
        }}
      />
    );
  }

  function renderInitiateBtn() {
    return (
      <StartGame
        title={isActiveGame ? `${nextSymbol}'s Move` : 'Start R Vs M Game'}
        disabled={btnDisabled()}
        onClick={async () => {
          let moves = [];
          let url = `http://localhost:8080/menace/next/${label}/${nextSymbol}`;

          setActiveGame(true);
          await axios
            .get(url)
            .then((res) => {
              moves = [...res.data];
            })
            .catch((e) => console.log(e, 'error'));

          moves.map((item) => {
            if (boxes[item.cellNumber - 1] != null || winner != null) {
              return;
            }
            const nextBoxes = boxes.slice();
            nextBoxes[item.cellNumber - 1] = item.player;
            let labelGen = [];
            nextBoxes.map((i) => {
              if (i == null) {
                labelGen.push('-');
              } else {
                labelGen.push(i);
              }
            });

            setLabel(labelGen.join(''));

            setSquares(nextBoxes);

            setIsXNext(!isXNext); // toggle turns
            renderBox(item.cellNumber - 1);
          });
        }}
      />
    );
  }

  const btnDisabled = () => {
    let flag = false;
    if (selectedGame == 0) {
      flag = winner || isBoardFull(boxes) ? true : false;
    } else {
      flag = nextSymbol == 'O' || (winner || isBoardFull(boxes)) ? true : false;
    }
    return flag;
  };

  function renderHumanMode() {
    return (
      <StartGame
        title={isActiveGame ? `${nextSymbol}'s Move` : 'Start H Vs M Game'}
        disabled={btnDisabled()}
        onClick={async () => {
          let moves = [];
          let url = `http://localhost:8080/menace/next/${label}/${nextSymbol}`;
          setActiveGame(true);
          await axios
            .get(url)
            .then((res) => {
              moves = [...res.data];
            })
            .catch((e) => console.log(e, 'error'));

          moves.map((item) => {
            if (boxes[item.cellNumber - 1] != null || winner != null) {
              return;
            }
            const nextBoxes = boxes.slice();
            nextBoxes[item.cellNumber - 1] = item.player;
            let labelGen = [];
            nextBoxes.map((i) => {
              if (i == null) {
                labelGen.push('-');
              } else {
                labelGen.push(i);
              }
            });

            setLabel(labelGen.join(''));

            setSquares(nextBoxes);

            setIsXNext(!isXNext); // toggle turns
            renderBox(item.cellNumber - 1);
          });
        }}
      />
    );
  }
  function getCurrentGame(selectedGame) {
    setSelectedGame(selectedGame);
    setSquares(Array(9).fill(null));
    setIsXNext(true);
    setActiveGame(false);
    setLabel('---------');
  }

  return (
    <div className='container'>
      <h3> MENACE - a Machine Educable Noughts And Crosses Engine </h3>
      <div className='wrapper'>
        <div> Select Your Game: &nbsp; </div>
        <Dropdown getCurrentGame={getCurrentGame}  />
      </div>
      <div className='game'>
        <div className='game-board'>
          <div className='board-row'>
            {renderBox(0)}
            {renderBox(1)}
            {renderBox(2)}
          </div>
          <div className='board-row'>
            {renderBox(3)}
            {renderBox(4)}
            {renderBox(5)}
          </div>
          <div className='board-row'>
            {renderBox(6)}
            {renderBox(7)}
            {renderBox(8)}
          </div>
        </div>
        <div className={!winner ? 'game-info' : 'win-state'}>{getStatus()}</div>
        <div className='wrapper'>
          <div className='refresh-button'>{renderRestartButton()}</div>
          {selectedGame == 0 ? (
            <div className='refresh-button'>{renderInitiateBtn()}</div>
          ) : (
            <div className='refresh-button'>{renderHumanMode()}</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
