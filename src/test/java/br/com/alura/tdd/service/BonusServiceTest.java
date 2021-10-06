package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    private ReajusteService service;
    private  Funcionario funcionario;

    @BeforeEach // estou dizendo: ANTES DE CADA UM DOS METODOS, CHAMAR O INICIALIZAR
    public  void Inicializar(){
        System.out.println("Inicializa");
        this.service=new ReajusteService();
        this.funcionario=new Funcionario( "Ana", LocalDate.now(),new BigDecimal("1000.00"));


    }
    @AfterEach // DEPOIS DE REALIZAR CADA METODO, CHARMAR O FINALIZAR
    public void finalizar(){
        System.out.println("fim");
    }
    //RODA UNICA VEZ, DEPOIS/ANTES OS METODOS SEREM FINALIZADOS obs: PRECISA SER STATIC
    @BeforeAll
    public  static void antesDeTodos(){
        System.out.println("Antes de todos");
    }
    @AfterAll
    public static void depoisDeTodos(){
        System.out.println("Depois de todos");
    }
    @Test
    public void reajusteDeveriaSerDeTresPorcentoQuandoDesempenhoForADesejar(){
        service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
        assertEquals(new BigDecimal("1030.00"),funcionario.getSalario());
    }
    @Test
    public void reajusteDeveriaSerDeQuinzePorcentoQuandoDesempenhoForBom(){
        service.concederReajuste(funcionario, Desempenho.BOM);
         assertEquals(new BigDecimal("1150.00"),funcionario.getSalario());
    }
    @Test
    public void reajusteDeveriaSerDeVintePorcentoQuandoDesempenhoForOtimo(){
        service.concederReajuste(funcionario, Desempenho.OTIMO);
        assertEquals(new BigDecimal("1200.00"),funcionario.getSalario());
    }
    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() { // testa exception
        BonusService service = new BonusService(); // chamei o metodo que eu quero testar
        //assertThrows(IllegalArgumentException.class,
          //      ()->service.calcularBonus(new Funcionario("Fernando", LocalDate.now(),new BigDecimal("25000")))); // passei o paramtro

        try{
            service.calcularBonus(new Funcionario("Fernando", LocalDate.now(),new BigDecimal("25000")));
            fail("Nao deu a exception");
        }catch (Exception e ){
            assertEquals("Funcionario com salario maior do que 1000 nao pode receber bonus",e.getMessage());
        }
    }
    @Test
    void bonusDeveriaSer10PorCentoDoSalario() {
        BonusService service = new BonusService(); // chamei o metodo que eu quero testar
        BigDecimal bonus = service.calcularBonus(new Funcionario("Fernando", LocalDate.now(),new BigDecimal("2500"))); // passei o paramtro
        assertEquals(new BigDecimal("250.00"),bonus);
    }
    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10000() {
        BonusService service = new BonusService(); // chamei o metodo que eu quero testar
        BigDecimal bonus = service.calcularBonus(new Funcionario("Fernando", LocalDate.now(),new BigDecimal("10000"))); // passei o paramtro
        assertEquals(new BigDecimal("1000.00"),bonus);
    }
}
/**
 *  assertEquals - Para validar se a implementação atende aos requisitos esperados.
 */