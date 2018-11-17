package com.github.hehe3301.test;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.AttachmentPartEntry;
import sx.blah.discord.util.Image;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.cache.LongMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

public class MockChannel implements IChannel {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public MessageHistory getMessageHistory() {
        return null;
    }

    @Override
    public MessageHistory getMessageHistory(int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryFrom(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryFrom(LocalDateTime localDateTime, int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryFrom(long l) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryFrom(long l, int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryTo(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryTo(LocalDateTime localDateTime, int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryTo(long l) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryTo(long l, int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryIn(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryIn(LocalDateTime localDateTime, LocalDateTime localDateTime1, int i) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryIn(long l, long l1) {
        return null;
    }

    @Override
    public MessageHistory getMessageHistoryIn(long l, long l1, int i) {
        return null;
    }

    @Override
    public MessageHistory getFullMessageHistory() {
        return null;
    }

    @Override
    public List<IMessage> bulkDelete() {
        return null;
    }

    @Override
    public List<IMessage> bulkDelete(List<IMessage> list) {
        return null;
    }

    @Override
    public int getMaxInternalCacheCount() {
        return 0;
    }

    @Override
    public int getInternalCacheCount() {
        return 0;
    }

    @Override
    public IMessage getMessageByID(long l) {
        return null;
    }

    @Override
    public IGuild getGuild() {
        return null;
    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public String getTopic() {
        return null;
    }

    @Override
    public String mention() {
        return null;
    }

    @Override
    public IMessage sendMessage(String s) {
        System.out.println(s);
        return null;
    }

    @Override
    public IMessage sendMessage(EmbedObject embedObject) {
        return null;
    }

    @Override
    public IMessage sendMessage(String s, boolean b) {
        return null;
    }

    @Override
    public IMessage sendMessage(String s, EmbedObject embedObject) {
        return null;
    }

    @Override
    public IMessage sendMessage(String s, EmbedObject embedObject, boolean b) {
        return null;
    }

    @Override
    public IMessage sendFile(File file)  {
        return null;
    }

    @Override
    public IMessage sendFiles(File... files) {
        return null;
    }

    @Override
    public IMessage sendFile(String s, File file) {
        return null;
    }

    @Override
    public IMessage sendFiles(String s, File... files) {
        return null;
    }

    @Override
    public IMessage sendFile(EmbedObject embedObject, File file) {
        return null;
    }

    @Override
    public IMessage sendFiles(EmbedObject embedObject, File... files) {
        return null;
    }

    @Override
    public IMessage sendFile(String s, InputStream inputStream, String s1) {
        return null;
    }

    @Override
    public IMessage sendFiles(String s, AttachmentPartEntry... attachmentPartEntries) {
        return null;
    }

    @Override
    public IMessage sendFile(EmbedObject embedObject, InputStream inputStream, String s) {
        return null;
    }

    @Override
    public IMessage sendFiles(EmbedObject embedObject, AttachmentPartEntry... attachmentPartEntries) {
        return null;
    }

    @Override
    public IMessage sendFile(String s, boolean b, InputStream inputStream, String s1) {
        return null;
    }

    @Override
    public IMessage sendFiles(String s, boolean b, AttachmentPartEntry... attachmentPartEntries) {
        return null;
    }

    @Override
    public IMessage sendFile(String s, boolean b, InputStream inputStream, String s1, EmbedObject embedObject) {
        return null;
    }

    @Override
    public IMessage sendFiles(String s, boolean b, EmbedObject embedObject, AttachmentPartEntry... attachmentPartEntries) {
        return null;
    }

    @Override
    public IMessage sendFile(MessageBuilder messageBuilder, InputStream inputStream, String s) {
        return null;
    }

    @Override
    public IInvite createInvite(int i, int i1, boolean b, boolean b1) {
        return null;
    }

    @Override
    public void toggleTypingStatus() {

    }

    @Override
    public void setTypingStatus(boolean b) {

    }

    @Override
    public boolean getTypingStatus() {
        return false;
    }

    @Override
    public void edit(String s, int i, String s1) {

    }

    @Override
    public void changeName(String s) {

    }

    @Override
    public void changePosition(int i) {

    }

    @Override
    public void changeTopic(String s) {

    }

    @Override
    public void changeNSFW(boolean b) {

    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public void delete() {

    }

    /**
     * @deprecated
     */
    @Override
    public LongMap<sx.blah.discord.handle.obj.PermissionOverride> getUserOverridesLong() {
        return null;
    }

    @Override
    public LongMap<sx.blah.discord.handle.obj.PermissionOverride> getUserOverrides() {
        return null;
    }

    /**
     * @deprecated
     */
    @Override
    public LongMap<sx.blah.discord.handle.obj.PermissionOverride> getRoleOverridesLong() {
        return null;
    }

    @Override
    public LongMap<sx.blah.discord.handle.obj.PermissionOverride> getRoleOverrides() {
        return null;
    }

    @Override
    public EnumSet<Permissions> getModifiedPermissions(IUser iUser) {
        return null;
    }

    @Override
    public EnumSet<Permissions> getModifiedPermissions(IRole iRole) {
        return null;
    }

    @Override
    public void removePermissionsOverride(IUser iUser) {

    }

    @Override
    public void removePermissionsOverride(IRole iRole) {

    }

    @Override
    public void overrideRolePermissions(IRole iRole, EnumSet<Permissions> enumSet, EnumSet<Permissions> enumSet1) {

    }

    @Override
    public void overrideUserPermissions(IUser iUser, EnumSet<Permissions> enumSet, EnumSet<Permissions> enumSet1) {

    }

    @Override
    public List<IExtendedInvite> getExtendedInvites() {
        return null;
    }

    @Override
    public List<IUser> getUsersHere() {
        return null;
    }

    @Override
    public List<IMessage> getPinnedMessages() {
        return null;
    }

    @Override
    public void pin(IMessage iMessage) {

    }

    @Override
    public void unpin(IMessage iMessage) {

    }

    @Override
    public List<IWebhook> getWebhooks() {
        return null;
    }

    @Override
    public IWebhook getWebhookByID(long l) {
        return null;
    }

    @Override
    public List<IWebhook> getWebhooksByName(String s) {
        return null;
    }

    @Override
    public IWebhook createWebhook(String s) {
        return null;
    }

    @Override
    public IWebhook createWebhook(String s, Image image) {
        return null;
    }

    @Override
    public IWebhook createWebhook(String s, String s1) {
        return null;
    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public void changeCategory(ICategory iCategory) {

    }

    @Override
    public ICategory getCategory() {
        return null;
    }

    @Override
    public IDiscordClient getClient() {
        return null;
    }

    @Override
    public IShard getShard() {
        return null;
    }

    @Override
    public IChannel copy() {
        return null;
    }

    @Override
    public long getLongID() {
        return 0;
    }
}
