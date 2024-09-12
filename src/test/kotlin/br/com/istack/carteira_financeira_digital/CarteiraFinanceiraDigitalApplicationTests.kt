package br.com.istack.carteira_financeira_digital

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CarteiraFinanceiraDigitalApplicationTests {

	@Test
	fun contextLoads() {
		val context = runApplication<CarteiraFinanceiraDigitalApplication>()
		assertNotNull(context)
	}

	@Test
	fun `main method runs without exceptions`() {
		assertDoesNotThrow {
			main(emptyArray())
		}
	}

}
