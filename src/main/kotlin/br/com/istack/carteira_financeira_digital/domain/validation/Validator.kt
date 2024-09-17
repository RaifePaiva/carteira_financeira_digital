package br.com.istack.carteira_financeira_digital.domain.validation

interface Validator<T> {

    fun validate(instance: T)

}