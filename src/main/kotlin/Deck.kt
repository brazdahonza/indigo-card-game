package indigo

import java.lang.NumberFormatException

class Deck {
    var deck: MutableList<String> = createDeck()

    fun get(numberOfCardsParameter: Int): String {
        val numberOfCards = numberOfCardsParameter
        var success = false
        var cardsToReturn: String = ""

        while (!success){
            if (numberOfCards <= 0 || numberOfCards > 52) {
                println("Invalid number of cards.")
                break
            }

            if (deck.size < numberOfCards) {
                break
            }

            for (index in deck.lastIndex downTo deck.lastIndex - numberOfCards + 1) {
                cardsToReturn += deck[index] + ","
                deck.removeAt(index)
            }
            success = true
        }
        return cardsToReturn
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

    fun shuffleDeck() {
        deck.shuffle()
        println("Card deck is shuffled.")
    }

    fun resetDeck() {
        deck = createDeck()
        println("Card deck is reset.")
    }

}