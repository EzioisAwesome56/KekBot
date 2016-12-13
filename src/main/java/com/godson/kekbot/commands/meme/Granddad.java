package com.godson.kekbot.commands.meme;

import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandCategory;
import com.godson.kekbot.KekBot;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Granddad {
    public static Command granddad = new Command("granddad")
            .withCategory(CommandCategory.MEME)
            .withDescription("FLEEENSTONES!?")
            .withUsage("{p}granddad")
            .onExecuted(context -> {
                TextChannel channel = context.getTextChannel();
                Guild server = context.getGuild();
                List<Role> checkForMeme = server.getRolesByName("Living Meme", true);
                if (checkForMeme.size() == 0) {
                    channel.sendMessage(":exclamation: __**Living Meme**__ role not found! Please add this role and assign it to me!").queue();
                } else {
                    Role meme = checkForMeme.get(0);
                    if (server.getSelfMember().getRoles().contains(meme)) {
                        if (new File("granddad").isDirectory()) {
                            File granddads[] = new File("granddad").listFiles();
                            Random random = new Random();
                            int index = random.nextInt(granddads.length);
                            Optional<VoiceChannel> voiceChannel = context.getGuild().getVoiceChannels().stream().filter(c -> c.getMembers().contains(context.getMember())).findFirst();
                            if (!voiceChannel.isPresent()) {
                                channel.sendMessage("This command requies you to be in a voice channel!").queue();
                            } else {
                                KekBot.player.loadAndMeme(context, granddads[index].getAbsolutePath());
                            }
                        }
                    } else {
                        channel.sendMessage(":exclamation: This command requires me to have the __**Living Meme**__ role.").queue();
                    }
                }
            });
}
