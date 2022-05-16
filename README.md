# Kalah Game

An application which enables two player to play Kalah game

# Game Rules

Each of the two players has his six pits in front of him. To the right of the six pits,
each player has a house pit. At the start of the game, there are six stones in each
of the six round pits.

The player who begins with the first move picks up all the stones in any of his
own six pits, and sows the stones on to the right, one in each of the following
pits, including his own house pit. No stones are put in the opponents' house pit. If the
player's last stone lands in his own house pit, he gets another turn. This can be
repeated several times before it's the other player's turn.

During the game the pits are emptied on both sides. Always when the last stone
lands in an own empty pit, the player captures his own stone and all stones in the
opposite pit (the other player’s pit) and puts them in his own house pit.

The game is over as soon as one of the sides runs out of stones. The player who
still has stones in his pits keeps them and puts them in his house pit. The winner of
the game is the player who has the most stones in his house pit.

## Requirements

1. Java - 1.11.x

2. Maven - 3.x.x


## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/mohammadalibahramian/kalah-game
```

**2. Build and run the app using maven**

you can run the app using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:7575>.

## Rest API Documentation

You can find the APIs documentation for this application on

<http://localhost:7575/swagger-ui.html/>

## How to play

You can find a simple visualization of game by accessing the following link

<http://localhost:7575/>
