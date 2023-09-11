import OptionModel from "../../../models/OptionModel"
import { OptionButton } from "../OptionButton/OptionButton"
import "./EventCard.css";

export const EventCard = (props: {prompt: String, options: OptionModel[]}) => {
  const optionButtonClickHandler = (room: String) => {
    console.log(`${room} button was pressed!`);
    console.log(props);
  }

  return (
    <div className='main'>
      <div className='box-div'>
        <div className='event-card'>
          <div>
            <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
            <h5 className='card-text display-2 mb-5'>{props.prompt}</h5>
          </div>
          <div className="d-flex justify-content-around">
            <div className='button-container d-flex flex-column'>
              {props.options.map((optionModel: OptionModel) => (
                <OptionButton
                  key={optionModel.optionId}
                  optionModel={optionModel}
                  optionButtonClickHandler={optionButtonClickHandler}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}