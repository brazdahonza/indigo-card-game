package indigo

class Computer(deck: Deck, table: Table, game: Game) : Player(deck, table, game) {

    override fun play():Boolean {
        val cardPlayed= getCardToPlay()
        val chosenCardIndex: Int = hand.indexOf(cardPlayed)

        println("Computer plays $cardPlayed")
        println()
        if(table.tableList.isNotEmpty()) {
            if(checkCard(cardPlayed)) {
                val winString = "${this.javaClass.simpleName} wins cards"
                println(winString)
                game.printScore()
                removeCardFromHand(chosenCardIndex)
                println(table.toString())
                game.lastWon = "computer"
            } else {
                table.add(hand[chosenCardIndex])
                removeCardFromHand(chosenCardIndex)
                println(table.toString())
            }
        } else {
            table.add(hand[chosenCardIndex])
            removeCardFromHand(chosenCardIndex)
            println(table.toString())
        }
        return true
    }

    override fun getCardsInHand() {
        for (i in 0..hand.lastIndex) {
            print("${hand[i]} ")
        }
        println()
    }

    private fun getCardToPlay(): String {
        val candidateCards = table.getCandidateCards(hand)
        return if(hand.size == 1) {
            hand[0]
        } else if(candidateCards.size == 1) {
            candidateCards[0]
        } else if(table.tableList.isEmpty()){
            noCardsOnTable(hand)
        } else if(candidateCards.size == 0) {
            noCardsOnTable(hand)
        } else {
            candidateCards.shuffle()
            noCardsOnTable(candidateCards)
        }
    }

    private fun noCardsOnTable(list: MutableList<String>): String {
        val cardToReturn: String

        val heartCards = deckObject.getCardsBySuit(list, "♥")
        val diamondsCards = deckObject.getCardsBySuit(list, "♦")
        val clubsCards = deckObject.getCardsBySuit(list, "♣")
        val spadesCards = deckObject.getCardsBySuit(list, "♠")
        val cardsToChooseFrom = mutableListOf<String>()

        if(heartCards.size > 1) cardsToChooseFrom.addAll(heartCards)
        if(diamondsCards.size > 1) cardsToChooseFrom.addAll(diamondsCards)
        if(clubsCards.size > 1) cardsToChooseFrom.addAll(clubsCards)
        if(spadesCards.size > 1) cardsToChooseFrom.addAll(spadesCards)

        if(cardsToChooseFrom.size >= 2) {
            cardsToChooseFrom.shuffle()
            cardToReturn = cardsToChooseFrom[cardsToChooseFrom.lastIndex]
        } else if (list.size <= 4) {
            cardsToChooseFrom.clear()
            for (rank in deckObject.ranks) {
                val rankCards = deckObject.getCardsBySuit(list, rank)
                if (rankCards.size > 1) {
                    cardsToChooseFrom.addAll(rankCards)
                }
            }
            cardToReturn = if(cardsToChooseFrom.isNotEmpty()){
                cardsToChooseFrom[cardsToChooseFrom.lastIndex]
            } else {
                val cards = mutableListOf<String>()
                cards.addAll(list)
                cards.shuffle()
                cards[cards.lastIndex]
            }
        } else {
            val cards = mutableListOf<String>()
            cards.addAll(list)
            cards.shuffle()
            cardToReturn = cards[cards.lastIndex]
        }

        return cardToReturn
    }

}