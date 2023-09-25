import { Dispatch, SetStateAction } from "react";
import EventModel from "../../models/EventModel";
import OptionModel from "../../models/OptionModel"
import "./OptionButton.css"

export const OptionButton = (props: {optionModel: OptionModel, resetUsername: (() => void), displayUsernameInput: ((show: boolean) => void), optionButtonClickHandler: (id: number) => Promise<void>}) => {
  const optionButtonClickHandler = () => {
    const showUsernameInput = promptUsername();
    props.displayUsernameInput(showUsernameInput);
    props.resetUsername();
    props.optionButtonClickHandler(props.optionModel.resultId);
  }

  const promptUsername = () => {
    const roomResultIds = [310, 311, 312, 313];
    return roomResultIds.some(id => id === props.optionModel.resultId);
  }

  return (
    <button className='btn btn-brown btn-lg m-1' onClick={optionButtonClickHandler}>
        {props.optionModel.label}
      </button>
  )
}