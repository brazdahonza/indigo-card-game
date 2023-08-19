package indigo

class ComputerPlayer(deck: Deck, table: Table) : Player(deck, table) {

    override fun play():Boolean {
        val chosenCardIndex: Int = hand.lastIndex
        val cardPlayed = hand[hand.lastIndex]

        removeCardFromHand(chosenCardIndex)
        println("Computer plays $cardPlayed")
        println()
        table.add(cardPlayed)
        return true
    }
}