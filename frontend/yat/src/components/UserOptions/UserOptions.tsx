import { useState } from "react";
import EventModel from "../../models/EventModel";
import OptionModel from "../../models/OptionModel";
import { ConinueButton } from "../ContinueButton/ContinueButton";
import { OptionButton } from "../OptionButton/OptionButton";

export const UserOptions = (
  {
    displayUsernameInput,
    showGreetTraveler,
    username,
    gameEvent,
    updateUsername,
    showUsernameInput,
    resetUsername,
    fetchData,
    handleContinueBtn,
    updateDisplayStates,
    startGame
  }:
    {
      displayUsernameInput: boolean,
      showGreetTraveler: boolean,
      username: string,
      gameEvent: EventModel,
      updateUsername: (name: string) => void,
      showUsernameInput: (display: boolean) => void
      resetUsername: () => void,
      fetchData: (id: number) => Promise<void>,
      handleContinueBtn: () => void,
      updateDisplayStates: (gameEvent: EventModel) => void,
      startGame: () => void
    }
) => {

  const [ readyToStartGame, setReadyToStartGame] = useState(false);

  const { eventId, options } = gameEvent;
  const handleSubmit = async (formEvent: React.FormEvent<HTMLFormElement>) => {
    formEvent.preventDefault();
    console.log(formEvent.currentTarget.elements);
    console.log(formEvent.currentTarget.elements[0]);
    const baseUrl: string = 'http://localhost:8080/players/post?';
    const url: string = `${baseUrl}room=${eventId}&username=${username}`;
    try {
      const response = await fetch(url, { method: 'POST' });
      if (!response.ok) {
        throw new Error('Something went wrong with creating a username');
      }
      console.log('success!', response)
      updateDisplayStates(gameEvent);
    } catch (error) {
      console.log(`Failed to post to database`, error);
    }
  }

  const handleUserOptionSelection = (resultId: number) => {
    promptUsername(resultId);
    noMorePlayers(resultId);
    fetchData(resultId);
  }

  const promptUsername = (resultId: number) => {
    const roomResultIds = [310, 311, 312, 313];
    if (roomResultIds.some(id => id === resultId)) {
      showUsernameInput(true);
      resetUsername();
    }
  }

  const noMorePlayers = (resultId: number) => {
    if (resultId === 307) {
      setReadyToStartGame(true);
    }
  }

  return (
    <>
      {!readyToStartGame && displayUsernameInput &&
        // Show username input form
        <form onSubmit={handleSubmit}>
          <input type="text" id="username" name="username" value={username}
            onChange={event => updateUsername(event.target.value)}></input>
          <p>{username}</p>
          <button className='btn btn-brown btn-lg m-1' type="submit">Submit</button>
        </form>
      }
      {!readyToStartGame && showGreetTraveler &&
        // Greet player and allow to continue
        <ConinueButton clickContinueHandler={handleContinueBtn} />
      }
      {!readyToStartGame && !displayUsernameInput && !showGreetTraveler &&
      // Show game event options
        options.map((optionModel: OptionModel) => (
          <OptionButton
            key={optionModel.optionId}
            optionModel={optionModel}
            optionButtonClickHandler={handleUserOptionSelection}
          />
        ))
      }
      {readyToStartGame &&
        // No more players to add; show Continue button to start game
        <ConinueButton clickContinueHandler={startGame}/>
      }
    </>
  )
}