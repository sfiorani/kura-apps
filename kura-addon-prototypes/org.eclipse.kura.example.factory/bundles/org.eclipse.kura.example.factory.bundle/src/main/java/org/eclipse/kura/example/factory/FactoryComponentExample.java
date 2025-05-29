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
package org.eclipse.kura.example.factory;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.kura.configuration.ConfigurableComponent;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, //
        configurationPolicy = ConfigurationPolicy.REQUIRE, //
        name = "org.eclipse.kura.example.factory.FactoryComponentExample", //
        service = { ConfigurableComponent.class } //
)
@Designate(ocd = FactoryComponentExampleOCD.class, factory = true)
public class FactoryComponentExample implements ConfigurableComponent {

    private static final Logger logger = LoggerFactory.getLogger(FactoryComponentExample.class);

    private FactoryComponentExampleOptions options;

    private ClientHandler clientHandler;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /*
     * In the in activate, modified, deactivate methods it is possible to provide
     * the ComponentContext and the ExampleComponentOCD as parameters.
     * All parameters in activate, modified, deactivate are optional and can be
     * removed if not needed
     * 
     * Examples:
     * 
     * public void activate()
     * public void activate(ExampleComponentOCD configuration)
     * public void activate(ComponentContext componentContext, final Map<String, Object> properties, final
     * ExampleComponentOCD configuration)
     */
    @Activate
    public void activate(final FactoryComponentExampleOCD properties) {
        logger.info("Activating");

        updated(properties);

        logger.info("Activated");
    }

    @Modified
    public void updated(final FactoryComponentExampleOCD properties) {
        logger.info("Updating");

        logger.debug("Updating with properties: {}", properties);

        FactoryComponentExampleOptions newOpts = new FactoryComponentExampleOptions(properties);

        if (Objects.isNull(this.clientHandler) || !this.options.equals(newOpts)) {

            this.executor.submit(() -> {
                try {
                    this.options = newOpts;

                    if (!Objects.isNull(this.clientHandler)) {
                        this.clientHandler.stopSocket();
                    }

                    this.clientHandler = new ClientHandler(newOpts);
                    this.clientHandler.startSocket();
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }

            });

        }

        logger.info("Updated");

    }

    @Deactivate
    public synchronized void deactivate() {
        logger.info("Deactivating");
        logger.info("Deactivated");
    }

    public FactoryComponentExampleOptions getOptions() {
        return this.options;
    }

}
