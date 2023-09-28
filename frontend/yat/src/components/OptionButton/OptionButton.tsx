import OptionModel from "../../models/OptionModel"
import "./OptionButton.css"

export const OptionButton = (
  {
    optionModel,
    optionButtonClickHandler,
  }: {
    optionModel: OptionModel,
    optionButtonClickHandler: (resultId: number) => void,
  }
) => {

  const clickHandler = () => {
    optionButtonClickHandler(optionModel.resultId);
  }

  return (
    <button className='btn btn-brown btn-lg m-1' onClick={clickHandler}>
      {optionModel.label}
    </button>
  )
}