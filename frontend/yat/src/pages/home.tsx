// import { EventCard } from '../EventCard/EventCard';
import EventModel from '../models/EventModel';
import { useEffect, useRef, useState } from 'react';
import { SpinningLoading } from '../util/SpinningLoading';
import './home.css';
import { GameSetup } from '../components/GameSetup/GameSetup';
export const HomePage = () => {

  const [event, setEvent] = useState<EventModel>(new EventModel(0, "", false, []));
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  const [ displayGameSetup, setDisplayGameSetup ] = useState(true);

  useEffect(() => {
    startGame();
    fetchData();
    setIsLoading(false);
  }, []);

  // use event number 309 on initial load
  const fetchData = async (eventId: number = 309): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/game/event?id=${eventId}`;
      console.log('url', url)
      const response = await fetch(url);
      setEvent(await processData(response))
    } catch (error) {
      console.log(`Failed to fetch from database`, error);
    }
  }

  const startGame = async (): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/game/start`;
      console.log('url', url)
      const response = await fetch(url);
      console.log(response)
    } catch (error) {
      console.log(`Failed to start game`, error);
    }
  }

  const processData = async (responseData: Response): Promise<EventModel> => {
    if (!responseData.ok) {
      throw new Error('Something went wrong!');
    }
    const responseJson = await responseData.json();
    const loadedEvent: EventModel = responseJson;

    loadedEvent.options.sort((a, b) => a.optionId - b.optionId);
    console.log('loadedEvent', loadedEvent)
    return loadedEvent;
  }

  if (isLoading) {
    return (
      <SpinningLoading />
    )
  }

  if (httpError) {
    return (
      <div className='container m-5'>
        <p>{httpError}</p>
      </div>
    )
  }

  return (
    <>
      <div className='main'>
        <div className='box-div'>
          {/* start up prompts when user loads the game */}
          {displayGameSetup && <GameSetup gameEvent={event} fetchData={fetchData} />}
        </div>
      </div>
    </>

  )
}