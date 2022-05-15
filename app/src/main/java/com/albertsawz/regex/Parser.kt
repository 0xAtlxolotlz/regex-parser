package com.albertsawz.regex

/**
 * Abstraction for parsers
 */
interface Parser<A> {

    /**
     * Parses [string].
     *
     * @return matched element [A] and the remaining substring if the match is
     * successful. Returns null otherwise.
     */
    fun parse(string: String): ParserResult<A>?

    /**
     * Takes the current parser and the predicate as an input and produces a new parser as an
     * output.
     * Takes the value [A] from the given parser and if the value passes the [predicate], returns
     * this value from the parser which it returned as an output. If the value doesn't pass the
     * [predicate], it returns null indicating failure.
     */
    fun filter(predicate: (A) -> Boolean): Parser<A> {
        return map {
            return if (predicate(this))
        }
    }

    /**
     * Takes a value produced by the given parser and returns a new parser which produces a
     * transformed value.
     */
    fun <B> map(transform: (A) -> B?): Parser<B> {
        return flatMap { match ->
            return@flatMap object: Parser<B> {
                override fun parse(string: String): ParserResult<B>? {
                    return transform(match)
                }
            }
        }
    }

    /**
     * [A] is the data type of the parser that calls [flatMap].
     * @return a parser which produces the result of the parser returned by the [transform]
     * closure. The [transform] closure is called when the current parser matches a value.
     */
    fun <B> flatMap(transform: (A) -> Parser<B>): Parser<B> {
        return object: Parser<B> {
            override fun parse(string: String): ParserResult<B>? {
                // A class is supposed to implement this interface (Parser). Therefore, it will
                // have an implementation of parse() method, which is being called here.
                val parserResult = this@Parser.parse(string)
                return if (parserResult != null) {
                    transform(parserResult.result).parse(string)
                } else {
                    null
                }
            }
        }
    }
}