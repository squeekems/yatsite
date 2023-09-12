# You Are in a Tavern Site

Check out the [News](#news)

Try to follow gitflow. We know there is not much organization going on, but we can kind of see what features we are working on.

# Things That Need Doing
Todo:
- Update calls to hit exposed endpoint
  - Maybe make a dedicated spring project for API?
- create postman collection to his exposed endpoints
- Make unit tests?
- Create new CSV files based on DB
  - Create logic for loading new CSV files into the DB

# News
## News - 9/11/23
Jack is ignoring feature branching because he is an idiot and the project is still early in development. Please let him know if you have stuff you want to start working on before working on it. That way we avoid conflicts. :D

# Jack's Current Focus
I am trying to focus on getting the tabletop game playable. Using the `Game` class under the `util/runtime` directory, I am creating logic to handle the start of the game, event cards, and turns at runtime. I am trying to keep logic in code, and content in the database. To that end, I am creating postman requests for one shots.