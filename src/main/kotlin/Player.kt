package indigo

class Player(val deck: Deck) {
    val hand: MutableList<String> = mutableListOf()

    init {
        val handString = deck.get(6).split(",")
        for (card in handString) {
            hand.add(card)
        }
    }

    fun play() {
        var chosenCorrectCard = false
        while(!chosenCorrectCard) {
            println("Choose a card to play (1-${hand.size}):")
            val chosenCardIndex = readln().toInt()
            if(chosenCardIndex < 0 || chosenCardIndex > hand.lastIndex) {
                continue
            }
            chosenCorrectCard = true
        }
    }

    fun getCardsInHand() {
        print("Cards in hand: ")

        for (i in 0..hand.lastIndex) {
            println("$i)${hand[i]}")
        }
    }
}