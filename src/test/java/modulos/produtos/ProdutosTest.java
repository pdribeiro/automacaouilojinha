package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Teste Web do modulo de produtos")

public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //Abrir navegador
        System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
         this.navegador = new ChromeDriver();
        // Vou maximizar a tela
        this.navegador.manage().window().maximize();
        // timeout
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não e permitido registrar um produto com o valor igual a 0")
    public void testNaoePermitidoRegistrarProdutoComValorIgualAZero(){

        // Fazer login
     String mensagemApresentada = new LoginPage(navegador)
                .informaroUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS5")
                .informarValorDoProduto("000")
                .informarCorDoProduto("Preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

     Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);


        }
        @Test
        @DisplayName("Não é permitido registrar um produto com valor acima de 7000")
        public void testNaoePermitidoRegistrarProdutoComValorAcimaDe7Mil(){

            String mensagemApresentada = new LoginPage(navegador)
                    .informaroUsuario("admin")
                    .informarSenha("admin")
                    .submeterFormularioDeLogin()
                    .acessarFormularioAdicaoNovoProduto()
                    .informarNomeDoProduto("PS5")
                    .informarValorDoProduto("700001")
                    .informarCorDoProduto("Azul")
                    .submeterFormularioDeAdicaoComErro()
                    .capturarMensagemApresentada();

            Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

        }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na Faixa entre R$ 0,01 e R$ 7.000,00")
    public void testAdcionarProdutoComSucesso(){

        String mensagemApresentada =  new LoginPage(navegador)
                .informaroUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS5")
                .informarValorDoProduto("1000")
                .informarCorDoProduto("Azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);



    }

    @Test
    @DisplayName("Posso adicionar produtos com valor de 70000")
    public void testAdcionarProdutoComSucessoProduto70000(){

        String mensagemApresentada =  new LoginPage(navegador)
                .informaroUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS5")
                .informarValorDoProduto("700000")
                .informarCorDoProduto("Azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);



    }

        @AfterEach
        public void afterEach(){

            navegador.quit();

        }

}
