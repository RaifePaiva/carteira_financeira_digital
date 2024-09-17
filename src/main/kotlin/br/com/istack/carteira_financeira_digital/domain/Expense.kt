package br.com.istack.carteira_financeira_digital.domain

data class Expense private constructor(
    val name: String,
    val description: String?,
    val value: Double,
    val expenseType: ExpenseType
){

    companion object {

        fun of(
            aName: String,
            aDescription: String?,
            aValue: Double,
            expenseType: ExpenseType
        ): Expense {
            return Expense(
                name = aName,
                description = aDescription,
                value = aValue,
                expenseType = expenseType
            )
        }

    }

}