package com.github.hehe3301.test;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.cache.LongMap;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public class MockUser implements IUser
{
    @Override
    public String getName()
    {
        return "DUMMY_USER";
    }

    @Override
    public String getAvatar()
    {
        return null;
    }

    @Override
    public String getAvatarURL()
    {
        return null;
    }

    @Override
    public IPresence getPresence()
    {
        return null;
    }

    @Override
    public String getDisplayName(IGuild iGuild)
    {
        return null;
    }

    @Override
    public String mention()
    {
        return this.mention(true);
    }

    @Override
    public String mention(boolean b)
    {
        return "@DUMMY_USER";
    }

    @Override
    public String getDiscriminator()
    {
        return null;
    }

    @Override
    public List<IRole> getRolesForGuild(IGuild iGuild)
    {
        return null;
    }

    @Override
    public Color getColorForGuild(IGuild iGuild)
    {
        return null;
    }

    @Override
    public EnumSet<Permissions> getPermissionsForGuild(IGuild iGuild)
    {
        return null;
    }

    @Override
    public String getNicknameForGuild(IGuild iGuild)
    {
        return null;
    }

    @Override
    public IVoiceState getVoiceStateForGuild(IGuild iGuild)
    {
        return null;
    }

    /**
     * @deprecated
     */
    @Override
    public LongMap<IVoiceState> getVoiceStatesLong()
    {
        return null;
    }

    @Override
    public LongMap<IVoiceState> getVoiceStates()
    {
        return null;
    }

    @Override
    public void moveToVoiceChannel(IVoiceChannel iVoiceChannel)
    {

    }

    @Override
    public boolean isBot()
    {
        return false;
    }

    @Override
    public IPrivateChannel getOrCreatePMChannel()
    {
        return null;
    }

    @Override
    public void addRole(IRole iRole)
    {

    }

    @Override
    public void removeRole(IRole iRole)
    {

    }

    @Override
    public boolean hasRole(IRole iRole)
    {
        return false;
    }

    @Override
    public IDiscordClient getClient()
    {
        return null;
    }

    @Override
    public IShard getShard()
    {
        return null;
    }

    @Override
    public IUser copy()
    {
        return null;
    }

    @Override
    public long getLongID()
    {
        return 0;
    }
}
