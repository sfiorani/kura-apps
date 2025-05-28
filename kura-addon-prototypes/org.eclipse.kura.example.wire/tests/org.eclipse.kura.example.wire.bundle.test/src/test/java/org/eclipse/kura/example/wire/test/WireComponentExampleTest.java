/*******************************************************************************
 * Copyright (c) 2025 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Eurotech
 *******************************************************************************/
package org.eclipse.kura.example.wire.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.kura.example.wire.WireComponentExample;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WireComponentExampleTest {

    private static final Logger logger = LoggerFactory.getLogger(WireComponentExampleTest.class);

    private WireComponentExample exampleComponent = new WireComponentExample();
    private Map<String, Object> properties = new HashMap<>();

    BundleContext context = Mockito.mock(BundleContext.class);

    @Test
    public void shouldActivate() {

        givenExampleComponent();
        // givenProperties("example", "winning.message");
        //
        // thenExampleOptionIs("example");
    }

    private void givenExampleComponent() {
        this.exampleComponent = new WireComponentExample();
    }

    private void givenProperties(String key, Object value) {
        this.properties.put(key, value);
    }

    private void thenExampleOptionIs(String examplePropertyValue) {
        assertEquals(examplePropertyValue, this.exampleComponent.getOptions().getChannelFilterName());
    }

}