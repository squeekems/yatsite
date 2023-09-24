class OptionModel {
  optionId: number;
  label: string;
  resultId: number;

  constructor(optionId: number, label: string, result: number) {
    this.optionId = optionId;
    this.label = label;
    this.resultId = result;
  }
}
export default OptionModel;