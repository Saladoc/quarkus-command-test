package de.saladoc.quarkustest;

import io.quarkus.runtime.StartupEvent;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.util.logging.ExceptionLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class JavacordRegistrar {

    @Inject
    Logger logger;

    @Inject
    List<SlashCommandBuilder> commands;

    @Inject
    DiscordApi api;

    public void onStartup(@Observes StartupEvent __) {
        logger.info("Registering {} commands.", commands.size());
        api.bulkOverwriteGlobalApplicationCommands(commands)
                .exceptionally(ExceptionLogger.get());
    }

}
