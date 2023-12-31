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
    const responseText = await responseData.text();
    const loadedIntro: string = responseText;
    console.log('loadedIntro', loadedIntro)
    return loadedIntro;
  }

  return (
    <>
      {httpError && <p>{httpError}</p>}

      {!httpError && <div className='event-card'>
        <Message messageText={intro} />
        <div className='d-flex justify-content-around'>
          <div className='button-container d-flex flex-column'>
            <ConinueButton clickContinueHandler={startGame} />
          </div>
        </div>
      </div>
      }

    </>
  )
}