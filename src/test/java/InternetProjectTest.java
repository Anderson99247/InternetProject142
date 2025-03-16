// 1 - Bibliotecas / imports
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;    // bibliioteca principal
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;   // biblioteca do chrome

// 2 - classe
public class InternetProjectTest {

    // 2.1 - Atributos
    private WebDriver driver;    // objeto do selenium

    // 2.2 - Funções e Metodos

    // Antes do Teste
    @BeforeEach
    public void iniciar() {    
        driver = new ChromeDriver();          // instanciar o objeto do selenium como chromeDriver
        driver.manage().window().maximize();  // Maximiza a janela do navegador

    }


    // Depois do Teste
    @AfterEach
    public void finalizar() {
    driver.quit(); // finaliza o objeto do Selenium

    }

    // Método auxiliar para login (sem @Test)
    
    public void realizarLogin(String usuario, String senha) {
        driver.get("https://the-internet.herokuapp.com/login");   // Abre o site, the-internet.herokuapp.com
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys(usuario);
        password.sendKeys(senha);
        loginButton.click();
    }

    @Test
    public void loginComSucesso() {
        realizarLogin("tomsmith", "SuperSecretPassword!");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("You logged into a secure area!")); // 1 teste positivo.
    }

    @Test
    public void loginUsuarioErrado() {
        realizarLogin("usuarioErrado", "SuperSecretPassword!");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!")); // 8° teste negativo
    }

    @Test
    public void loginSenhaErrada() {
        realizarLogin("tomsmith", "senhaErrada");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your password is invalid!")); // 7° teste negativo
    }

    @Test
    public void loginUsuarioVazio() {
        realizarLogin("", "SuperSecretPassword!");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!")); // 6° teste negativo
    }

    @Test
    public void loginSenhaVazia() {
        realizarLogin("tomsmith", "");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your password is invalid!")); // 5° teste negativo
    }

    @Test
    public void loginAmbosErrados() {
        realizarLogin("usuarioErrado", "senhaErrada");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!"));  // 4° teste negativo
    }

    @Test
    public void loginUsuarioErradoSenhaVazia() {
        realizarLogin("usuarioErrado", "");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!"));  // 3° teste negativo
    }

    @Test
    public void loginUsuarioVazioSenhaErrada() {
        realizarLogin("", "senhaErrada");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!"));  // 2° teste negativo
    }

    @Test
    public void loginAmbosVazios() {
        realizarLogin("", "");
        WebElement mensagem = driver.findElement(By.id("flash"));
        assertTrue(mensagem.getText().contains("Your username is invalid!"));  // 1° teste negativo
    }
}
