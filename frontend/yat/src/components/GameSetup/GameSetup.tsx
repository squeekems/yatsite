import { useSessionStorage } from '../../hooks/useSessionStorage';

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
  const [displayUsernameInput, setDisplayUsernameInput] = useSessionStorage('displayUsernameInput', false);
  const [showGreetTraveler, setShowGreetTraveler] = useSessionStorage('showGreetTraveler', false);
  const [username, setUsername] = useSessionStorage('username', '');

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