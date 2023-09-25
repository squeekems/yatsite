import { useState } from "react";
import EventModel from "../../models/EventModel";
import OptionModel from "../../models/OptionModel"
import { OptionButton } from "../OptionButton/OptionButton"
import "./EventCard.css";
import ReactDOM, { render } from "react-dom";

export const EventCard = (props: {event: EventModel, setEvent: Function}) => {

  const optionButtonClickHandler = async (option: OptionModel) => {
    // console.log(`${option} button was pressed!`);
    console.log(option);
    const baseUrl: string = 'http://localhost:8080/events/get?id=';
    const eventId: number = option.resultId;
    const url: string = `${baseUrl}${eventId}`;
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error('Something went wrong!');
    }

    const responseJson = await response.json();
    const loadedEvent: EventModel = responseJson;

    loadedEvent.options.sort((a, b) => a.optionId - b.optionId);

    console.log(responseJson);
    switch (eventId) {
      case 310:
      case 311:
      case 312:
      case 313:
        return (
          <div className='main'>
            <div className='box-div'>
              <div className='event-card'>
                <div>
                  <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
                  <h5 className='card-text display-2 mb-5'>{props.event.prompt}</h5>
                </div>
                <div className="d-flex justify-content-around">
                  <div className='button-container d-flex flex-column'>
                    <form onSubmit={handleSubmit}>
                      <input type="text" id="username" name="username" value={username} 
                        onChange={event => setUsername(event.target.value)}></input>
                      <button type="submit">Submit</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        );
    };
    props.setEvent(loadedEvent);
  }
  const [username, setUsername] = useState('');
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    console.log(event.currentTarget.elements);
    console.log(event.currentTarget.elements[0]);
    // const baseUrl: string = 'http://localhost:8080/players/post?';
    // const url: string = `${baseUrl}room=${props.optionResultId}username=${props.username}`;
  }

  return (
    <div className='main'>
      <div className='box-div'>
        <div className='event-card'>
          <div>
            <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
            <h5 className='card-text display-2 mb-5'>{props.event.prompt}</h5>
          </div>
          <div className="d-flex justify-content-around">
            <div className='button-container d-flex flex-column'>
              {/*props.event.options.map((optionModel: OptionModel) => (
                <OptionButton
                  key={optionModel.optionId}
                  optionModel={optionModel}
                  optionButtonClickHandler={optionButtonClickHandler}
                />
              ))*/}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}