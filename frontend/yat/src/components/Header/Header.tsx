import './Header.css';

export const Header = () => {
  return(
  <div className="game-nav">
    <button className="btn btn-brown btn-lg m-1">New Game</button>
    <p id="current-player-turn">Player: Current Player</p>
  </div>
  )
}