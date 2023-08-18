package indigo
import java.lang.NumberFormatException

fun main() {
    startGame()
}

fun startGame() {
    var cards: MutableList<String> = createDeck()
    var gameRunning = true

    while(gameRunning) {
        println("Choose an action (reset, shuffle, get, exit):")
        val option = readln()
        when(option) {
            "reset" -> cards = resetDeck()
            "shuffle" -> shuffleDeck(cards)
            "get" -> get(cards)
            "exit" -> {
                gameRunning = false
                println("Bye")
            }
            else -> println("Wrong action.")
        }
    }
}

fun createDeck(): MutableList<String> {
    val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val suits = listOf("♦", "♥", "♠", "♣")
    val cards = mutableListOf<String>()

    for (suit in suits) {
        for (rank in ranks) {
            cards.add(rank + suit)
        }
    }
    cards.shuffle()
    return cards
}

fun get(cards: MutableList<String>) {
    var success = false

    while (!success){
        println("Number of cards:")
        val numberOfCards: Int

        try {
            numberOfCards = readln().toInt()
        } catch (e: NumberFormatException) {
            println("Invalid number of cards.")
            break
        }

        if (numberOfCards <= 0 || numberOfCards > 52) {
            println("Invalid number of cards.")
            break
        }

        if (cards.size < numberOfCards) {
            println("The remaining cards are insufficient to meet the request.")
            break
        }

        for (index in cards.lastIndex downTo cards.lastIndex - numberOfCards + 1) {
            print(cards[index] + " ")
            cards.removeAt(index)
        }

        println()
        success = true
    }

}

fun shuffleDeck(cards: MutableList<String>) {
    cards.shuffle()
    println("Card deck is shuffled.")
}

fun resetDeck(): MutableList<String> {
    println("Card deck is reset.")
    return createDeck()
}
