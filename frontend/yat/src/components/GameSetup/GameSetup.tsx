import { useEffect, useState } from 'react';

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

  useEffect(() => {
    const showUsernameInput = sessionStorage.getItem('displayUsernameInput');
    const showGreeting = sessionStorage.getItem('showGreetingTraveler');
    const sessionUsername = sessionStorage.getItem('username') || '';
    console.log('SESSION USERNAME INPUT', showUsernameInput)
    setDisplayUsernameInput(showUsernameInput === 'true');
    setShowGreetTraveler(showGreeting === 'true');
    setUsername(sessionUsername);
    console.log('DISPLAY USERNAME INPUT', displayUsernameInput);
    console.log('SHOW GREETING', showGreetTraveler);
    console.log("USERNAME", username)
  }, []);

  const showUsernameInput = (display: boolean) => {
    setDisplayUsernameInput(display);
    sessionStorage.setItem('displayUsernameInput', `${display}`);
  }


  const updateUsername = (name: string) => {
    setUsername(name);
    sessionStorage.setItem('username', name);
  }

  const resetUsername = () => {
    setUsername('');
    sessionStorage.setItem('username', '');
  }

  const greetPlayer = () => {
    setShowGreetTraveler(false);
    sessionStorage.setItem('showGreeting', 'false');
  }

  // When user submits username, greet them.
  const updateDisplayStates = () => {
    setDisplayUsernameInput(false);
    setShowGreetTraveler(true);
    sessionStorage.setItem('displayUsernameInput', 'false');
    sessionStorage.setItem('showGreeting', 'true');
  }

  return (
    <>
      <div className='event-card'>
        {displayUsernameInput && !showGreetTraveler && <Message messageText={`What is your name, Traveler?`} />}
        {!displayUsernameInput && showGreetTraveler && <Message messageText={`Welcome to the tavern, ${username}`} />}
        {!displayUsernameInput && !showGreetTraveler && <Message messageText={gameEvent.prompt} />}

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