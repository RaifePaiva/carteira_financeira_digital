package br.com.istack.carteira_financeira_digital.domain.owner

import br.com.istack.carteira_financeira_digital.domain.wallet.WalletID
import java.time.LocalDateTime

class Owner private constructor(
    val id: OwnerID,
    val name: String,
    val active: Boolean,
    val wallets: MutableSet<WalletID>? = mutableSetOf(),
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val deletedAt: LocalDateTime?
) {

    init {
        OwnerValidator().validate(this)
    }

    companion object {
        fun of (
            name: String
        ): Owner{
            return Owner(
                id = OwnerID.unique(),
                name = name,
                active = true,
                createdAt = LocalDateTime.now(),
                updatedAt = null,
                deletedAt = null,
                wallets = mutableSetOf()
            )
        }
    }

    fun update(
        name: String
    ): Owner {
        return Owner(
            id = this.id,
            name = name,
            active = this.active,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = this.deletedAt,
            wallets = this.wallets
        )
    }

    fun deactivate(): Owner {
        return Owner(
            id = this.id,
            name = this.name,
            active = false,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = LocalDateTime.now(),
            wallets = this.wallets
        )
    }

    fun activate(): Owner {
        return Owner(
            id = this.id,
            name = this.name,
            active = true,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = this.deletedAt,
            wallets = this.wallets
        )
    }

    fun addWallet(wallet: WalletID): Owner {
        this.wallets?.add(wallet)
        return Owner(
            id = this.id,
            name = this.name,
            active = this.active,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now(),
            deletedAt = this.deletedAt,
            wallets = wallets
        )
    }

}