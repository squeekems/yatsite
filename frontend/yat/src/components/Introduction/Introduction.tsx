import { useState } from 'react';

import { Message } from "../Message/Message";
import { ConinueButton } from '../ContinueButton/ContinueButton';

export const Introduction = (
  {
    gameIntro,
    startGame
  }:
    {
      gameIntro: string,
      startGame: () => void
    }
) => {
  const [displayUsernameInput, setDisplayUsernameInput] = useState(false);
  const [showGreetTraveler, setShowGreetTraveler] = useState(false);
  const [username, setUsername] = useState('');

  return (
    <>
      <div className='event-card'>
        <Message
          displayUsernameInput={displayUsernameInput}
          showGreetTraveler={showGreetTraveler}
          username={username}
          prompt={gameIntro}
        />

        <div className='d-flex justify-content-around'>
          <div className='button-container d-flex flex-column'>
            <ConinueButton clickContinueHandler={startGame} />
          </div>
        </div>
      </div>
    </>
  )
}