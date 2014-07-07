package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 * 
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject (which is accessed via a Condition). It must
 *        implement both "Fair" and "NonFair" semaphore semantics,
 *        just like Java Semaphores.
 */
public class SimpleSemaphore {
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
	final ReentrantLock lock;	

    /**
     * Define a Condition that waits while the number of permits is 0.
     */
    // TODO - you fill in here
	// NOTE - could've or should've been private
	private final Condition PermitsAvail;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here.  Make sure that this data member will
    // ensure its values aren't cached by multiple Threads.
	// NOTE: volatile not required if locking managed elsewhere
	// Should not be static.
	volatile int nPermitsAvail;

    public SimpleSemaphore(int permits, boolean fair) {
        // TODO - you fill in here to initialize the SimpleSemaphore,
        // making sure to allow both fair and non-fair Semaphore
        // semantics.
    	// NOTE:
        lock = new ReentrantLock( fair );
        PermitsAvail = lock.newCondition();
        nPermitsAvail = permits;
    }

    /**
     * Acquire one permit from the semaphore in a manner that can be
     * interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here.
    	// NOTE: if using lock instead of lockInterruptibly,will work but be 
    	// less responsive.
        lock.lockInterruptibly();
        try
        {
                while(nPermitsAvail == 0)
                        PermitsAvail.await();
                nPermitsAvail--;
        }
        finally
        {
                lock.unlock();
        }
    }

    /**
     * Acquire one permit from the semaphore in a manner that cannot be
     * interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here.
        lock.lock();
        try
        {
                while(nPermitsAvail == 0)
                        PermitsAvail.awaitUninterruptibly();
                nPermitsAvail--;
        }
        finally
        {
                lock.unlock();
        }    
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here.
        lock.lock();
        try
        {
                nPermitsAvail++;
                // NOTE: code below acceptable but better to optimize to 
                // for available permits, as in:
                // if (mAvailablePermits > 0)
                //   PermitsAvail.signal();
                PermitsAvail.signal();
        }
        finally
        {
                lock.unlock();
        }   	
    }

    /**
     * Return the number of permits available.
     */
    public int availablePermits() {
        // TODO - you fill in here by changing null to the appropriate
        // return value.
		// NOTE: No lock needed if nPermitsAvail is declared as volatile
        return nPermitsAvail;
    }
}
