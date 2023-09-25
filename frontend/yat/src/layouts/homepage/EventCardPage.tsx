import { EventCard } from '../EventCard/EventCard';
import EventModel from '../../models/EventModel';
import OptionModel from '../../models/OptionModel';
import { useEffect, useRef, useState } from 'react';
import { SpinningLoading } from '../../util/SpinningLoading';

import { OptionButton } from "../OptionButton/OptionButton"
import '../EventCard/EventCard.css';
export const EventCardPage = () => {

  const [event, setEvent] = useState<EventModel>(new EventModel(0, "", false, []));
  const [displayUsernameInput, setDisplayUsernameInput] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  /*useEffect(() => {
    const fetchEvent = async () => {
      // const baseUrl: string = 'http://groceriesbyrecipe.ddns.net:8393/events/get?id=';
      const baseUrl: string = 'http://localhost:8080/events/get?id=';
      const eventId: number = 309;
      const url: string = `${baseUrl}${eventId}`;
      const response = await fetch(url);

      if (!response.ok) {
        throw new Error('Something went wrong!');
      }

      const responseJson = await response.json();
      const loadedEvent: EventModel = responseJson;

      loadedEvent.options.sort((a, b) => a.optionId - b.optionId);

      console.log(responseJson);

      setEvent(loadedEvent);
      setIsLoading(false);
    };

    fetchEvent().catch((error: any) => {
      setIsLoading(false);
      setHttpError(error.message);
    })
  },[]);*/

  useEffect(() => {
    fetchData();
    setIsLoading(false);
    setDisplayUsernameInput(false);
    setUsername('');
  }, []);

  // use event number 309 on initial load
  const fetchData = async (eventId: number = 309): Promise<void> => {
    try {
      const url: string = `http://localhost:8080/events/get?id=${eventId}`;
      console.log('url', url)
      const response = await fetch(url);
      setEvent(await processData(response))
    } catch (error) {
      console.log(`Failed to fetch from database`, error);
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

  const [username, setUsername] = useState('');
  const handleSubmit = async (formEvent: React.FormEvent<HTMLFormElement>) => {
    console.log('EVENT', event)
    formEvent.preventDefault();
    // setUsername('');
    console.log(formEvent.currentTarget.elements);
    console.log(formEvent.currentTarget.elements[0]);
    const baseUrl: string = 'http://localhost:8080/players/post?';
    const url: string = `${baseUrl}room=${event.eventId}&username=${username}`;
    try {
      //const response = await fetch(url, { method: 'POST' });
      /*if (!response.ok) {
        throw new Error('Something went wrong with creating a username');
      }
      console.log('success!', response)*/
      setDisplayUsernameInput(false);
      setEvent(event);
    } catch (error) {
      console.log(`Failed to post to database`, error);
    }
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
      {!displayUsernameInput && <div className='main'>
        <div className='box-div'>
          <div className='event-card'>
            <div>
              <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
              <h5 className='card-text display-2 mb-5'>{event.prompt}</h5>
            </div>

            <div className="d-flex justify-content-around">
              <div className='button-container d-flex flex-column'>
                {event.options.map((optionModel: OptionModel) => (
                  <OptionButton
                    key={optionModel.optionId}
                    optionModel={optionModel}
                    displayUsernameInput={showUsernameInput}
                    resetUsername={resetUsername}
                    optionButtonClickHandler={fetchData}
                  />
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>}
      {displayUsernameInput && <div className='main'>
        <div className='box-div'>
          <div className='event-card'>
            <div>
              <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
              <h5 className='card-text display-2 mb-5'>What is your name, Traveler?</h5>
            </div>
            <div className='d-flex justify-content-around'>
              <div className='button-container d-flex flex-column'>
                <form onSubmit={handleSubmit}>
                  <input type="text" id="username" name="username" value={username}
                    onChange={event => setUsername(event.target.value)}></input>
                  <p>{username}</p>
                  <button className='btn btn-brown btn-lg m-1' type="submit">Submit</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>}
    </>

  )
}