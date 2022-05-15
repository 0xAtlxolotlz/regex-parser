package com.albertsawz.regex

/**
 * @param result Matched element
 * @param string Remaining string
 */
data class ParserResult<A>(val result: A, val string: String)