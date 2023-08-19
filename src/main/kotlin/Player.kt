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

    open fun play(): Boolean {
        var chosenCorrectCard = false
        var chosenCardIndex: Int = 0

        while(!chosenCorrectCard) {
            println("Choose a card to play (1-${hand.size}):")
            val answer = readln()
            if (answer == "exit") {
                return false;
            }
            chosenCardIndex = readln().toInt() - 1
            if(chosenCardIndex < 0 || chosenCardIndex >= hand.size) {
                continue
            }
            println()
            table.add(hand[chosenCardIndex])
            removeCardFromHand(chosenCardIndex)
            chosenCorrectCard = true
        }
        return true;
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

    fun handEmpty(): Boolean {
        return hand.isEmpty()
    }
}