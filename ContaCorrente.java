package SistemaBanco;

	public class ContaCorrente extends ContaBancaria {
	private double limiteCredito;

    	public ContaCorrente(int numero, String agencia, double limiteCredito) {
        super(numero, agencia);
        this.limiteCredito = limiteCredito;
    }

    
    	public void sacar(double valor) {
        if (valor > 0 && (getSaldo() + limiteCredito) >= valor) {
        setSaldo(getSaldo() - valor);
        System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
        System.out.println("Saldo insuficiente para o saque ou valor inválido.");
        }
    }

    
    public void depositar(double valor) {
    if (valor > 0) {
    setSaldo(getSaldo() + valor);
    System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
     } else {
    System.out.println("Valor inválido para depósito.");
        }
    }

    
    	public void transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && (getSaldo() + limiteCredito) >= valor) {
        setSaldo(getSaldo() - valor);
        destino.depositar(valor);
        System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        } else {
        System.out.println("Saldo insuficiente para a transferência ou valor inválido.");
        }
    }
}
