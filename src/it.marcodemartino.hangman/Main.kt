package it.marcodemartino.hangman

import it.marcodemartino.hangman.game.Hangman
import it.marcodemartino.hangman.game.HangmanResult
import java.io.BufferedReader
import java.io.InputStreamReader

var wordList = mutableListOf<String>()

fun main() {
    val `in` = Hangman::class.java.getResourceAsStream("/words.txt")
    BufferedReader(InputStreamReader(`in`)).readLines().forEach{ s: String -> wordList.add(s) }

    var hangman = Hangman(getRandomWord())

    do {
        var userWillPlay = true

        while (!hangman.hasWin()) {
            print("The word to guess is: ${hangman.getActualWord()}, write a letter o a word: ")
            val input : String = readLine()!!
            println(" ")

            if (input.length == 1) {
                when (hangman.checkLetter(input[0])) {
                    HangmanResult.SAID_LETTER -> println("The letter you wrote was already said")
                    HangmanResult.WRONG_LETTER -> println("You wrote a wrong letter! Errors: ${hangman.getErrors()}")
                    HangmanResult.RIGHT_LETTER -> println("Congratulations, you guessed a letter!")
                }
                continue
            }

            when (hangman.checkWord(input)) {
                HangmanResult.WRONG_WORD_LENGTH -> println("The length of the word you wrote is not equals to the word to guess")
                HangmanResult.SAID_WORD -> println("The word you wrote was already said")
                HangmanResult.WRONG_WORD -> println("You wrote a wrong word! Errors: ${hangman.getErrors()}")
                HangmanResult.RIGHT_WORD -> println("Congratulations, you guessed the word!")
            }
        }

        println("You win!")

        var input = ""
        while (!input.equals("y", true) || !input.equals("n", true)) {
            print("Would you like to play another match? (y/n) ")
            input = readLine()!!

            if(input.equals("y", true)) {
                println("\nOkay, another match is starting")
                hangman = Hangman(getRandomWord())
                break
            } else if(input.equals("n", true)) {
                println("\nOkay, thank you for playing")
                userWillPlay = false
                break
            }
        }
    } while (userWillPlay)
}

fun getRandomWord() : String {
    return wordList[(0..wordList.size).random()]
}