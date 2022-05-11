package phonebook

import java.io.File
import kotlin.math.*

const val DIRECTORY_PATH = "/home/fox/IdeaProjects/Phone Book/Phone Book/task/src/phonebook/directory.txt"
const val FIND_PATH = "/home/fox/IdeaProjects/Phone Book/Phone Book/task/src/phonebook/find.txt"

object PhoneBook {
    var phoneBookList: MutableList<PhoneNumber> = mutableListOf()
    var hashMapBookList: HashMap<String, Int> = hashMapOf()

    fun fillPhoneBook() {
        val file = File(DIRECTORY_PATH)
        file.forEachLine {
            val strings = it.split(" ")
            phoneBookList.add(PhoneNumber(strings[0].toInt(), strings.drop(1).joinToString(" ")))
        }
    }

    fun getPhoneNumberByIndex(index: Int): PhoneNumber {
        return phoneBookList[index]
    }


    fun bubbleSort(linearSearchTime: Long = 0): Long {
        val timerStart = System.currentTimeMillis()
        while (!phoneBookIsSorted()) {
            for (i in 0 until phoneBookList.count() - 1){
                if (phoneBookList[i].name > phoneBookList[i + 1].name) {
                    val temp = phoneBookList[i]
                    phoneBookList[i] = phoneBookList[i + 1]
                    phoneBookList[i + 1] = temp
                }
            }
            val curTime = System.currentTimeMillis()
            if (curTime - timerStart > linearSearchTime * 10 && linearSearchTime != 0L) {
                return curTime - timerStart + quickSort()
                }
        }
        val finishTime = System.currentTimeMillis()
        return finishTime - timerStart
    }

    fun jumpSearch(): Long {
        val startTime = System.currentTimeMillis()
        val file = File(FIND_PATH)
        file.forEachLine {
            jumpSearchElement(it)
        }
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }

    private fun jumpSearchElement(value: String): Int {
        val step = floor(sqrt(phoneBookList.count().toDouble())).toInt()

        var curr = 0
        while (curr < phoneBookList.count()) {
            if (phoneBookList[curr].name == value) {
                return curr
            }
            if (phoneBookList[curr].name > value) {
                var ind = curr - 1
                while (ind > curr - step && ind >= 0) {
                    if (phoneBookList[ind].name == value) {
                        return ind
                    }
                    ind -= 1
                }
                return -1
            }
            curr += step
        }

        var ind = phoneBookList.count() - 1

        while (ind > curr - step) {
            if (phoneBookList[ind].name == value) {
                return ind
            }
            ind -= 1
        }
        return -1
    }

    fun binarySearch(): Long {
        val startTime = System.currentTimeMillis()
        val file = File(FIND_PATH)
        file.forEachLine {
            binarySearchElement(it)
        }
        return System.currentTimeMillis() - startTime
    }

    private fun binarySearchElement(value: String): Int {
        var left = 0
        var right = phoneBookList.count() - 1
        while (left <= right) {
            var middle = (left + right) / 2
            if (phoneBookList[middle].name == value) {
                return middle
            }
            else if (phoneBookList[middle].name > value) {
                right = middle - 1
            }
            else {
                left = middle + 1
            }
        }
        return -1
    }

    fun quickSort(): Long {
        val startTime = System.currentTimeMillis()
        phoneBookList.sortBy { it.name }
        return System.currentTimeMillis() - startTime
    }

    private fun phoneBookIsSorted(): Boolean {
        for (i in 0 until phoneBookList.count() - 1)
            if (phoneBookList[i].name > phoneBookList[i + 1].name)
                return false
        return true
    }

    fun linearSearch(): Long {
        val timerStart = System.currentTimeMillis()
        val file = File(FIND_PATH)
        file.forEachLine {
            for (phoneNumber in phoneBookList) {
                if (phoneNumber.name == it)
                    break
            }
        }
        val timerEnd = System.currentTimeMillis()
        return timerEnd - timerStart
    }

    fun createHashMap(): Long {
        val startTime = System.currentTimeMillis()
        phoneBookList.forEach {
            hashMapBookList[it.name] = it.number
        }
        return System.currentTimeMillis() - startTime
    }

    fun findByHashMap(): Long {
        val startTime = System.currentTimeMillis()
        val file = File(FIND_PATH)
        file.forEachLine {
            hashMapBookList[it]
        }
        return System.currentTimeMillis() - startTime
    }
}