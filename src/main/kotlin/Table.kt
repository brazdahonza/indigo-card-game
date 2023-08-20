package indigo

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
    }

    fun add(card: String) {
        tableList.add(card)
    }

    override fun toString(): String {
        return if(tableList.isEmpty()) {
            "No cards on the table"
        } else {
            var cardsOnTableString = "cards on the table, and the top card is"
            val lastCard = tableList[tableList.lastIndex]
            val listSize = tableList.size

            cardsOnTableString = "$listSize $cardsOnTableString $lastCard"
            cardsOnTableString
        }
    }

    fun clearTable(): String {
        val tableListToString = tableList.joinToString(",")
        tableList.removeAll(tableList)
        return tableListToString
    }

    fun getCandidateCards(hand: MutableList<String>): MutableList<String> {
        val candidateCards: MutableList<String> = mutableListOf()
        val lastCard: CharArray
        try {
            lastCard = tableList[tableList.lastIndex].toCharArray()
        } catch(e: IndexOutOfBoundsException){
            return candidateCards
        }

        for(card in hand) {
            val cardArray = card.toCharArray()

            if (cardArray[cardArray.lastIndex] == lastCard[lastCard.lastIndex]){
                candidateCards.add(card)
            } else if (cardArray[0] == lastCard[0]) {
                candidateCards.add(card)
            }
        }

        return candidateCards
    }
}