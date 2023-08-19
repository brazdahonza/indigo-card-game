package indigo

fun main() {
    startGame()
}

fun startGame() {
    printWelcome()
    val playerPlayingFirst = playerFirst()

    val deck: Deck = Deck()
    val table: Table = Table(deck)
    val player: Player = Player(deck, table)
    val computer: ComputerPlayer = ComputerPlayer(deck, table)

    var gameRunning = true

    if(playerPlayingFirst) {
        player.getCardsInHand()
        player.play()
    }

    while(gameRunning) {
        computer.play()
        player.getCardsInHand()
        player.play()

        if (deck.deck.isEmpty()) {
            gameRunning = false
        }
    }
}

fun playerFirst(): Boolean {
    while(true) {
        println("Play first?")
        val input = readln()
        if(input == "yes") {
            return true
        } else if (input == "no") {
            return false
        } else {
            continue
        }
    }
}

fun printWelcome() {
    println("Indigo Card Game")
}

