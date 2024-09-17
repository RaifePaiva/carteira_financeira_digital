package br.com.istack.carteira_financeira_digital.domain.owner

import br.com.istack.carteira_financeira_digital.domain.Identifier
import java.util.*

class OwnerID private constructor(anId: String) : Identifier() {

    override val value: String

    init {
        Objects.requireNonNull(anId)
        this.value = anId
    }

    companion object {
        fun unique(): OwnerID {
            return OwnerID(UUID.randomUUID().toString())
        }

        fun from(anId: String): OwnerID {
            return OwnerID(anId)
        }
    }
}