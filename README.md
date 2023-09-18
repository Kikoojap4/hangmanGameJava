# Hangman Game in Java

a simple Hangman game in java that works in java console, all with ascii art

## Introduction

This game was made for my [university](https://www.iut.u-bordeaux.fr/general/but-info/), the goal was to make a fun game with bug & error so the students fix it and play the game

## Getting Started

The game is in java, so you need to have java installed on your computer

The game is in french, but you can change the words in the file `src/mots.txt`, but all the ascii art are in french (you can change them too (they are hard coded))

All the game is made to be play in the console, so you can't play it in a GUI, the game menu is made with ascii art made by code, so you can simply change the display of the game with changing the code the variable `CHAR_AFFICHAGE_HORIZONTAL` and `CHAR_AFFICHAGE_VERTICAL` and at last `CHAR_LETTRE_CACHEE` (the last one is the letter that is hidden in the word)

## How to play

To navigate in the menu, you need to the number of the menu you want to go, and press enter

1. rules and how to play

2. play

3. exit

### 1. rules and how to play

This menu is made to explain the rules and how to play the game, because there is 2 game mode, <br>the first one is the normal mode, and the second one is the hardcore mode : (originally this was a bug, but we decided to keep it )<br><br> the hardcore mode is the same as the normal mode, but the letter that you didn't find are replaced by your last letter find, so you can't find the word if you don't find the first letter

### 2. play

This menu is made to play the game, you can choose the game mode with the number of the menu you want to go, and press enter