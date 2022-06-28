package de.saladoc.quarkustest;

import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class JavacordProvider {

    @Inject
    Logger logger;

    @Inject
    @ConfigProperty(name = "bot.token")
    String discordToken;

    @Produces
    @ApplicationScoped
    public DiscordApi discordApi() {
        DiscordApiBuilder builder = new DiscordApiBuilder()
                .setToken(discordToken)
                .setAllIntentsExcept(Intent.GUILD_PRESENCES, Intent.DIRECT_MESSAGE_TYPING, Intent.GUILD_MESSAGE_TYPING)
                .setWaitForUsersOnStartup(true)
                .setWaitForServersOnStartup(true)
                .setUserCacheEnabled(true);
        DiscordApi api = builder.login().join();
        logger.info("Bot started successfully. Invite Link: {}", api.createBotInvite());
        return api;
    }

    void disconnect(@Disposes DiscordApi api) {
        api.disconnect().thenRun(() -> logger.info("Bot disconnected"));
    }
}
