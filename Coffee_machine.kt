package machine

fun main() {
    val machine = CoffeeMachine(400, 540, 120, 9, 550)
    Loop@ while (true) {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        when (readln()) {
            "fill" -> machine.fill()
            "take" -> machine.take()
            "buy" -> machine.buy()
            "remaining" -> machine.info()
            "exit" -> break@Loop
        }
    }
}


class CoffeeMachine(water: Int, milk: Int, beans: Int, cups: Int, money: Int) {
    private var milk: Int = 0
    private var water: Int = 0
    private var beans: Int = 0
    private var cups: Int = 0
    private var money: Int = 0

    init {
        this.milk = milk
        this.water = water
        this.beans = beans
        this.cups = cups
        this.money = money
    }

    fun info() {
        println("\nThe coffee machine has:\n" +
                "$water ml of water\n" +
                "$milk ml of milk\n" +
                "$beans g of coffee beans\n" +
                "$cups disposable cups\n" +
                "\$$money of money")
    }

    fun buy() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        var waterToDoCoffee: Int = 0
        var milkToDoCoffee: Int = 0
        var beansToDoCoffee: Int = 0
        var price = 0
        when (readln()) {
            "1" -> {
                waterToDoCoffee = 250
                beansToDoCoffee = 16
                price = 4
            }
            "2" -> {
                waterToDoCoffee = 350
                milkToDoCoffee = 75
                beansToDoCoffee = 20
                price = 7
            }
            "3" -> {
                waterToDoCoffee = 200
                milkToDoCoffee = 100
                beansToDoCoffee = 12
                price = 6
            }
            "back" -> return
        }
        if (waterToDoCoffee > water) println("Sorry, not enough water!")
        else if (milkToDoCoffee > milk) println("Sorry, not enough milk!")
        else if (beansToDoCoffee > beans) println("Sorry, not enough coffee beans!")
        else if (cups < 1) println("Sorry, not enough cups!")
        else {
            water -= waterToDoCoffee; milk -= milkToDoCoffee; beans -= beansToDoCoffee; money += price; cups--
            println("I have enough resources, making you a coffee!")
        }
    }

    fun take() {
        println("\nI gave you \$$money")
        money = 0
    }

    fun fill() {
        print("Write how many ml of water do you want to add: "); water += readln().toInt()
        print("Write how many ml of milk do you want to add: "); milk += readln().toInt()
        print("Write how many grams of coffee beans do you want to add: "); beans += readln().toInt()
        print("Write how many disposable cups of coffee do you want to add:"); cups += readln().toInt()
    }
}
