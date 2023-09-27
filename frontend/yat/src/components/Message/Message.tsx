export const Message = (
  { displayUsernameInput, showGreetTraveler, username, prompt }:
    {
      displayUsernameInput: boolean,
      showGreetTraveler: boolean,
      username: string,
      prompt: string
    }
) => {
  return (
    <div>
      <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
      <h2 className='card-text display-2 mb-5'>
        {displayUsernameInput && !showGreetTraveler && ('What is your name, Traveler?')}
        {!displayUsernameInput && showGreetTraveler && (`Welcome to the tavern, ${username}.`)}
        {!displayUsernameInput && !showGreetTraveler && prompt}
      </h2>
    </div>
  )
}