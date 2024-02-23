package SistemaBanco;

/*Area de lembrete: 
 * - Clean Code: 
 * I- shift codigos
 * II-pular linha menu   */

	import java.util.ArrayList;
import java.util.List;

	public abstract class ContaBancaria {
    private int numero;
    private String agencia;
    private List<Pessoa> titulares;
    private double saldo;

    	public ContaBancaria(int numero, String agencia) {
    	this.numero = numero;
        this.agencia = agencia;
        this.titulares = new ArrayList<>();
    }

    	public int getNumero() {
        return numero;
    }

    	public String getAgencia() {
        return agencia;
    }

    	public double getSaldo() {
        return saldo;
    }

    	public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    	public void adicionarTitular(Pessoa titular) {
        titulares.add(titular);
    }

    	public void removerTitular(Pessoa titular) {
        titulares.remove(titular);
    }

    	public void excluirTitular(String cpf) {
        titulares.removeIf(titular -> titular.getCpf().equals(cpf));
    }

    	public List<Pessoa> getTitulares() {
        return new ArrayList<>(titulares);
    }

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public abstract void transferir(ContaBancaria destino, double valor);

    @Override
    	public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Número: ").append(numero).append("\n")
        .append("Agência: ").append(agencia).append("\n")
        .append("Titulares: ").append(titulares).append("\n")
        .append("Saldo: ").append(saldo);
        return stringBuilder.toString();
    }
}
