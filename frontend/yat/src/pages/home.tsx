import EventModel from '../models/EventModel';
import { useEffect, useState } from 'react';
import { SpinningLoading } from '../util/SpinningLoading';
import './home.css';
import { GameSetup } from '../components/GameSetup/GameSetup';
import { Introduction } from '../components/Introduction/Introduction';
import { Game } from '../components/Game/Game';
import { Header } from '../components/Header/Header';
import { useSessionStorage } from '../hooks/useSessionStorage';

export const HomePage = () => {
  const [sessionStarted, setSessionStarted] = useSessionStorage('sessionStarted', false);
  const [event, setEvent] =  useSessionStorage('currentEvent', (new EventModel(0, "", false, [])));
  const [displayGameSetup, setDisplayGameSetup] = useSessionStorage('displayGameSetup', true);
  const [showIntro, setShowIntro] = useSessionStorage('showIntro', false);
  const [startGamePlay, setStartGamePlay] = useSessionStorage('startGamePlay', false);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState('');

  useEffect(() => {
    if (!sessionStarted) {
      startGame();
      fetchData();
      setSessionStarted(true);
    }
    setIsLoading(false);
  }, []);

  // use event number 309 on initial load
  const fetchData = async (eventId: number = 309): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/game/event?id=${eventId}`;
      console.log('fetch data url', url)
      const response = await fetch(url);
      setEvent(await processData(response));
    } catch (error) {
      console.log(`Failed to fetch from database`, error);
    }
  }

  const startGame = async (): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/game/start`;
      console.log('start game url', url)
      const response = await fetch(url);
      console.log(response)
    } catch (error) {
      console.log(`Failed to start game`, error);
    }
  }

  const processData = async (responseData: Response): Promise<EventModel> => {
    if (!responseData.ok) {
      setHttpError('An error has occurred');
      throw new Error('Something went wrong!');
    }
    console.log(responseData)
    const responseJson = await responseData.json();
    console.log('responseJson', responseJson)
    const loadedEvent: EventModel = responseJson;

    loadedEvent.options.sort((a, b) => a.optionId - b.optionId);
    console.log('loadedEvent', loadedEvent)
    return loadedEvent;
  }

  const continueToIntro = () => {
    setDisplayGameSetup(false);
    setShowIntro(true);
  }

  const continueToGame = () => {
    setShowIntro(false);
    setStartGamePlay(true);
  }

  return (
    <>
      <Header />
      {isLoading && <SpinningLoading />}

      {httpError && <div className='container m-5'><p>{httpError}</p></div>}

      <div className='main'>
        <div className='box-div'>
          {/* start up prompts when user loads the game */}
          {displayGameSetup && <GameSetup gameEvent={event} fetchData={fetchData} getIntro={continueToIntro} />}
          {showIntro && <Introduction startGame={continueToGame} />}
          {startGamePlay && <Game></Game>}
        </div>
      </div>
    </>

  )
}