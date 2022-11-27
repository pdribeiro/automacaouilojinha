package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;

    }

    public LoginPage informaroUsuario(String usuario){

        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        return this;
    }

    public LoginPage informarSenha(String senha){
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys(senha);

        return this;

    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[name='action']")).click();

        return new  ListaDeProdutosPage(navegador);
    }

}
