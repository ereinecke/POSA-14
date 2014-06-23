package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	this.mRLock = new ReentrantLock(fair);
    	this.permitsAvail = permits;
    	
    	// Create the permitsZero Condition
    	this.permitsZero = this.mRLock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	this.mRLock.lockInterruptibly();
    	
    	try {
	    	while (this.permitsAvail <= 0) {
	    		this.permitsZero.await();
	    	}
	    	
	    	this.permitsAvail--;
    	}
    	finally {
    		this.mRLock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	this.mRLock.lock();
    	
    	try {
	    	while (this.permitsAvail <= 0) {
	    		this.permitsZero.await();
	    	}
	    	
	    	this.permitsAvail--;
    	}
    	catch (InterruptedException ie) {
    		// Do nothing.
    	}
    	finally {
    		this.mRLock.unlock();
    	}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	this.mRLock.lock();
    	
    	this.permitsAvail++;
    	
    	this.permitsZero.signal();
    	
    	this.mRLock.unlock();
    }

    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    ReentrantLock mRLock = null;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    Condition permitsZero = null; 

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    int permitsAvail = 3;
}

