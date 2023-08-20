package indigo

open class Player(val deckObject: Deck, val table: Table, val game: Game) {
    val hand: MutableList<String> = mutableListOf()
    var cardCount = 0
    var scoreCount = 0
    init {
        insertCardsToDeck()
    }

    fun insertCardsToDeck() {
        val handString = deckObject.get(6).split(",")
        for (card in handString) {
            if (card != "") {
                hand.add(card)
            }
        }
    }
    fun increaseCardCount(numberOfCards: Int) {
        cardCount += numberOfCards
    }

    fun increaseScoreCount(scoreCountNumber: Int) {
        scoreCount += scoreCountNumber
    }
    open fun play(): Boolean {
        var chosenCorrectCard = false
        var chosenCardIndex: Int

        while(!chosenCorrectCard) {
            println("Choose a card to play (1-${hand.size}):")
            val answer = readln()
            var badInput = false
            if (answer == "exit") {
                return false
            }

            for (ch in answer) {
                if(ch.isLetter()) {
                    badInput = true
                }
            }
            if (badInput) continue

            chosenCardIndex = answer.toInt() - 1
            if(chosenCardIndex < 0 || chosenCardIndex >= hand.size) {
                continue
            }

            if(table.tableList.isNotEmpty()) {
                if (checkCard(hand[chosenCardIndex])) {
                    val winString = "${this.javaClass.simpleName} wins cards"
                    println(winString)
                    game.printScore()
                    removeCardFromHand(chosenCardIndex)
                    println(table.toString())
                    game.lastWon = "player"
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

            println()
            chosenCorrectCard = true
        }
        return true
    }


    fun checkCard(card: String): Boolean {
        val cardSymbolArray = card.toCharArray()
        val lastDeckCardArray = table.tableList[table.tableList.lastIndex].toCharArray()

        if(cardSymbolArray[cardSymbolArray.lastIndex] == lastDeckCardArray[lastDeckCardArray.lastIndex]) {
            val cardsFromTable = table.clearTable().split(",").toMutableList()
            cardsFromTable.add(card)
            for(cardtable in cardsFromTable) {
                if(cardtable.length == 3) {
                    increaseScoreCount(1)
                } else if(cardtable.toCharArray()[0].toString() in deckObject.pointRanks) {
                    increaseScoreCount(1)
                }
                increaseCardCount(1)
            }
            return true
        } else if(cardSymbolArray[0] == lastDeckCardArray[0]) {
            val cardsFromTable = table.clearTable().split(",").toMutableList()
            cardsFromTable.add(card)
            for(cardtable in cardsFromTable) {
                if(cardtable.length == 3) {
                    increaseScoreCount(1)
                } else if(cardtable.toCharArray()[0].toString() in deckObject.pointRanks) {
                    increaseScoreCount(1)
                }
                increaseCardCount(1)
            }
            return true
        } else {
            return false
        }
    }

    fun removeCardFromHand(cardIndex: Int) {
        hand.removeAt(cardIndex)

        if(hand.isEmpty()) {
            insertCardsToDeck()
        }
    }

    open fun getCardsInHand() {
        print("Cards in hand: ")

        for (i in 0..hand.lastIndex) {
            print("${i + 1})${hand[i]} ")
        }
        println()
    }

}