import { useState } from "react";
// import { OptionButton } from "./OptionButton/OptionButton"
// import "./EventCard.css";
import ReactDOM, { render } from "react-dom";

export const OpenQCard = (props: {question: String, setUsername: Function}) => {
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
            <h5 className='card-text display-2 mb-5'>{props.question}</h5>
          </div>
          <div className="d-flex justify-content-around">
            <div className='button-container d-flex flex-column'>
              <form onSubmit={handleSubmit}>
                {/* <input type="text" id="username" name="username" value={username}
                  onChange={event => setUsername(event.target.value)}></input> */}
                <button type="submit">Submit</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}