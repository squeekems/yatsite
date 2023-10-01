import './Header.css';

export const Header = () => {
  const startNewGameSession = async () => {
    try {
      const url: string = `http://localhost:8080/game/newGame`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error ('Something went wrong resetting the game');
      }
      sessionStorage.clear();
      window.location.reload();
      console.log('new session response', response);
    } catch (error) {
      console.log(`Failed to start new session`, error);
    }  
  }

  return(
  <div className="game-nav">
    <button onClick={startNewGameSession} className="btn btn-brown btn-lg m-1">New Game</button>
    <p id="current-player-turn">Player: Current Player</p>
  </div>
  )
}