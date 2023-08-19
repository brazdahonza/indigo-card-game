package indigo

open class Player(val deck: Deck, val table: Table) {
    val hand: MutableList<String> = mutableListOf()

    init {
        insertCardsToDeck()
    }

    fun insertCardsToDeck() {
        val handString = deck.get(6).split(",")
        for (card in handString) {
            if (card != "") {
                hand.add(card)
            }
        }
    }

    open fun play() {
        var chosenCorrectCard = false
        var chosenCardIndex: Int = 0

        while(!chosenCorrectCard) {
            println("Choose a card to play (1-${hand.size}):")
            chosenCardIndex = readln().toInt() - 1
            if(chosenCardIndex < 0 || chosenCardIndex > hand.size) {
                continue
            }
            table.add(hand[chosenCardIndex])
            removeCardFromHand(chosenCardIndex)
            chosenCorrectCard = true
        }
    }

    fun removeCardFromHand(cardIndex: Int) {
        hand.removeAt(cardIndex)

        if(hand.isEmpty()) {
            insertCardsToDeck()
        }
    }

    fun getCardsInHand() {
        print("Cards in hand: ")

        for (i in 0..hand.lastIndex) {
            print("${i + 1})${hand[i]} ")
        }
        println()
    }
}