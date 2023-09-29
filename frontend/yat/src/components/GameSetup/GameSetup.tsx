import { useState } from 'react';

import EventModel from "../../models/EventModel";
import { Message } from "../Message/Message";
import { UserOptions } from '../UserOptions/UserOptions';

export const GameSetup = (
  {
    gameEvent,
    fetchData,
    getIntro
  }:
    {
      gameEvent: EventModel,
      fetchData: (id: number) => Promise<void>,
      getIntro: () => void
    }
) => {
  const [displayUsernameInput, setDisplayUsernameInput] = useState(false);
  const [showGreetTraveler, setShowGreetTraveler] = useState(false);
  const [username, setUsername] = useState('');

  const showUsernameInput = (display: boolean) => {
    setDisplayUsernameInput(display);
  }


  const updateUsername = (name: string) => {
    setUsername(name);
  }
  
  const resetUsername = () => {
    setUsername('');
  }

  const greetPlayer = () => {
    setShowGreetTraveler(false);
  }

  // When user submits username, greet them.
  const updateDisplayStates = () => {
    setDisplayUsernameInput(false);
    setShowGreetTraveler(true);
   }


  return (
    <>
      <div className='event-card'>
        <Message
          displayUsernameInput={displayUsernameInput}
          showGreetTraveler={showGreetTraveler}
          username={username}
          prompt={gameEvent.prompt}
        />

        <div className='d-flex justify-content-around'>
          <div className='button-container d-flex flex-column'>
            <UserOptions
              displayUsernameInput={displayUsernameInput}
              showGreetTraveler={showGreetTraveler}
              username={username}
              gameEvent={gameEvent}
              updateUsername={updateUsername}
              showUsernameInput={showUsernameInput}
              resetUsername={resetUsername}
              fetchData={fetchData}
              handleContinueBtn={greetPlayer}
              updateDisplayStates={updateDisplayStates}
              getIntro={getIntro}
            />
          </div>
        </div>
      </div>
    </>
  )
}