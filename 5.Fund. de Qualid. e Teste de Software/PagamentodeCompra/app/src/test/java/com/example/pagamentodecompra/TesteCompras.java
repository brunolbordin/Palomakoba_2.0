package com.example.pagamentodecompra;

import com.example.pagamentodecompra.util.DSL;
import com.example.pagamentodecompra.util.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TesteCompras {

        private AndroidDriver driver;
        private final DSL dsl = new DSL();

        @Before
        public void setUp(){
            driver = DriverFactory.getDriver();
        }

        @Test
        public void testaValorTotal() throws InterruptedException {
            dsl.selecionarItens("Arroz (R$ 3,50)");
            dsl.selecionarItens("Carne (R$ 11,30)");
            dsl.clicar(By.id("com.example.pagamentodecompra:id/btnTotal"));
            Thread.sleep(2000);
            Assert.assertEquals("Valor: 14.8", dsl.valorTotal(By.id("com.example.pagamentodecompra:id/" +
                    "txtValor")));
        }

        @After
        public void tearDown() {
            DriverFactory.finalizarDriver();
        }
}
