package br.com.istack.carteira_financeira_digital.exception

open class NoStacktraceException : RuntimeException {
    constructor(message: String?) : super(message, null)

    constructor(message: String?, cause: Throwable?) : super(message, cause, true, false)
}