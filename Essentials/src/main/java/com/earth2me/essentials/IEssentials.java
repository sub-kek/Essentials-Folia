package com.earth2me.essentials;

import com.earth2me.essentials.api.IItemDb;
import com.earth2me.essentials.api.IJails;
import com.earth2me.essentials.api.IWarps;
import com.earth2me.essentials.commands.IEssentialsCommand;
import com.earth2me.essentials.commands.PlayerNotFoundException;
import com.earth2me.essentials.perm.PermissionsHandler;
import com.earth2me.essentials.updatecheck.UpdateChecker;
import com.earth2me.essentials.userstorage.IUserMap;
import net.ess3.nms.refl.providers.ReflOnlineModeProvider;
import net.ess3.provider.BannerDataProvider;
import net.ess3.provider.BiomeKeyProvider;
import net.ess3.provider.ContainerProvider;
import net.ess3.provider.DamageEventProvider;
import net.ess3.provider.FormattedCommandAliasProvider;
import net.ess3.provider.InventoryViewProvider;
import net.ess3.provider.ItemUnbreakableProvider;
import net.ess3.provider.KnownCommandsProvider;
import net.ess3.provider.MaterialTagProvider;
import net.ess3.provider.PersistentDataProvider;
import net.ess3.provider.PlayerLocaleProvider;
import net.ess3.provider.SchedulingProvider;
import net.ess3.provider.SerializationProvider;
import net.ess3.provider.ServerStateProvider;
import net.ess3.provider.SignDataProvider;
import net.ess3.provider.SpawnerBlockProvider;
import net.ess3.provider.SpawnerItemProvider;
import net.ess3.provider.SyncCommandsProvider;
import net.ess3.provider.WorldInfoProvider;
import net.essentialsx.api.v2.services.BalanceTop;
import net.essentialsx.api.v2.services.mail.MailService;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public interface IEssentials extends Plugin {
    void addReloadListener(IConf listener);

    void reload();

    Map<String, IEssentialsCommand> getCommandMap();

    List<String> onTabCompleteEssentials(CommandSender sender, Command command, String commandLabel, String[] args, ClassLoader classLoader, String commandPath, String permissionPrefix, IEssentialsModule module);

    boolean onCommandEssentials(CommandSender sender, Command command, String commandLabel, String[] args, ClassLoader classLoader, String commandPath, String permissionPrefix, IEssentialsModule module);

    @Deprecated
    User getUser(Object base);

    User getUser(UUID base);

    User getUser(String base);

    User getUser(Player base);

    User matchUser(Server server, User sourceUser, String searchTerm, Boolean getHidden, boolean getOffline) throws PlayerNotFoundException;

    boolean canInteractWith(CommandSource interactor, User interactee);

    boolean canInteractWith(User interactor, User interactee);

    I18n getI18n();

    User getOfflineUser(String name);

    World getWorld(String name);

    int broadcastMessage(String message);

    int broadcastMessage(IUser sender, String message);

    int broadcastMessage(IUser sender, String message, Predicate<IUser> shouldExclude);

    int broadcastMessage(String permission, String message);

    void broadcastTl(String tlKey, Object... args);

    void broadcastTl(IUser sender, String tlKey, Object... args);

    void broadcastTl(IUser sender, String permission, String tlKey, Object... args);

    void broadcastTl(IUser sender, Predicate<IUser> shouldExclude, String tlKey, Object... args);

    void broadcastTl(IUser sender, Predicate<IUser> shouldExclude, boolean parseKeywords, String tlKey, Object... args);

    ISettings getSettings();

    IJails getJails();

    IWarps getWarps();

    Worth getWorth();

    Backup getBackup();

    Kits getKits();

    RandomTeleport getRandomTeleport();

    UpdateChecker getUpdateChecker();

    void runTaskAsynchronously(Runnable run);

    void runTaskLaterAsynchronously(Runnable run, long delay);

    SchedulingProvider.EssentialsTask runTaskTimerAsynchronously(Runnable run, long delay, long period);

    void scheduleEntityDelayedTask(Entity entity, Runnable run);

    SchedulingProvider.EssentialsTask scheduleEntityDelayedTask(Entity entity, Runnable run, long delay);

    SchedulingProvider.EssentialsTask scheduleEntityRepeatingTask(Entity entity, Runnable run, long delay, long period);

    void scheduleLocationDelayedTask(Location location, Runnable run);

    void scheduleLocationDelayedTask(Location location, Runnable run, long delay);

    SchedulingProvider.EssentialsTask scheduleLocationRepeatingTask(Location location, Runnable run, long delay, long period);

    default void scheduleGlobalDelayedTask(Runnable run) {
        scheduleGlobalDelayedTask(run, 1);
    }

    SchedulingProvider.EssentialsTask scheduleGlobalDelayedTask(Runnable run, long delay);

    SchedulingProvider.EssentialsTask scheduleGlobalRepeatingTask(Runnable run, long delay, long period);

    void scheduleInitTask(Runnable runnable);

    boolean isEntityThread(Entity entity);

    boolean isRegionThread(Location location);

    boolean isGlobalThread();

    void ensureEntity(Entity entity, Runnable runnable);

    void ensureRegion(Location location, Runnable runnable);

    void ensureGlobal(Runnable runnable);

    PermissionsHandler getPermissionsHandler();

    AlternativeCommandsHandler getAlternativeCommandsHandler();

    void showError(CommandSource sender, Throwable exception, String commandLabel);

    IItemDb getItemDb();

    IUserMap getUsers();

    @Deprecated
    UserMap getUserMap();

    BalanceTop getBalanceTop();

    EssentialsTimer getTimer();

    MailService getMail();

    /**
     * Get a list of players who are vanished.
     *
     * @return A list of players who are vanished
     * @deprecated Use {@link net.ess3.api.IEssentials#getVanishedPlayersNew()} where possible.
     */
    @Deprecated
    List<String> getVanishedPlayers();

    Collection<Player> getOnlinePlayers();

    Iterable<User> getOnlineUsers();

    SpawnerItemProvider getSpawnerItemProvider();

    SpawnerBlockProvider getSpawnerBlockProvider();

    ServerStateProvider getServerStateProvider();

    MaterialTagProvider getMaterialTagProvider();

    ContainerProvider getContainerProvider();

    KnownCommandsProvider getKnownCommandsProvider();

    SerializationProvider getSerializationProvider();

    FormattedCommandAliasProvider getFormattedCommandAliasProvider();

    SyncCommandsProvider getSyncCommandsProvider();

    PersistentDataProvider getPersistentDataProvider();

    ReflOnlineModeProvider getOnlineModeProvider();

    ItemUnbreakableProvider getItemUnbreakableProvider();

    WorldInfoProvider getWorldInfoProvider();

    SignDataProvider getSignDataProvider();

    PlayerLocaleProvider getPlayerLocaleProvider();

    DamageEventProvider getDamageEventProvider();

    BiomeKeyProvider getBiomeKeyProvider();

    BannerDataProvider getBannerDataProvider();

    InventoryViewProvider getInventoryViewProvider();

    PluginCommand getPluginCommand(String cmd);
}
