package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * @class SimpleAtomicLong
 * 
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong {
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;

    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */

    // TODO -- you fill in here by replacing the null with an
    // initialization of ReentrantReadWriteLock.
    private final ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();

    // This is done according to the example inside the source code of
    // ReentrantReadWriteLock class
    // It is more efficient then calling f.i. mRWLock.writeLock() in each
    // method.
    private final Lock mReadLock = mRWLock.readLock();
    private final Lock mWriteLock = mRWLock.writeLock();

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue) {
        // TODO -- you fill in here
        mValue = initialValue;
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get() {
        long value = 0;

        // TODO -- you fill in here
        mReadLock.lock();
        try {
            value = mValue;
        } finally {
            mReadLock.unlock();
        }
        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     * 
     * @returns the updated value
     */
    public long decrementAndGet() {
        long value = 0;

        // TODO -- you fill in here
        mWriteLock.lock();
        try {
            value = --mValue;
        } finally {
            mWriteLock.unlock();
        }

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     * 
     * @returns the previous value
     */
    public long getAndIncrement() {
        long value = 0;

        // TODO -- you fill in here
        mWriteLock.lock();
        try {
            value = mValue++;
        } finally {
            mWriteLock.unlock();
        }

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     * 
     * @returns the previous value
     */
    public long getAndDecrement() {
        long value = 0;

        // TODO -- you fill in here
        mWriteLock.lock();
        try {
            value = mValue--;
        } finally {
            mWriteLock.unlock();
        }

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     * 
     * @returns the updated value
     */
    public long incrementAndGet() {
        long value = 0;

        // TODO -- you fill in here
        mWriteLock.lock();
        try {
            value = ++mValue;
        } finally {
            mWriteLock.unlock();
        }

        return value;
    }
}
