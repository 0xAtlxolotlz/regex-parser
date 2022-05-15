package com.albertsawz.regex

/**
 * Class used to nest parser definitions
 */
class Parsers

/**
 * Check whether the input string has the given prefix
 */
fun Parsers.string(prefix: String): Parser<Unit> {
    return object: Parser<Unit> {
        override fun parse(string: String): ParserResult<Unit>? {
            return if (string.startsWith(prefix)) {
                ParserResult(Unit, string.drop(prefix.length))
            } else {
                null
            }
        }

    }
}

/**
 * Matches any single character
 */
val Parsers.char: Parser<Char>
    get() = object : Parser<Char> {
        override fun parse(string: String): ParserResult<Char>? {
            return if (string.isEmpty()) {
                null
            } else {
                ParserResult(string.first(), string.drop(1))
            }
        }
    }

/**
 *
 */
val Parsers.digit: Parser<Char>
    get() = char.filter()

fun Char.contains(): Boolean = isDigit()