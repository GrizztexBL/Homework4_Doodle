Brandon Grizzle \
Matthew Morgan \
Homework 4 \
December 2, 2024
# Design Choices
We chose to use a Model-View-Controller design where the View class handles the scene creation and display. The Model class handles the location of the doodle as well as left and right movement. The Controller class updates the View based on the Model. In other words, when a change happens in the Model, the Controller will display these changes in the View. The Controller also handles the generation and removal of each platform. We also used an abstract Platform class that requires each platform subclass to implement a jump method that is called in the Controller when the doodle is supposed to jump on the platform.

# Known Bugs
The game runs as intended, there are no bugs that we know of.

# High Scores
Brandon: 66
Morgan: 79