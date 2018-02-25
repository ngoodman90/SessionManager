package com.ngoodman;

import java.util.logging.Logger;

public abstract class Worker
{
    private SessionManager sessionManager;
    private Session session;

    private final static Logger LOGGER = Logger.getLogger(Worker.class.getName());

    public Worker(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    private void requestSession()
    {
        try
        {
            session = sessionManager.requestSession();
        }
        catch (Exception e)
        {
            LOGGER.info("Unable to get session\n" + e.getMessage());
        }
    }

    private void returnSession()
    {
        if (session != null)
        {
            try
            {
                sessionManager.returnSession(session);
            }
            catch (Exception e)
            {
                LOGGER.info("Unable to return session\n" + e.getMessage());
            }
        }
    }

    private void invalidateSession()
    {
        if (session != null)
        {
            try
            {
                sessionManager.invalidateSession(session);
            }
            catch (Exception e)
            {
                LOGGER.info("Unable to invalidate session\n" + e.getMessage());
            }
        }
    }

    public final WorkerStatusEnum runJob()
    {
        WorkerStatusEnum status;
        requestSession();

        try
        {
            status = WorkerStatusEnum.RUNNING;
            workerTask();
        }
        catch(Exception e)
        {
            status = WorkerStatusEnum.FAILED;
            invalidateSession();
            // do not return the object to the pool twice
            session = null;
        }
        finally
        {
            returnSession();
            status = WorkerStatusEnum.COMPLETED;
        }
        return status;
    }

    protected abstract void workerTask();
}
