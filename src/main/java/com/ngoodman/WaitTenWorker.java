package com.ngoodman;

import java.util.logging.Logger;

public class WaitTenWorker extends Worker
{
    private final static Logger LOGGER = Logger.getLogger(WaitTenWorker.class.getName());

    public WaitTenWorker(SessionManager sessionManager)
    {
        super(sessionManager);
    }

    protected void workerTask()
    {
        try
        {
            LOGGER.info("Waiting 10 seconds");
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
