import { EventCard } from './EventCard/EventCard';
import EventModel from '../../models/EventModel';
import OptionModel from '../../models/OptionModel';
import { useEffect, useState } from 'react';
import { SpinningLoading } from '../util/SpinningLoading';

export const EventCardPage = () => {

  const [event, setEvent] = useState<EventModel>(
    new EventModel(
      0,
      "Where do we find you in the tavern at this time?", 
      false, 
      [
        new OptionModel(
          0,
          "Bar",
          new EventModel(
            1,
            "Grab a Tankard and have a seat!",
            false,
            []
          )
        ),
        new OptionModel(
          1,
          "Dining Room",
          new EventModel(
            2,
            "Get a Knife ready for the meal to come.",
            false,
            []
          )
        ),
        new OptionModel(
          2,
          "Library",
          new EventModel(
            3,
            "Ah. Looking for a good read? Grab that book over there: Tome of Bludgeoning. It is a good read. I promise.",
            false,
            []
          )
        ),
        new OptionModel(
          3,
          "My Room",
          new EventModel(
            4,
            "There is a bucket near the bed for... Y'know? If you need it.",
            false,
            []
          )
        )
      ]
    )
  );
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
    const fetchEvent = async () => {
      const baseUrl: string = 'http://groceriesbyrecipe.ddns.net:8393/events/get?id=';
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
    <EventCard prompt={event.prompt} options={event.options}/>
  )
}