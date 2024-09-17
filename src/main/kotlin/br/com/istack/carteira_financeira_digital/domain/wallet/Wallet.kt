package br.com.istack.carteira_financeira_digital.domain.wallet

import br.com.istack.carteira_financeira_digital.domain.Expense
import br.com.istack.carteira_financeira_digital.domain.Income

class Wallet private constructor(
    val id: WalletID,
    val ownerId: String,
    var name: String,
    var description: String?
) {

    var expenses: Array<Expense> = arrayOf()
        private set

    var incomes: Array<Income> = arrayOf()
        private set

    companion object {
        fun of(
            aOwnerID: String,
            aName: String,
            aDescription: String?
        ): Wallet {
            return Wallet(
                WalletID.unique(),
                aOwnerID,
                aName,
                aDescription
            )
        }
    }


    fun addExpense(expenses: Array<Expense>) {
        this.expenses = expenses
    }

    fun addIncome(incomes: Array<Income>) {
        this.incomes = incomes
    }

    fun removeExpense(expense: Expense) {
        if(!this.expenses.contains(expense)){
            throw IllegalArgumentException("Expense not found")
        }

        this.expenses = this.expenses.filterNot { it == expense }.toTypedArray()
    }

    fun removeIncome(income: Income) {
        if(!this.incomes.contains(income)){
            throw IllegalArgumentException("Income not found")
        }

        this.incomes = this.incomes.filterNot { it == income }.toTypedArray()
    }

    fun totalBalance(): Double {
        return incomes.sumOf { it.value } - expenses.sumOf { it.value }
    }

    fun update(
        name: String,
        description: String?
    ): Wallet{
        return Wallet(
            this.id,
            this.ownerId,
            name,
            description
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wallet

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}