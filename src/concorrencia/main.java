/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concorrencia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 09247872952
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        ScheduledExecutorService threadsWriteras = Executors.newScheduledThreadPool(1);
        ExecutorService threadsReaderas = Executors.newFixedThreadPool(4);

        try {
            for (int i = 0; i < 120; i++) {
                
                threadsReaderas.execute(new Reader(buffer)); 
                // cria e tenta iniciar as threads leitoras e as coloca no estado executável 
                //(pronto -> executando e executando -> pronto) fornecendo acesso a cada uma das 4, 
                //no pool, ao buffer.

                threadsWriteras.scheduleAtFixedRate(new Writer(buffer), 0, 300, TimeUnit.MILLISECONDS); 
                // cria e tenta iniciar as threads escritoras e as coloca no estado executável, 
                // fornecendo acesso a cada uma ao buffer, uma por vez.
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        threadsWriteras.shutdownNow();
        threadsReaderas.shutdownNow();
        
        while(!threadsWriteras.isTerminated() ){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { 
                System.out.println("Thread: "+Thread.currentThread().getName()+ " não finalizada.");
            }
        }
        System.exit(0);
    }
    
}
