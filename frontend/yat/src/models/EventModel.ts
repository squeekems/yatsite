import OptionModel from "./OptionModel";

class EventModel {
  eventId: number;
  prompt: string;
  isCard: boolean;
  options: OptionModel[];

  constructor(eventId: number, prompt: string, isCard: boolean, options: OptionModel[]) {
    this.eventId = eventId;
    this.prompt = prompt;
    this.isCard = isCard;
    this.options = options;
  }
}
export default EventModel;