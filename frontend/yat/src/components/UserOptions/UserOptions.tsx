import EventModel from "../../models/EventModel";
import OptionModel from "../../models/OptionModel";
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
    updateDisplayStates
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
      updateDisplayStates: () => void
    }
) => {
  const { eventId, options } = gameEvent;
  const handleSubmit = async (formEvent: React.FormEvent<HTMLFormElement>) => {
    console.log('EVENT', gameEvent)
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
      updateDisplayStates();
    } catch (error) {
      console.log(`Failed to post to database`, error);
    }
  }

  

  return (
    <>
      {displayUsernameInput && !showGreetTraveler &&
      // Show username input form
        <form onSubmit={handleSubmit}>
          <input type="text" id="username" name="username" value={username}
            onChange={event => updateUsername(event.target.value)}></input>
          <p>{username}</p>
          <button className='btn btn-brown btn-lg m-1' type="submit">Submit</button>
        </form>
      }
      {!displayUsernameInput && showGreetTraveler &&
        // Show continue button
        <button
          className='btn btn-brown btn-lg m-1'
          onClick={handleContinueBtn}>Continue
        </button>
      }
      {!displayUsernameInput && !showGreetTraveler &&
        // Show game event options
        options.map((optionModel: OptionModel) => (
          <OptionButton
            key={optionModel.optionId}
            optionModel={optionModel}
            displayUsernameInput={showUsernameInput}
            resetUsername={resetUsername}
            optionButtonClickHandler={fetchData}
          />
      ))}
    </>
  )
}