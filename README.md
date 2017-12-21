# Project Title

CodingPuzzleBackend 

Terrorists want to explode bomb in a building, Main character or hero's responsibility is to stop them from doing this. 
Basic weapon to fight with enemies is Knife which is free. 
A player can earn money by killing enemies, and use that money to purchase more destructive weapons.
Character will fight with enemies in first 3 level of game at different locations. 
To attack enemy, character has to press K, if he/she presses any other key enemy will attack character.
In the last level of game character has to defuse bomb [within 10 seconds] using defusing kit.
If at any level Main character is killed he can purchase life to play again. 
I have not detected any money while purchasing a life so that user does not get out of balance :)

A player can quit at any level and resume again. But only last uncompleted game will be available for resuming.

### Prerequisites

Need to fill game data in following files according to formats defined in csv files.

1. enemies.csv [Contains information about enemies in a game]
2. game_levels.csv [Contains information about different games levels in a game]
3. locations.csv [Contains information about different locations where a character will explore]
4. weapons.csv [Contains information about different weapons used in game]

## Running the tests

mvn test 

## Create Jar File

mvn clean install 

## Running Jar File

java -jar target/codingPuzzleBackend-0.0.1-SNAPSHOT.jar



