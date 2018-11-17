package com.github.hehe3301.test;

import com.vdurmont.emoji.Emoji;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.MessageTokenizer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MockMessage implements IMessage
{
    private String chatMessage;
    private IChannel channel;
    private IUser author;

    public MockMessage(String chatMessage)
    {
        this.chatMessage = chatMessage;
        this.channel = new MockChannel();
        this.author = new MockUser();
    }

    @Override
    public String getContent()
    {
        return chatMessage;
    }

    @Override
    public IChannel getChannel()
    {
        return channel;
    }

    @Override
    public IUser getAuthor()
    {
        return this.author;
    }

    @Override
    public LocalDateTime getTimestamp()
    {
        return null;
    }

    @Override
    public List<IUser> getMentions()
    {
        return null;
    }

    @Override
    public List<IRole> getRoleMentions()
    {
        return null;
    }

    @Override
    public List<IChannel> getChannelMentions()
    {
        return null;
    }

    @Override
    public List<Attachment> getAttachments()
    {
        return null;
    }

    @Override
    public List<IEmbed> getEmbeds()
    {
        return null;
    }

    @Override
    public IMessage reply(String s)
    {
        return null;
    }

    @Override
    public IMessage reply(String s, EmbedObject embedObject)
    {
        return null;
    }

    @Override
    public IMessage edit(String s)
    {
        return null;
    }

    @Override
    public IMessage edit(String s, EmbedObject embedObject)
    {
        return null;
    }

    @Override
    public IMessage edit(EmbedObject embedObject)
    {
        return null;
    }

    @Override
    public boolean mentionsEveryone()
    {
        return false;
    }

    @Override
    public boolean mentionsHere()
    {
        return false;
    }

    @Override
    public void delete()
    {

    }

    @Override
    public Optional<LocalDateTime> getEditedTimestamp()
    {
        return Optional.empty();
    }

    @Override
    public boolean isPinned()
    {
        return false;
    }

    @Override
    public IGuild getGuild()
    {
        return null;
    }

    @Override
    public String getFormattedContent()
    {
        return null;
    }

    @Override
    public List<IReaction> getReactions()
    {
        return null;
    }

    @Override
    @Deprecated
    public IReaction getReactionByIEmoji(IEmoji iEmoji)
    {
        return null;
    }

    @Override
    public IReaction getReactionByEmoji(IEmoji iEmoji)
    {
        return null;
    }

    @Override
    public IReaction getReactionByID(long l)
    {
        return null;
    }

    @Override
    public IReaction getReactionByUnicode(Emoji emoji)
    {
        return null;
    }

    @Override
    public IReaction getReactionByUnicode(String s)
    {
        return null;
    }

    @Override
    public IReaction getReactionByEmoji(ReactionEmoji reactionEmoji)
    {
        return null;
    }

    @Override
    public void addReaction(IReaction iReaction)
    {

    }

    @Override
    public void addReaction(IEmoji iEmoji)
    {

    }

    @Override
    public void addReaction(Emoji emoji)
    {

    }

    @Override
    @Deprecated
    public void addReaction(String s)
    {

    }

    @Override
    public void addReaction(ReactionEmoji reactionEmoji)
    {

    }

    @Override
    @Deprecated
    public void removeReaction(IReaction iReaction)
    {

    }

    @Override
    public void removeReaction(IUser iUser, IReaction iReaction)
    {

    }

    @Override
    public void removeReaction(IUser iUser, ReactionEmoji reactionEmoji)
    {

    }

    @Override
    public void removeReaction(IUser iUser, IEmoji iEmoji)
    {

    }

    @Override
    public void removeReaction(IUser iUser, Emoji emoji)
    {

    }

    @Override
    public void removeReaction(IUser iUser, String s)
    {

    }

    @Override
    public void removeAllReactions()
    {

    }

    @Override
    public MessageTokenizer tokenize()
    {
        return null;
    }

    @Override
    public boolean isDeleted()
    {
        return false;
    }

    @Override
    public long getWebhookLongID()
    {
        return 0;
    }

    @Override
    public Type getType()
    {
        return null;
    }

    @Override
    public boolean isSystemMessage()
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
    public IMessage copy()
    {
        return null;
    }

    @Override
    public long getLongID()
    {
        return 0;
    }
}
