package de.saladoc.quarkustest;

import io.quarkus.arc.properties.IfBuildProperty;
import net.kautler.command.api.CommandContext;
import net.kautler.command.api.restriction.RestrictionChainElement;
import net.kautler.command.api.restriction.javacord.slash.BotOwnerJavacordSlash;
import net.kautler.command.api.slash.javacord.SlashCommandJavacord;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.util.logging.ExceptionLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@IfBuildProperty(name = "bot.cmd.override", stringValue = "true")
public class CommandOverride implements SlashCommandJavacord {

    @Override
    public List<String> getAliases() {
        return List.of("override");
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("A command configured by overriding the methods");
    }

    @Override
    public Optional<String> getUsage() {
        return Optional.empty();
    }

    @Override
    public RestrictionChainElement getRestrictionChain() {
        return new RestrictionChainElement(BotOwnerJavacordSlash.class);
    }

    @Override
    public boolean isAsynchronous() {
        return false;
    }

    @Override
    public void execute(CommandContext<? extends SlashCommandInteraction> commandContext) {
        commandContext.getMessage().createImmediateResponder()
                .setFlags(MessageFlag.EPHEMERAL)
                .setContent("Override-based command executed")
                .respond().exceptionally(ExceptionLogger.get());
    }

    @Override
    public List<SlashCommandOption> getOptions() {
        return List.of();
    }
}
