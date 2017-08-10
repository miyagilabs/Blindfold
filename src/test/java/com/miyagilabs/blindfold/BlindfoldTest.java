/*
 * Copyright (C) 2017 Miyagilabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.miyagilabs.blindfold;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Görkem Mülayim
 */
public class BlindfoldTest {
    private static final Logger LOGGER = Logger.getLogger(BlindfoldTest.class.getName());
    private volatile boolean success;

    /**
     * Test of main method, of class Blindfold.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testMain() throws InterruptedException {
        Thread thread = new Thread() { // Wrapper thread.
            @Override
            public void run() {
                try {
                    Blindfold.main(new String[]{}); // Run JavaFX application.
                } catch(Throwable t) {
                    Throwable cause = t.getCause();
                    if(cause != null && cause.getClass().equals(InterruptedException.class)) {
                        // We expect to get this exception since we interrupted
                        // the JavaFX application.
                        success = true;
                        return;
                    }
                    // This is not the exception we are looking for so log it.
                    LOGGER.log(Level.SEVERE, null, t);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5000);  // Wait for 5 seconds before interrupting JavaFX application
        thread.interrupt();
        thread.join(1000); // Wait 1 second for our wrapper thread to finish.
        assertTrue("Something went wrong while starting GUI.", success);
    }
}
