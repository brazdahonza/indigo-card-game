package indigo

class Computer(deck: Deck, table: Table, game: Game) : Player(deck, table, game) {

    override fun play():Boolean {
        val chosenCardIndex: Int = hand.lastIndex
        if (hand.isEmpty()) return false
        val cardPlayed = hand[hand.lastIndex]
        println("Computer plays $cardPlayed")
        if(table.tableList.isNotEmpty()) {
            if(checkCard(cardPlayed)) {
                var winString = "${this.javaClass.simpleName} wins cards"
                println(winString)
                game.printScore()
                println(table.toString())
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
}