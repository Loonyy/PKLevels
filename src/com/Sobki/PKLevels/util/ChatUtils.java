package com.Sobki.PKLevels.util;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class ChatUtils {

	// Prefixes
	private String chatPrefix;
	private String errorPrefix;

	// Messages

	private String PathJoinedMsg;
	// Error Messages
	private String errorInvalidArguments;
	private String errorInsufficientPermission;
	private String errorNotEnoughArguments;
	private String errorMustBePlayer;
	private String errorInvalidPath;

	public ChatUtils(Plugin plugin) {

		// Prefixes
		this.chatPrefix = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Prefix.Default"));
		this.errorPrefix = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Prefix.Error"));

		// Messages

		this.PathJoinedMsg = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.PathJoined"));
		// Error Messages
		this.errorInvalidArguments = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.Errors.InvalidArguments"));
		this.errorInsufficientPermission = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.Errors.InsufficientPermission"));
		this.errorNotEnoughArguments = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.Errors.NotEnoughArgs"));
		this.errorMustBePlayer = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.Errors.MustBePlayer"));
		this.errorInvalidPath = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Chat.Messages.Errors.InvalidPath"));
	}

	/**
	 * 
	 * @return The text before messages in chat.
	 */
	public String getChatPrefix() {
		return chatPrefix;
	}

	/**
	 * 
	 * @return The text before error messages in chat.
	 */
	public String getErrorPrefix() {
		return errorPrefix;
	}

	/**
	 * 
	 * @return The message when a player joins a path.
	 */
	public String getPathJoinedMsg() {
		return PathJoinedMsg;
	}

	/**
	 * 
	 * @return The error message if the specified argument is invalid.
	 */
	public String getErrorInvalidArguments() {
		return errorInvalidArguments;
	}

	/**
	 * 
	 * @return The error message when the command sender does not have
	 *         permission to use the command.
	 */
	public String getErrorInsufficientPermission() {
		return errorInsufficientPermission;
	}

	/**
	 * 
	 * @return The error message when the command sender does not provide a
	 *         valid amount of arguments.
	 */
	public String getErrorNotEnoughArguments() {
		return errorNotEnoughArguments;
	}

	/**
	 * 
	 * @return The error message when the command sender is not a player.
	 */
	public String getErrorMustBePlayer() {
		return errorMustBePlayer;
	}
	
	/**
	 * 
	 * @return The error message when a specified path name is invalid.
	 */
	public String getErrorInvalidPath() {
		return errorInvalidPath;
	}
}
