package tictactoe

class TickTackToe {
    private val turn = mutableListOf("O", "X")
    private var numOfTurn = 1
    private var gameGrid = mutableListOf(mutableListOf("_", "_", "_"),
                                         mutableListOf("_", "_", "_"),
                                         mutableListOf("_", "_", "_"))
    private var xCount = 0
    private var oCount = 0
    private var blankCount = 0
    private var oWins = false
    private var xWins = false
    private var isDraw = false
    private var userInputCoordinates: String = ""
    private var userCoordinates = mutableListOf<String>()
    private var inputError = false

    init {
        this.theGame()
    }

    private fun theGame() {
        while (true) {
            if (!inputError) this.printGameGrid()
            this.readUserInput()
            this.analyzeUserInput()
            this.analyze()
            if (this.xWins || this.oWins || this.isDraw) break
        }
        this.numOfTurn = 1
    }

    private fun readUserInput() {
        this.userInputCoordinates = readln()
        this.userCoordinates = this.userInputCoordinates.split(" ").toMutableList()
    }

    private fun placeSymbol() {
        this.gameGrid[this.userCoordinates[0].toInt() - 1][this.userCoordinates[1].toInt() - 1] = this.turn[this.numOfTurn % 2]
        this.numOfTurn++
    }

    private fun printGameGrid() {
        println("---------")
        println("| ${this.gameGrid[0][0]} ${this.gameGrid[0][1]} ${this.gameGrid[0][2]} |")
        println("| ${this.gameGrid[1][0]} ${this.gameGrid[1][1]} ${this.gameGrid[1][2]} |")
        println("| ${this.gameGrid[2][0]} ${this.gameGrid[2][1]} ${this.gameGrid[2][2]} |")
        println("---------")
    }

    private fun analyzeUserInput() {
        if (this.userCoordinates[0].toIntOrNull() == null || this.userCoordinates[1].toIntOrNull() == null) {
            println("You should enter numbers!")
            this.inputError = true
            return
        }
        else if (this.userCoordinates[0].toInt() !in (1..3) || this.userCoordinates[1].toInt() !in (1..3)) {
            println("Coordinates should be from 1 to 3!")
            this.inputError = true
            return
        }
        else if (this.gameGrid[this.userCoordinates[0].toInt() - 1][this.userCoordinates[1].toInt() - 1] == "X" ||
            this.gameGrid[this.userCoordinates[0].toInt() - 1][this.userCoordinates[1].toInt() - 1] == "O") {
            println("This cell is occupied! Choose another one!")
            this.inputError = true
            return
        }
        this.inputError = false
        this.placeSymbol()
    }

    private fun analyze() {
        this.count()

        if (this.gameGrid[0][0] == "X" && this.gameGrid[0][1] == "X" && this.gameGrid[0][2] == "X" ||
            this.gameGrid[1][0] == "X" && this.gameGrid[1][1] == "X" && this.gameGrid[1][2] == "X" ||
            this.gameGrid[2][0] == "X" && this.gameGrid[2][1] == "X" && this.gameGrid[2][2] == "X" ||

            this.gameGrid[0][0] == "X" && this.gameGrid[1][0] == "X" && this.gameGrid[2][0] == "X" ||
            this.gameGrid[0][1] == "X" && this.gameGrid[1][1] == "X" && this.gameGrid[2][1] == "X" ||
            this.gameGrid[0][2] == "X" && this.gameGrid[1][2] == "X" && this.gameGrid[2][2] == "X" ||

            this.gameGrid[0][0] == "X" && this.gameGrid[1][1] == "X" && this.gameGrid[2][2] == "X" ||
            this.gameGrid[2][0] == "X" && this.gameGrid[1][1] == "X" && this.gameGrid[0][2] == "X") {
            this.xWins = true
        }

        if (this.gameGrid[0][0] == "O" && this.gameGrid[0][1] == "O" && this.gameGrid[0][2] == "O" ||
            this.gameGrid[1][0] == "O" && this.gameGrid[1][1] == "O" && this.gameGrid[1][2] == "O" ||
            this.gameGrid[2][0] == "O" && this.gameGrid[2][1] == "O" && this.gameGrid[2][2] == "O" ||

            this.gameGrid[0][0] == "O" && this.gameGrid[1][0] == "O" && this.gameGrid[2][0] == "O" ||
            this.gameGrid[0][1] == "O" && this.gameGrid[1][1] == "O" && this.gameGrid[2][1] == "O" ||
            this.gameGrid[0][2] == "O" && this.gameGrid[1][2] == "O" && this.gameGrid[2][2] == "O" ||

            this.gameGrid[0][0] == "O" && this.gameGrid[1][1] == "O" && this.gameGrid[2][2] == "O" ||
            this.gameGrid[2][0] == "O" && this.gameGrid[1][1] == "O" && this.gameGrid[0][2] == "O") {
            this.oWins = true
        }

        if (this.xWins) {
            this.printGameGrid()
            println("X wins")
            return
        } else if (this.oWins) {
            this.printGameGrid()
            println("O wins")
            return
        } else if (this.blankCount == 0 || numOfTurn > 9) {
            this.printGameGrid()
            this.isDraw = true
            println("Draw")
            return
        }
    }

    private fun count() {
        for (sublist in this.gameGrid) {
            for (element in sublist) {
                when (element) {
                    "X" -> this.xCount++
                    "O" -> this.oCount++
                    "_" -> this.blankCount++
                }
            }
        }
    }
}

fun main() {
    val tickTackToe = TickTackToe()
}