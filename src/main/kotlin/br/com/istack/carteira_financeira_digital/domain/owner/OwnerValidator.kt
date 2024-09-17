package br.com.istack.carteira_financeira_digital.domain.owner

import br.com.istack.carteira_financeira_digital.domain.validation.Error
import br.com.istack.carteira_financeira_digital.domain.validation.Validator
import br.com.istack.carteira_financeira_digital.exception.DomainException

class OwnerValidator : Validator<Owner> {

    private val MAX_NAME_LENGTH = 65
    private val MIN_NAME_LENGTH = 3

    override fun validate(instance: Owner){
        checkName(instance.name)
    }

    private fun checkName(name: String){
        if(name.isBlank()){
            throw DomainException.with(Error("'name' is not valid"))
        }

        if (name.trim().length < MIN_NAME_LENGTH){
            throw DomainException.with(Error("'name' contains less than 3 characters"))
        }

        if (name.trim().length > MAX_NAME_LENGTH){
            throw DomainException.with(Error("'name' contains more than 65 characters"))
        }
    }
}