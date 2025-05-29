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
package org.eclipse.kura.example.factory.test;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.kura.example.factory.FactoryComponentExample;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleComponentTest {

    private static final Logger logger = LoggerFactory.getLogger(FactoryComponentExample.class);

    private FactoryComponentExample exampleComponent = new FactoryComponentExample();
    private Map<String, Object> properties = new HashMap<>();

    @Test
    public void shouldActivate() {
        givenExampleComponent();
    }

    private void givenExampleComponent() {
        this.exampleComponent = new FactoryComponentExample();
    }

}