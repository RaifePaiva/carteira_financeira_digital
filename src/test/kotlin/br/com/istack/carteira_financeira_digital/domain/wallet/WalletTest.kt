package br.com.istack.carteira_financeira_digital.domain.wallet

import br.com.istack.carteira_financeira_digital.domain.Expense
import br.com.istack.carteira_financeira_digital.domain.ExpenseType
import br.com.istack.carteira_financeira_digital.domain.Income
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WalletTest {

    @Test
    fun `given valid params, when call create wallet, should instantiate a wallet`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)

        assertEquals(expectedOwnerID, wallet.ownerId)
        assertEquals(expectedName, wallet.name)
        assertEquals(expectedDescription, wallet.description)
        assertEquals(0.0, wallet.totalBalance())
        assertNotNull(wallet.id)
        assertTrue(wallet.expenses.isEmpty())
        assertTrue(wallet.incomes.isEmpty())
    }

    @Test
    fun `given a valid wallet, when call add income, should return balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedIncome = Income.of("Salário", "Meu salário", 1000.0)

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addIncome(arrayOf(expectedIncome))

        assertEquals(expectedIncome.value, wallet.totalBalance())
    }

    @Test
    fun `given a valid wallet with incomes, when call add expense, should return balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedIncome = Income.of("Salário", "Meu salário", 1000.0)
        val expectedExpense = Expense.of("Aluguel", "Aluguel do mês", 500.0, ExpenseType.FIXED)
        val expectedBalance = 500.0

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addIncome(arrayOf(expectedIncome))
        wallet.addExpense(arrayOf(expectedExpense))

        assertEquals(expectedBalance, wallet.totalBalance())
        assertEquals(1, wallet.expenses.size)
        assertEquals(1, wallet.incomes.size)
    }

    @Test
    fun `given a valid wallet with incomes and expenses, when call total balance, should return negative balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedIncome = Income.of("Salário", "Meu salário", 200.00)
        val expectedExpense = Expense.of("Aluguel", "Aluguel do mês", 300.0, ExpenseType.FIXED)
        val expectedBalance = -100.0

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addIncome(arrayOf(expectedIncome))
        wallet.addExpense(arrayOf(expectedExpense))

        assertEquals(expectedBalance, wallet.totalBalance())
    }

    @Test
    fun `given a valid wallet, when call remove expense, should be success and return balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedIncome = Income.of("Salário", "Meu salário", 200.00)
        val expectedExpenseRemoved1 = Expense.of("Aluguel", "Aluguel do mês", 300.0, ExpenseType.FIXED)
        val expectedExpenseRemoved2 = Expense.of("Mercado", "Compras do mês", 100.0, ExpenseType.VARIABLE)
        val expectedExpense = arrayOf(
            expectedExpenseRemoved1,
            expectedExpenseRemoved2
        )
        val expectedBalance = 100.0

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addIncome(arrayOf(expectedIncome))
        wallet.addExpense(expectedExpense)

        wallet.removeExpense(expectedExpenseRemoved1)

        assertEquals(expectedBalance, wallet.totalBalance())
        assertTrue(expectedExpense.contains(expectedExpenseRemoved2))
        assertFalse(wallet.expenses.contains(expectedExpenseRemoved1))
    }

    @Test
    fun `given a valid wallet without incomes, when call balance, should return negative balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedExpense = Expense.of("Aluguel", "Aluguel do mês", 300.0, ExpenseType.FIXED)
        val expectedBalance = -300.0

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addExpense(arrayOf(expectedExpense))

        assertEquals(expectedBalance, wallet.totalBalance())
    }

    @Test
    fun `given a valid wallet with a income, when calls remove income, should return balance`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"
        val expectedIncome1 = Income.of("Salário", "Meu salário", 200.00)
        val expectedIncome2 = Income.of("Marketing digital", "Pagamento por vendas online", 300.0)
        val expectedBalance = 300.0

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        wallet.addIncome(arrayOf(expectedIncome1, expectedIncome2))

        wallet.removeIncome(expectedIncome1)

        assertEquals(expectedBalance, wallet.totalBalance())
        assertFalse(wallet.incomes.isEmpty())
        assertTrue(wallet.incomes.contains(expectedIncome2))
        assertTrue(wallet.incomes.size == 1)
    }

    @Test
    fun `given a valid wallet, when calls update, should update wallet`(){
        val expectedOwnerID = "59e84350-3e89-4443-ac9e-225f4446c9f6"
        val expectedName = "Carteira de Teste"
        val expectedDescription = "Minha carteira de testes"

        val updatedName = "Carteira de Teste Atualizada"
        val updatedDescription = "Minha carteira de testes atualizada"

        val wallet = Wallet.of(expectedOwnerID, expectedName, expectedDescription)
        val walletUpdated = wallet.update(updatedName, updatedDescription)

        assertEquals(updatedName, walletUpdated.name)
        assertEquals(updatedDescription, walletUpdated.description)

    }

}