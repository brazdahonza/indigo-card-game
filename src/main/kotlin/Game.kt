package indigo

class Game() {

    lateinit var deck: Deck
    lateinit var table: Table
    lateinit var player: Player
    lateinit var computer: Computer
    var lastWon = ""

    fun startGame() {
        printWelcome()
        val playerPlayingFirst: Boolean
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

        deck = Deck()
        table = Table(deck)
        player = Player(deck, table, this)
        computer = Computer(deck, table, this)

        if(gameRunning){
            var exitOnPurpose = false
            if (playerPlayingFirst) {
                player.getCardsInHand()
                if (!player.play()) {
                    gameRunning = false
                    println("Game Over")
                    exitOnPurpose = true
                }
            }

            while (gameRunning) {
                if (deck.deck.size == 0 && computer.hand.size == 0 && player.hand.size == 0) {
                    println("Game Over")
                    break
                }
                computer.getCardsInHand()
                computer.play()
                if (deck.deck.size == 0 && computer.hand.size == 0 && player.hand.size == 0) {
                    break
                }
                player.getCardsInHand()
                if (!player.play()) {
                    exitOnPurpose = true
                    break
                }
                if (deck.deck.size == 0 && computer.hand.size == 0 && player.hand.size == 0) {
                    break
                }
            }

            if(table.tableList.isNotEmpty() || exitOnPurpose) {
                if(lastWon == "computer") {
                    table.tableList.addAll(player.hand)
                    val lastDeckCard = table.tableList[table.tableList.lastIndex].toCharArray()
                    lastDeckCard[0] = '1'
                    val cardToSend = lastDeckCard.joinToString("")
                    computer.cardCount -= 1
                    computer.checkCard(cardToSend)
                } else {
                    table.tableList.addAll(computer.hand)
                    val lastDeckCard = table.tableList[table.tableList.lastIndex].toCharArray()
                    lastDeckCard[0] = '1'
                    val cardToSend = lastDeckCard.joinToString("")
                    player.cardCount -= 1
                    player.checkCard(cardToSend)
                }
            }

            if(computer.cardCount > player.cardCount) {
                computer.increaseScoreCount(3)
            } else if(computer.cardCount < player.cardCount) {
                player.increaseScoreCount(3)
            } else {
                if(playerPlayingFirst) {
                    player.increaseScoreCount(3)
                } else {
                    computer.increaseScoreCount(3)
                }
            }

            printScore()
            println("Game Over")
        }
    }

    fun printWelcome() {
        println("Indigo Card Game")
    }

    fun printScore() {
        val scoreString = "Score: Player ${player.scoreCount} - Computer ${computer.scoreCount}"
        val cardScoreString = "Cards: Player ${player.cardCount} - Computer ${computer.cardCount}"

        println(scoreString)
        println(cardScoreString)
    }


}
