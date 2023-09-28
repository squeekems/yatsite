import './ContinueButton.css';

export const ConinueButton = (
  {
    clickContinueHandler
  }: {
    clickContinueHandler: () => void
  }
) => {


  return (
    <button className='btn btn-brown btn-lg m-1' onClick={clickContinueHandler}>Continue</button>
  )
}