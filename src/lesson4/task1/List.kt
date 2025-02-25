@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.io.File.separator
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun squares1(list: List<Double>) = list.map { it * it }

fun abs(v: List<Double>): Double {
    val squareElement = squares1(v)
    return sqrt(squareElement.sum())
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else (list.sum() / list.size)
/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) list[i] = list[i] - mean
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var sum = 0
    if (a.isEmpty() && b.isEmpty()) return 0
    for (i in 0 until a.size) {
        sum += a[i] * b[i]
    }
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun pow(x: Int, i: Int): Int {
    var r = 1
    for (j in 0 until i) r *= x
    return r
}

fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for (i in 0 until p.size) {
        px = px + p[i] * pow(x, i)
    }
    return px

}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i - 1] + list[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    val n1 = n / 2
    var n2 = n
    var i = 2
    while (i <= n1) {
        while (n2 % i == 0) {
            list.add(i)
            n2 /= i
        }
        i++
    }
    if (n2 > 1) {
        list.add(n)
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
//не работает
fun numberDegree(n: Int, base: Int): Int {
    var k = 1
    while (n >= k) {
        k = k * base
    }
    k /= base
    return k
}

fun countDegree(n: Int, base: Int): Int {
    var k = 1
    var count = 0
    while (n >= k) {
        k = k * base
        count++
    }
    count--
    return count
}

fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var count = 0
    var b = countDegree(n, base)
    while (n > 0) {
        val k = numberDegree(n, base)
        count = (n - k) / k
        list.add(count)
        count = 0
        b--
    }
    while (b != 0) {
        list.add(0)
        b--
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var quantity = digits.size - 1
    var result = 0
    var base1 = 1
    while (quantity != -1) {
        result += digits[quantity] * base1
        base1 *= base
        quantity--
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun forSixAndThree(rewers: Int, count: Int, rus: String, count2: Int): String {
    val k = rewers % 10
    if (k == 0) return rus
    var rus1 = rus
    when (k) {
        9 -> rus1 += "девятьсот "
        8 -> rus1 += "восемьсот "
        7 -> rus1 += "семьсот "
        6 -> rus1 += "шестьсот "
        5 -> rus1 += "пятьсот "
        4 -> rus1 += "четыреста "
        3 -> rus1 += "триста "
        2 -> rus1 += "двести "
        0 -> rus1
        else -> rus1 += "сто "
    }
    return rus1
}

fun forFiveAndTwo(rewers: Int, count: Int, rus: String, count2: Int): String {
    val k = rewers % 10
    var rewers1 = rewers
    var rus1 = rus
    var count1 = count
    if (k == 0) return rus1
    when (k) {
        9 -> rus1 += "девяносто "
        8 -> rus1 += "восемьдесят "
        7 -> rus1 += "семьдесят "
        6 -> rus1 += "шестьдесят "
        5 -> rus1 += "пятьдесят "
        4 -> rus1 += "сорок "
        3 -> rus1 += "тридцать "
        2 -> rus1 += "двадцать "
        0 -> rus1
        else -> {
            rewers1 /= 10
            val a = rewers1 % 10
            when (a) {
                9 -> rus1 += "девятнадцать "
                8 -> rus1 += "восемнадцать "
                7 -> rus1 += "семнадцать "
                6 -> rus1 += "шестнадцать "
                5 -> rus1 += "пятнадцать "
                4 -> rus1 += "четырнадцать "
                3 -> rus1 += "тринадцать "
                2 -> rus1 += "двенадцать "
                1 -> rus1 += "одиннадцать "
                0 -> rus1 += "десять "
            }
            count1--
            if (count1 == 4) rus1 += "тысяч "
            return rus1
        }

    }
    return rus1
}

fun forFourAndOne(rewers: Int, count: Int, rus: String, count2: Int): String {
    val k = rewers % 10
    if (k == 0 && count == 4) return rus + "тысяч "
    if (k == 0 && count == 1) return rus
    var rus1 = rus
    rus1 += when (k) {
        9 -> if (count == 4) "девять тысяч " else "девять"
        8 -> if (count == 4) "восемь тысяч " else "восемь"
        7 -> if (count == 4) "семь тысяч " else "семь"
        6 -> if (count == 4) "шесть тысяч " else "шесть"
        5 -> if (count == 4) "пять тысяч " else "пять"
        4 -> if (count == 4) "четыре тысячи " else "четыре"
        3 -> if (count == 4) "три тысячи " else "три"
        2 -> if (count == 4) "две тысячи " else "два"
        else -> if (count == 4) "одна тысяча " else "один"
    }
    return rus1
}

fun russian(n: Int): String {
    var rus = ""
    var rewers = 0
    var count = 0
    var n1 = n
    while (n1 != 0) {
        val a = n1 % 10
        rewers = rewers * 10 + a
        n1 /= 10
        count++
    }
    val count2 = count
    while (count != 0) {
        when {
            count == 6 || count == 3 -> {
                rus = forSixAndThree(rewers, count, rus, count2)
                count--
                rewers /= 10
            }
            count == 5 || count == 2 -> {
                rus = forFiveAndTwo(rewers, count, rus, count2)
                if (rewers % 10 == 1) {
                    count -= 2
                    rewers /= 100
                } else {
                    count--
                    rewers /= 10
                }
            }
            count == 4 || count == 1 -> {
                rus = forFourAndOne(rewers, count, rus, count2)
                count--
                rewers /= 10
            }
        }
    }
    return rus.trim()
}