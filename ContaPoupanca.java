package SistemaBanco;

public class ContaPoupanca extends ContaBancaria {
    private int diaAniversario;
    private double taxaJuros;

    	public ContaPoupanca(int numero, String agencia, int diaAniversario, double taxaJuros) {
        super(numero, agencia);
        this.diaAniversario = diaAniversario;
        this.taxaJuros = taxaJuros;
    }

    
    	public void sacar(double valor) {
        if (valor > 0 && getSaldo() >= valor) {
        setSaldo(getSaldo() - valor);
        atualizarSaldo();
        System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
        System.out.println("Saldo insuficiente para o saque ou valor inválido.");
        }
    }

    
    	public void depositar(double valor) {
        if (valor > 0) {
        setSaldo(getSaldo() + valor);
        atualizarSaldo();
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        } else {
        System.out.println("Valor inválido para depósito.");
        }
    }

    
    		public void transferir(ContaBancaria destino, double valor) {
    		if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            destino.depositar(valor);
            atualizarSaldo();
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
    		} else {
            System.out.println("Saldo insuficiente para a transferência ou valor inválido.");
        }
    }

    	private double calcularJuros() {
        return taxaJuros * getSaldo();
    }

    	private void atualizarSaldo() {
        double juros = calcularJuros();
        setSaldo(getSaldo() + juros);
    }
}
