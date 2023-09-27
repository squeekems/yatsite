// import { EventCard } from '../EventCard/EventCard';
import EventModel from '../models/EventModel';
import OptionModel from '../models/OptionModel';
import { useEffect, useRef, useState } from 'react';
import { SpinningLoading } from '../util/SpinningLoading';

import { OptionButton } from "../components/OptionButton/OptionButton";

import './home.css';
import { Message } from '../components/Message/Message';
import { UserOptions } from '../components/UserOptions/UserOptions';
export const HomePage = () => {

  const [event, setEvent] = useState<EventModel>(new EventModel(0, "", false, []));
  const [displayUsernameInput, setDisplayUsernameInput] = useState(false);
  const [showGreetTraveler, setShowGreetTraveler] = useState(false);
  const [username, setUsername] = useState('');
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
    startGame();
    fetchData();
    setIsLoading(false);
    setDisplayUsernameInput(false);
    setUsername('');
    setShowGreetTraveler(false);
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

  const showUsernameInput = (display: boolean) => {
    setDisplayUsernameInput(display);
  }

  const resetUsername = () => {
    setUsername('');
  }

  const handleContinueBtn = () => {
    setShowGreetTraveler(false);
  }

  const updateUsername = (name: string) => {
    setUsername(name);
  }

  // When user submits username, greet them.
  const updateDisplayStates = () => {
   setDisplayUsernameInput(false);
   setShowGreetTraveler(true);
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
    //<EventCard event={event} setEvent={setEvent}/>
    <>
      <div className='main'>
        <div className='box-div'>
          <div className='event-card'>
            <Message
              displayUsernameInput={displayUsernameInput}
              showGreetTraveler={showGreetTraveler}
              username={username}
              prompt={event.prompt}
            />

            <div className='d-flex justify-content-around'>
              <div className='button-container d-flex flex-column'>
                <UserOptions
                  displayUsernameInput={displayUsernameInput}
                  showGreetTraveler={showGreetTraveler}
                  username={username}
                  gameEvent={event}
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
        </div>
      </div>
    </>

  )
}