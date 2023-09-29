import { useEffect, useState } from 'react';

import { Message } from "../Message/Message";
import { ConinueButton } from '../ContinueButton/ContinueButton';

export const Introduction = (
  {
    startGame
  }:
    {
      startGame: () => void
    }
) => {
  const [displayUsernameInput, setDisplayUsernameInput] = useState(false);
  const [showGreetTraveler, setShowGreetTraveler] = useState(false);
  const [username, setUsername] = useState('');
  const [showIntro, setShowIntro] = useState(false);
  const [httpError, setHttpError] = useState('');
  const [intro, setIntro] = useState('');

  useEffect(() => {
    getIntro();
  }, []);

  const getIntro = async (): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/game/intro`;
      console.log('intro url', url)
      const response = await fetch(url);
      console.log(response)
      setIntro(await processIntro(response));
    } catch (error) {
      console.log(`Failed to fetch from database`, error);
    }
  }

  const processIntro = async (responseData: Response): Promise<string> => {
    if (!responseData.ok) {
      setHttpError('An error has occurred');
      throw new Error('Something went wrong!');
    }
    const responseText = await responseData.text(); //await responseData.json();

    const loadedIntro: string = responseText;

    console.log('loadedIntro', loadedIntro)
    return loadedIntro;
  }

  return (
    <>
      <div className='event-card'>
        <Message
          displayUsernameInput={displayUsernameInput}
          showGreetTraveler={showGreetTraveler}
          username={username}
          prompt={intro}
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