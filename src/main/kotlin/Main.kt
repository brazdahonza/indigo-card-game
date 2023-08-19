package indigo

fun main() {
    startGame()
}

fun startGame() {
    printWelcome()
    var playerPlayingFirst = true
    var gameRunning = true
    while(true) {
        println("Play first?")
        val input = readln()
        if(input == "yes") {
            playerPlayingFirst = true
            break
        } else if (input == "no") {
            playerPlayingFirst =  false
            break
        } else if (input == "exit"){
            gameRunning = false
            playerPlayingFirst = false
            println("Game Over")
            break
        } else {
            continue
        }
    }

    val deck: Deck = Deck()
    val table: Table = Table(deck)
    val player: Player = Player(deck, table)
    val computer: ComputerPlayer = ComputerPlayer(deck, table)

    if(playerPlayingFirst) {
        player.getCardsInHand()
        if(!player.play()) {
            gameRunning = false
            println("Game Over")
        }
    }

    while(gameRunning) {
        if (deck.deck.isEmpty() && computer.handEmpty() && player.handEmpty()) {
            println("Game Over")
            break
        }
        computer.play()

        if (deck.deck.isEmpty() && computer.handEmpty() && player.handEmpty()) {
            println("Game Over")
            break
        }
        player.getCardsInHand()
        if(!player.play()) {
            gameRunning = false
            println("Game Over")
        }
    }
}

fun printWelcome() {
    println("Indigo Card Game")
}

