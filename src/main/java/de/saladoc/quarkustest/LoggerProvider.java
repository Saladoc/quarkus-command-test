package de.saladoc.quarkustest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProvider {

    @Produces
    public Logger provideLogger(InjectionPoint target) {
        return LogManager.getLogger(target.getType().getTypeName());
    }

}
