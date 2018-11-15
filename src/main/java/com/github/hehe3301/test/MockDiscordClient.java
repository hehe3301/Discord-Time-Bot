package com.github.hehe3301.test;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.modules.ModuleLoader;
import sx.blah.discord.util.Image;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MockDiscordClient implements IDiscordClient {

    private EventDispatcher dispatcher;
    public MockDiscordClient() {
        // Defaults taken from EventDispatcher source code, but with our mock
        // client in place of the real one.
        this.dispatcher = new EventDispatcher(
                this,
                new EventDispatcher.CallerRunsPolicy(),
                1,
                Runtime.getRuntime().availableProcessors() * 4,
                128,
                60L,
                TimeUnit.SECONDS
        );
    }

    @Override
    public EventDispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override
    public ModuleLoader getModuleLoader() {
        return null;
    }

    @Override
    public List<IShard> getShards() {
        return null;
    }

    @Override
    public int getShardCount() {
        return 0;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void login() {
        System.out.println("login was called.");
    }

    @Override
    public void logout() {

    }

    @Override
    public void changeUsername(String s) {

    }

    @Override
    public void changeAvatar(Image image) {

    }

    @Override
    public void changePlayingText(String s) {

    }

    @Override
    public void online(String s) {

    }

    @Override
    public void online() {

    }

    @Override
    public void idle(String s) {

    }

    @Override
    public void idle() {

    }

    @Override
    public void streaming(String s, String s1) {

    }

    @Override
    public void dnd(String s) {

    }

    @Override
    public void dnd() {

    }

    @Override
    public void invisible() {

    }

    @Override
    public void mute(IGuild iGuild, boolean b) {

    }

    @Override
    public void deafen(IGuild iGuild, boolean b) {

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public IUser getOurUser() {
        return null;
    }

    @Override
    public List<IChannel> getChannels(boolean b) {
        return null;
    }

    @Override
    public List<IChannel> getChannels() {
        return null;
    }

    @Override
    public IChannel getChannelByID(long l) {
        return null;
    }

    @Override
    public List<IVoiceChannel> getVoiceChannels() {
        return null;
    }

    @Override
    public IVoiceChannel getVoiceChannelByID(long l) {
        return null;
    }

    @Override
    public List<IGuild> getGuilds() {
        return null;
    }

    @Override
    public IGuild getGuildByID(long l) {
        return null;
    }

    @Override
    public List<IUser> getUsers() {
        return null;
    }

    @Override
    public IUser getUserByID(long l) {
        return null;
    }

    @Override
    public IUser fetchUser(long l) {
        return null;
    }

    @Override
    public List<IUser> getUsersByName(String s) {
        return null;
    }

    @Override
    public List<IUser> getUsersByName(String s, boolean b) {
        return null;
    }

    @Override
    public List<IRole> getRoles() {
        return null;
    }

    @Override
    public IRole getRoleByID(long l) {
        return null;
    }

    @Override
    public List<IMessage> getMessages(boolean b) {
        return null;
    }

    @Override
    public List<IMessage> getMessages() {
        return null;
    }

    @Override
    public IMessage getMessageByID(long l) {
        return null;
    }

    @Override
    public IPrivateChannel getOrCreatePMChannel(IUser iUser) {
        return null;
    }

    @Override
    public IInvite getInviteForCode(String s) {
        return null;
    }

    @Override
    public List<IRegion> getRegions() {
        return null;
    }

    @Override
    public IRegion getRegionByID(String s) {
        return null;
    }

    @Override
    public List<IVoiceChannel> getConnectedVoiceChannels() {
        return null;
    }

    @Override
    public String getApplicationDescription() {
        return null;
    }

    @Override
    public String getApplicationIconURL() {
        return null;
    }

    @Override
    public String getApplicationClientID() {
        return null;
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public IUser getApplicationOwner() {
        return null;
    }

    @Override
    public List<ICategory> getCategories() {
        return null;
    }

    @Override
    public ICategory getCategoryByID(long l) {
        return null;
    }

    @Override
    public List<ICategory> getCategoriesByName(String s) {
        return null;
    }
}
