package indigo

import java.util.Stack

class Table(val deck: Deck) {
    val tableList: MutableList<String> = mutableListOf()

    init {
        initCardList()
    }

    fun initCardList() {
        val initCards = deck.get(4).split(",")
        var initialCardsString = "Initial cards on the table: "
        var cardsOnTableString = "cards on the table, and the top card is"

        for (card in initCards) {
            if (card != "") {
                tableList.add(card)
                initialCardsString += "$card "
            }
        }

        val lastCard = tableList[tableList.lastIndex]
        val listSize = tableList.size

        println(initialCardsString)
        println()
        cardsOnTableString = "$listSize $cardsOnTableString $lastCard"
        println(cardsOnTableString)
        println()
    }

    fun add(card: String) {
        var cardsOnTableString = "cards on the table, and the top card is"
        tableList.add(card)
        val lastCard = tableList[tableList.lastIndex]
        val listSize = tableList.size

        cardsOnTableString = "$listSize $cardsOnTableString $lastCard"
        print(cardsOnTableString)
        println()
    }
}