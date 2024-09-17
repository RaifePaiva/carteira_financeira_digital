package br.com.istack.carteira_financeira_digital.domain.owner

import br.com.istack.carteira_financeira_digital.domain.wallet.Wallet
import br.com.istack.carteira_financeira_digital.exception.DomainException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class OwnerTest{

    @Test
    fun `given a valid owner, when calls add wallet, should return owner with a new wallet`(){
        val owner = Owner.of("John Doe")
        val wallet = Wallet.of(owner.id.value,"My Wallet", "My Wallet Description")

        owner.addWallet(wallet.id)
        val wallets = owner.wallets

        assertNotNull(wallets)
        assertTrue(wallets!!.isNotEmpty())
        assertEquals(wallets.first(), wallet.id)
    }

    @Test
    fun `given a valid params, when calls create a owner, should return a intance of owner`(){
        val expectedName = "John Doe"

        val owner = Owner.of(expectedName)

        val creationDate = owner.createdAt
        val now = LocalDateTime.now()

        assertTrue(owner.active)
        assertEquals(expectedName, owner.name)
        assertNotNull(owner.id)
        assertNotNull(owner.createdAt)
        assertNull(owner.updatedAt)
        assertNull(owner.deletedAt)
        assertNotNull(owner.wallets)
        assertEquals(creationDate?.truncatedTo(ChronoUnit.SECONDS), now.truncatedTo(ChronoUnit.SECONDS))
    }

    @Test
    fun `given a owner, when calls update owner, should return owner updated`(){
        val owner = Owner.of("John Doe")
        val expectedName = "John Doe Updated"

        val updatedOwner = owner.update(expectedName)

        val updateDate = updatedOwner.updatedAt
        val now = LocalDateTime.now()

        assertTrue(updatedOwner.active)
        assertEquals(expectedName, updatedOwner.name)
        assertNotNull(updatedOwner.id)
        assertNotNull(updatedOwner.createdAt)
        assertNotNull(updatedOwner.updatedAt)
        assertNull(updatedOwner.deletedAt)
        assertNotNull(updatedOwner.wallets)
        assertEquals(updateDate?.truncatedTo(ChronoUnit.SECONDS), now.truncatedTo(ChronoUnit.SECONDS))
    }

    @Test
    fun `given a owner, when calls deactivate owner, should return owner deactivated`(){
        val owner = Owner.of("John Doe")

        val deactivatedOwner = owner.deactivate()

        val deactivateDate = deactivatedOwner.deletedAt
        val now = LocalDateTime.now()

        assertFalse(deactivatedOwner.active)
        assertEquals(owner.name, deactivatedOwner.name)
        assertNotNull(deactivatedOwner.id)
        assertNotNull(deactivatedOwner.createdAt)
        assertNotNull(deactivatedOwner.updatedAt)
        assertNotNull(deactivatedOwner.deletedAt)
        assertNotNull(deactivatedOwner.wallets)
        assertEquals(deactivateDate?.truncatedTo(ChronoUnit.SECONDS), now.truncatedTo(ChronoUnit.SECONDS))
    }

    @Test
    fun `given a owner, when calls activate owner, should return owner activated`(){
        val owner = Owner.of("John Doe").deactivate()

        val activatedOwner = owner.activate()

        val activateDate = activatedOwner.updatedAt
        val now = LocalDateTime.now()

        assertTrue(activatedOwner.active)
        assertEquals(owner.name, activatedOwner.name)
        assertNotNull(activatedOwner.id)
        assertNotNull(activatedOwner.createdAt)
        assertNotNull(activatedOwner.updatedAt)
        assertNotNull(activatedOwner.deletedAt)
        assertNotNull(activatedOwner.wallets)
        assertEquals(activateDate?.truncatedTo(ChronoUnit.SECONDS), now.truncatedTo(ChronoUnit.SECONDS))
    }

    @Test
    fun `given invalid params for owner, when calls create Owner, should throws exception`(){
        // this validation, verify if the name contains less than 3 characters
        val invalidOwnerNameExpected = "Jo"

        val result = assertThrows<DomainException> {
            Owner.of(invalidOwnerNameExpected)
        }

        assertEquals("'name' contains less than 3 characters", result.message)
    }

    @Test
    fun `given invalid params for owner, when calls update Owner, should throws exception`(){
        val owner = Owner.of("John Doe")
        // this validation, verify if the name contains less than empty characters
        val invalidOwnerNameExpected = ""

        val result = assertThrows<DomainException> {
            owner.update(invalidOwnerNameExpected)
        }

        assertEquals("'name' is not valid", result.message)
    }

}