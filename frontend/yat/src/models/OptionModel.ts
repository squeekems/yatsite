import EventModel from "./EventModel";

class OptionModel {
  optionId: number;
  label: string;
  result: EventModel;

  constructor(optionId: number, label: string, result: EventModel) {
    this.optionId = optionId;
    this.label = label;
    this.result = result;
  }
}
export default OptionModel;