package com.godson.kekbot.commands.meme;

import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandCategory;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.List;

public class Salt {
    public static Command salt = new Command("salt")
            .withCategory(CommandCategory.MEME)
            .withDescription("Tells a story of how a specified user discovered salt.")
            .withUsage("{p}salt <@user>")
            .onExecuted(context -> {
                TextChannel channel = context.getTextChannel();
                Guild server = context.getGuild();
                String args[] = context.getArgs();
                List<Role> checkForMeme = server.getRolesByName("Living Meme", true);
                if (checkForMeme.size() == 0) {
                    channel.sendMessage(":exclamation: __**Living Meme**__ role not found! Please add this role and assign it to me!").queue();
                } else {
                    Role meme = checkForMeme.get(0);
                    if (server.getSelfMember().getRoles().contains(meme)) {
                        if (args.length == 0) {
                            channel.sendMessage("You must supply a name or mention!").queue();
                        } else {
                            String input = args[0];
                            channel.sendMessage("Sodium, atomic number 11, was first isolated by " + input + " in 1807. A chemical component of salt, he named it Na in honor of the saltiest region on earth, North America.").queue();
                        }
                    } else {
                        channel.sendMessage(":exclamation: This command requires me to have the __**Living Meme**__ role.").queue();
                    }
                }
            });
}
