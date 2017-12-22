# Project Title

CodingPuzzleBackend 

Terrorists want to explode bomb in a building, Main character or hero's responsibility is to stop them from doing this. 
Game Rules:
1. To Play game user need to select weapon.
2. Basic weapon to fight with enemies is Knife which is free. 
3. To purchase other weapons user need to kill enemies and get kill reward, and use that money to purchase more destructive weapons.
4. To attack enemy, user has to press K, if user presses any other key enemy will attack.
5. To Defuse bomb user need to pree D [within 10 seconds] using defusing kit
6. If user is killed at any level he can purchase life to play again. I have not detected any money while purchasing a life so that user does not get out of balance :)

7. A player can quit at any level and resume again. But only last uncompleted game will be available for resuming.

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



