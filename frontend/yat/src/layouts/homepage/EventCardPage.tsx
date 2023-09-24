import { EventCard } from './EventCard/EventCard';
import EventModel from '../../models/EventModel';
import OptionModel from '../../models/OptionModel';
import { useEffect, useRef, useState } from 'react';
import { SpinningLoading } from '../util/SpinningLoading';

export const EventCardPage = () => {

  const [event, setEvent] = useState<EventModel>(new EventModel(0, "", false, []));
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
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
  },[]);

  if (isLoading) {
    return (
      <SpinningLoading/>
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
    <EventCard event={event} setEvent={setEvent}/>
  )
}