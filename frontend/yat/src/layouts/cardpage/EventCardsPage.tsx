import { useEffect, useState } from "react";
import EventModel from "../../models/EventModel";
import { SpinningLoading } from "../util/SpinningLoading";

export const EventCardsPage = () => {

  const [events, setEvents] = useState<EventModel[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
    const fetchEvents = async () => {
      const baseUrl: string = 'http://localhost:8080/api/events';
      const url: string = `${baseUrl}?page=0&size=9`;
      const response = await fetch(url);

      if (!response.ok) {
        throw new Error('Something went wrong!');
      }

      const responseJson = await response.json();
      const responseData = responseJson._embedded.events;
      const loadedEvents: EventModel[] = [];

      for (const key in responseData) {
        loadedEvents.push({
          eventId: responseData[key].eventId,
          prompt: responseData[key].prompt,
          isCard: responseData[key].isCard,
          options: responseData[key].options
        })
      }

      setEvents(loadedEvents);
      setIsLoading(false);
    };
    fetchEvents().catch((error: any) => {
      setIsLoading(false);
      setHttpError(error.message);
    })
  }, []);

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
    <table>

    </table>
  )
}