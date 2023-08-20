package indigo

class Deck {
    val ranks: List<String> = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val suits: List<String> = listOf("♦", "♥", "♠", "♣")
    val pointRanks: List<String> = listOf("A", "10", "J", "Q", "K")
    var deck: MutableList<String> = createDeck()
    fun get(numberOfCardsParameter: Int): String {
        val numberOfCards = numberOfCardsParameter
        var success = false
        var cardsToReturn = ""

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
        val cards = mutableListOf<String>()
        for (suit in suits) {
            for (rank in ranks) {
                cards.add(rank + suit)
            }
        }
        cards.shuffle()
        return cards
    }

    fun getCardsBySuit(hand: MutableList<String>, s: String): MutableList<String> {
        val returnCardList = mutableListOf<String>()

        for (card in hand) {
            if (card.contains(s)) {
                returnCardList.add(card)
            }
        }

        return returnCardList
    }

}