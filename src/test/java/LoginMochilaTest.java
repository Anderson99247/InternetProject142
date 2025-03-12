// 1 - Bibliotecas / imports
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;    // bibliioteca principal
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;   // biblioteca do chrome

// 2 - classe
public class LoginMochilaTest {

    // 2.1 - Atributos
    private WebDriver driver;    // objeto do selenium

    // 2.2 - Funções e Metodos

    // Antes do Teste
    @BeforeEach
    public void iniciar() {    
        driver = new ChromeDriver();    // instanciar o objeto do selenium como chromeDriver


    }


    // Depois do Teste
    @AfterEach
    public void finalizar() {
    driver.quit(); // finaliza o objeto do Selenium

    }

    // Teste
    @Test
    public void LoginMochilaTest(){
    driver.get("https://www.saucedemo.com");   // abre o site Saucedemo
    // informa o login, senha e aperta o botão "login"
    
    WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();// adicionar a mochila

        driver.findElement(By.id("shopping_cart_container")).click(); // acessar o carrinho

        //verificar os itens no carrinho
        WebElement productName = driver.findElement(By.className("inventory_item_name"));
            WebElement productPrice = driver.findElement(By.className("inventory_item_price"));
            WebElement productQuantity = driver.findElement(By.className("cart_quantity"));

            //conferir nomes dos produtos
            System.out.println("Produto no carrinho: " + productName.getText());
            System.out.println("Preço: " + productPrice.getText());
            System.out.println("Quantidade: " + productQuantity.getText());

            //conferir se estar correto
            if (productName.getText().contains("Sauce Labs Backpack") &&
                productPrice.getText().contains("$29.99") &&
                productQuantity.getText().equals("1")) {
                System.out.println("Produto adicionado corretamente!");
            } else {
                System.out.println("Erro ao adicionar o produto.");
            }

        }



    
}






 