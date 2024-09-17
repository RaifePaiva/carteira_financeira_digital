package br.com.istack.carteira_financeira_digital.exception

import br.com.istack.carteira_financeira_digital.domain.validation.Error

class DomainException private constructor(aMessage: String?, private val errors: List<Error> ) :
    NoStacktraceException(aMessage) {

        companion object {

            fun with(anErrors: Error): DomainException {
                return DomainException(anErrors.message, listOf(anErrors))
            }

            fun with(anErrors: List<Error>): DomainException {
                return DomainException("", anErrors)
            }

        }

    fun getErrors(): List<Error> {
        return this.errors
    }

}