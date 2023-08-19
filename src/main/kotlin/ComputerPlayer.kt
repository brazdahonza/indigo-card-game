package indigo

class ComputerPlayer(deck: Deck, table: Table) : Player(deck, table) {

    override fun play() {
        val chosenCardIndex: Int = hand.lastIndex
        val cardPlayed = hand[hand.lastIndex]
        table.add(hand[chosenCardIndex])

        println()
        removeCardFromHand(chosenCardIndex)
        println()
        println("Computer plays $cardPlayed")
    }
}