import { useState } from 'react';

import EventModel from "../../models/EventModel";
import OptionModel from "../../models/OptionModel";
import { OptionButton } from "../OptionButton/OptionButton";
import { Message } from "../Message/Message";
import { UserOptions } from '../UserOptions/UserOptions';

export const GameSetup = (
  {
    gameEvent,
    fetchData
  }:
    {
      gameEvent: EventModel,
      fetchData: (id: number) => Promise<void>
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

  const handleContinueBtn = () => {
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
              handleContinueBtn={handleContinueBtn}
              updateDisplayStates={updateDisplayStates}
            />
          </div>
        </div>
      </div>
    </>
  )
}