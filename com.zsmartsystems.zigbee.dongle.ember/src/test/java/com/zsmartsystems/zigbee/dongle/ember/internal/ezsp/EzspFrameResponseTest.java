/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNoCallbacksResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspFrameResponseTest {

    @Test
    public void testResponseV4() {
        EzspVersionResponse response = new EzspVersionResponse(new int[] { 0x01, 0x80, 0x00, 0x04, 0x02, 0x00, 0x59 });
        System.out.println(response);

        assertEquals(4, response.getProtocolVersion());
        assertEquals(0x5900, response.getStackVersion());
    }

    @Test
    public void testResponseV5() {
        EzspVersionResponse response = new EzspVersionResponse(
                new int[] { 0x01, 0x80, 0xFF, 0x00, 0x00, 0x05, 0x02, 0x10, 0x5A });
        System.out.println(response);

        assertEquals(5, response.getProtocolVersion());
        assertEquals(0x5A10, response.getStackVersion());
    }

    @Test
    public void testResponse() {
        EzspNoCallbacksResponse response = new EzspNoCallbacksResponse(new int[] { 0x2C, 0x88, 0x07 });
        System.out.println(response);
        assertFalse(response.isCallbackPending());

        response = new EzspNoCallbacksResponse(new int[] { 0x2C, 0x8C, 0x07 });
        System.out.println(response);
        assertTrue(response.isCallbackPending());
    }
}