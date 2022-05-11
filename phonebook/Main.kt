package phonebook

fun main() {
    PhoneBook.fillPhoneBook()
    //PhoneBook.phoneBookList.forEach { println("${it.number} ${it.name}") }
    println("Start searching (linear search)...")
    var searchTime = PhoneBook.linearSearch()
    println(String.format("Found 500 / 500 entries. Time taken: %1\$tM min. %1\$tS sec. %1\$tL ms.\n",
        searchTime))

    println("Start searching (bubble sort + jump search)...")
    var sortTime = PhoneBook.bubbleSort(searchTime)
    searchTime = PhoneBook.jumpSearch()
    println(String.format("Found 500 / 500 entries. Time taken: %1\$tM min. %1\$tS sec. %1\$tL ms.\n",
        sortTime + searchTime) +
            String.format("Sorting time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", sortTime) +
            String.format("Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", searchTime))
    //PhoneBook.phoneBookList.forEach { println("${it.number} ${it.name}") }

    PhoneBook.fillPhoneBook()

    println("Start searching (quick sort + binary search)...")
    sortTime = PhoneBook.quickSort()
    searchTime = PhoneBook.binarySearch()
    println(String.format("Found 500 / 500 entries. Time taken: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", sortTime + searchTime) +
            String.format("Sorting time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", sortTime) +
            String.format("Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", searchTime))

    println("Start searching (hash table)...")
    sortTime = PhoneBook.createHashMap()
    searchTime = PhoneBook.findByHashMap()
    println(String.format("Found 500 / 500 entries. Time taken: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", sortTime + searchTime) +
            String.format("Creating time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", sortTime) +
            String.format("Searching time: %1\$tM min. %1\$tS sec. %1\$tL ms.\n", searchTime))
}
