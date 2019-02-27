package it.marcodemartino.hangman.game

class Hangman(var word : String) {

    private var lettersSaid = mutableListOf<Char>()
    private var wordsSaid = mutableListOf<String>()
    private var errors : Int = 0

    fun checkLetter(letter : Char) : HangmanResult {
        if(lettersSaid.contains(letter)) {
            return HangmanResult.SAID_LETTER
        }

        if(!word.contains(letter, true)) {
            errors++
            return HangmanResult.WRONG_LETTER
        }

        lettersSaid.add(letter)

        return HangmanResult.RIGHT_LETTER
    }

    fun checkWord(wordGuessed : String) : HangmanResult {
        if(wordGuessed.length != word.length) {
            return HangmanResult.WRONG_WORD_LENGTH
        }

        if(wordsSaid.contains(wordGuessed)) {
            return HangmanResult.SAID_WORD
        }

        wordsSaid.add(wordGuessed)

        if(!wordGuessed.equals(word, true)) {
            errors++
            return HangmanResult.WRONG_WORD
        }

        return HangmanResult.RIGHT_WORD
    }

    fun hasWin() : Boolean {
        if(wordsSaid.contains(word)) return true

        word.forEach { c -> if(!lettersSaid.contains(c)) return false }
        return true
    }

    fun getActualWord() : String {
        var actualWord = word
        actualWord.toCharArray().forEach { c: Char ->
            if (!lettersSaid.contains(c)) actualWord = actualWord.replace(c, '-')
        }
        return actualWord
    }

    fun getErrors() : Int {
        return errors
    }
}