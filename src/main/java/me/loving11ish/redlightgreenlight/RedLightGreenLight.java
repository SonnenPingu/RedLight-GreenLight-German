package me.loving11ish.redlightgreenlight;

import com.rylinaux.plugman.api.PlugManAPI;
import com.tcoded.folialib.FoliaLib;
import io.papermc.lib.PaperLib;
import me.loving11ish.redlightgreenlight.commands.CommandManager;
import me.loving11ish.redlightgreenlight.events.*;
import me.loving11ish.redlightgreenlight.externalhooks.PlugManXAPI;
import me.loving11ish.redlightgreenlight.updatesystem.JoinEvent;
import me.loving11ish.redlightgreenlight.updatesystem.UpdateChecker;
import me.loving11ish.redlightgreenlight.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

private File messagesFile;
private FileConfiguration messagesConfig;

/*** Loads the language file based on the configuration.
 */
public void loadMessagesConfig() {
    String language = getConfig().getString("language", "en").toLowerCase();

    // Select file based on the language
    messagesFile = new File(getDataFolder(), "messages_" + language + ".yml");

    // If the file does not exist, copy it from the resources
    if (!messagesFile.exists()) {
        saveResource("messages_" + language + ".yml", false);
    }

    // Load file
    messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
}

/**
 * Returns the language file configuration.
 */
public FileConfiguration getMessagesConfig() {
    return messagesConfig;
}

public final class RedLightGreenLight extends JavaPlugin {

   
    @Override
    public void onEnable() {
        //Plugin startup logic
        plugin = this;
        foliaLib = new FoliaLib(plugin);
        versionCheckerUtils = new VersionCheckerUtils();
        versionCheckerUtils.setVersion();

 // Loading the main configuration file
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();

    // Loading the language file
    loadMessagesConfig();

    // Plugin start messages based on the language file
    console.sendMessage(ColorUtils.translateColorCodes(getMessagesConfig().getString("prefix") + "Plugin erfolgreich gestartet!"));
}
