import OptionModel from "../../../models/OptionModel"
import "./OptionButton.css"

export const OptionButton = (props: {optionModel: OptionModel, optionButtonClickHandler: Function}) => {

  const optionButtonClickHandler = () => {
    props.optionButtonClickHandler(props.optionModel);
  }

  return (
    <button className='btn btn-brown btn-lg m-1' onClick={optionButtonClickHandler}>
        {props.optionModel.label}
      </button>
  )
}