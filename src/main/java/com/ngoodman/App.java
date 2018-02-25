package com.ngoodman;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionManager sessionManager = new SessionManager(50);
        WaitTenWorker waitTenWorker = new WaitTenWorker(sessionManager);
        WorkerStatusEnum status = waitTenWorker.runJob();
        System.out.println(status);
    }
}
