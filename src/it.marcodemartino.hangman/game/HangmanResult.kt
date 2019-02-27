package it.marcodemartino.hangman.game

enum class HangmanResult {

    RIGHT_LETTER,
    WRONG_LETTER,
    SAID_LETTER,
    RIGHT_WORD,
    WRONG_WORD,
    WRONG_WORD_LENGTH,
    SAID_WORD;
}