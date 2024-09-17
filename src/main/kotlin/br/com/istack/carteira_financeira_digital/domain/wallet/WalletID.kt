package br.com.istack.carteira_financeira_digital.domain.wallet

import br.com.istack.carteira_financeira_digital.domain.Identifier
import java.util.*

class WalletID private constructor(anId: String) : Identifier() {

    override val value: String

    init {
        Objects.requireNonNull(anId)
        this.value = anId
    }

    companion object {
        fun unique(): WalletID {
            return WalletID(UUID.randomUUID().toString())
        }

        fun from(anId: String): WalletID {
            return WalletID(anId)
        }
    }
}