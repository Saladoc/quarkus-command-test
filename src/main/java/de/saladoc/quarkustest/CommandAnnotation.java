package de.saladoc.quarkustest;

import io.quarkus.arc.properties.IfBuildProperty;
import net.kautler.command.api.CommandContext;
import net.kautler.command.api.annotation.Alias;
import net.kautler.command.api.annotation.Description;
import net.kautler.command.api.slash.javacord.SlashCommandJavacord;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.util.logging.ExceptionLogger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@IfBuildProperty(name = "bot.cmd.annotation", stringValue = "true")
@Alias("annotation")
@Description("A command configured via annotations")
public class CommandAnnotation implements SlashCommandJavacord {

    @Override
    public void execute(CommandContext<? extends SlashCommandInteraction> commandContext) {
        commandContext.getMessage().createImmediateResponder()
                .setFlags(MessageFlag.EPHEMERAL)
                .setContent("Annotation-based command executed")
                .respond().exceptionally(ExceptionLogger.get());
    }
}
