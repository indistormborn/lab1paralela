/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concorrencia;

/**
 *
 * @author 09247872952
 */
public class Reader implements Runnable {

private Buffer mensagem;

    public Reader(Buffer msg) {
        this.mensagem = msg;
    }

    @Override
    public void run() {
        for (int i = 0; i < 120; i++) { 
            while(mensagem.ler() != 0){}  
        }
    }
    
}
