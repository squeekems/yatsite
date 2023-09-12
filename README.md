# You Are in a Tavern Site

Try to follow gitflow. We know there is not much organization going on, but we can kind of see what features we are working on.

# Things That Need Doing
Todo:
- Update calls to hit exposed endpoint
  - Maybe make a dedicated spring project for API?
- create postman collection to his exposed endpoints
- Make unit tests?
- Create new CSV files based on DB
  - Create logic for loading new CSV files into the DB

# Jack's Current Focus
I am trying to focus on getting the tabletop game playable. Using the `Game` class under the `util/runtime` directory, I am creating logic to handle the start of the game, event cards, and turns at runtime. I am trying to keep logic in code, and content in the database. To that end, I am creating postman requests for one shots.